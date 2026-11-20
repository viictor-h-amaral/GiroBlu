package model;

import exceptions.EstacaoLotadaException;
import exceptions.ProblemaViagemException;
import exceptions.VeiculoEmUsoException;
import exceptions.VeiculoIndisponivelException;
import exceptions.VeiculoNaoEmManutencaoException;
import exceptions.VeiculoNaoEmViagemException;
import auxiliares.EstadoAtualVeiculo;
import contracts.Manutensivel;

public abstract class Veiculo implements Manutensivel{

    private int codigo;
    private String modelo;
    private EstadoAtualVeiculo estadoAtual;
    private double valorDesbloqueio;
    private double valorPorMinuto;
    private Estacao estacao;

    public Veiculo(int codigo, String modelo, double valorDesbloqueio, double valorPorMinuto, Estacao estacao) throws EstacaoLotadaException{
        validarCriacaoVeiculo(codigo, modelo, valorDesbloqueio, valorPorMinuto, estacao);
        this.codigo = codigo;
        this.modelo = modelo;
        this.estadoAtual = EstadoAtualVeiculo.DISPONIVEL;
        this.valorDesbloqueio = valorDesbloqueio;
        this.valorPorMinuto = valorPorMinuto;
        this.estacao = estacao;
        this.estacao.adicionarVeiculo(this);
    }

    private void validarCriacaoVeiculo(int codigo, String modelo, double valorDesbloqueio, double valorPorMinuto, Estacao estacao) throws EstacaoLotadaException{
        if(codigo <= 0){
            throw new IllegalArgumentException("Código inválido. Deve ser maior que zero.");
        }
        else if(modelo == null || modelo.isBlank()){
            throw new IllegalArgumentException("Modelo inválido. Não pode ser nulo ou vazio.");
        }
        else if(valorDesbloqueio < 0){
            throw new IllegalArgumentException("Valor desbloqueio inválido. Deve ser maior que zero.");
        }
        else if(valorPorMinuto < 0){
            throw new IllegalArgumentException("Valor por minuto inválido. Deve ser maior que zero.");
        }
        else if(estacao == null){
            throw new IllegalArgumentException("Estacao inválida. A estacao não pode ser nula.");
        }
        else if(estacao.isLotada()){
            throw new EstacaoLotadaException(estacao.getNome(), estacao.getLimiteVagas());
        }
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getModelo() {
        return this.modelo;
    }

    public EstadoAtualVeiculo getEstadoAtual() {
        return this.estadoAtual;
    }

    public double getValorDesbloqueio() {
        return this.valorDesbloqueio;
    }

    public double getValorPorMinuto() {
        return this.valorPorMinuto;
    }

    public Estacao getEstacao(){
        return this.estacao;
    }

    public void validarLiberarParaViagem() throws ProblemaViagemException {
        if(getEstadoAtual() != EstadoAtualVeiculo.DISPONIVEL){
            throw new VeiculoIndisponivelException();
        }
    }

    public void liberarParaViagem() throws ProblemaViagemException{
        validarLiberarParaViagem();
        this.estadoAtual = EstadoAtualVeiculo.EM_VIAGEM;
    }

    public void validarRetornarViagem(Estacao estacao) throws VeiculoNaoEmViagemException, EstacaoLotadaException{
        if(getEstadoAtual() != EstadoAtualVeiculo.EM_VIAGEM){
            throw new VeiculoNaoEmViagemException();
        }
        else if(estacao.isLotada()){
            throw new EstacaoLotadaException(estacao.getNome(), estacao.getLimiteVagas());
        }
    }

    public void retornarViagem(Estacao estacao) throws VeiculoNaoEmViagemException, EstacaoLotadaException{ //TO DO: parâmetros do método Estacao estacao. Realizar exception EstacaoLotadaException
        validarRetornarViagem(estacao);
        this.estadoAtual = EstadoAtualVeiculo.DISPONIVEL;
    }

    public double calcularValorPorTempoDeUso(int minutos) {
        if (minutos < 0) {
            throw new IllegalArgumentException("Tempo de uso inválido. Deve ser maior ou igual a zero.");
        }
        return minutos * getValorPorMinuto();
    }

    public double calcularValorTotal(int minutos) {
        if (minutos < 0) {
            throw new IllegalArgumentException("Tempo de uso inválido. Deve ser maior ou igual a zero.");
        }
        return calcularValorPorTempoDeUso(minutos) + getValorDesbloqueio();
    }
    
    @Override 
    public void enviarParaManutencao() throws VeiculoEmUsoException{
        if(getEstadoAtual() != EstadoAtualVeiculo.DISPONIVEL){
            throw new VeiculoEmUsoException();
        }
        this.estadoAtual = EstadoAtualVeiculo.EM_MANUTENCAO;
        this.estacao = null;
    }

    @Override 
    public void retornarManutencao() throws VeiculoNaoEmManutencaoException{
        if(getEstadoAtual() != EstadoAtualVeiculo.EM_MANUTENCAO){
            throw new VeiculoNaoEmManutencaoException();
        }
        this.estadoAtual = EstadoAtualVeiculo.DISPONIVEL;
    }

}
