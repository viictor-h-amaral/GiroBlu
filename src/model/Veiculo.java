package model;

import exceptions.EstacaoLotadaException;
import exceptions.ProblemaViagemException;
import exceptions.VeiculoEmUsoException;
import exceptions.VeiculoIndisponivelException;
import exceptions.VeiculoNaoEmManutencaoException;
import exceptions.VeiculoNaoEmViagemException;
import auxiliares.EstadoAtualVeiculo;
import contracts.Manutensivel;
import contracts.Salvavel;

public abstract class Veiculo extends Salvavel implements Manutensivel {

    private String modelo;
    private EstadoAtualVeiculo estadoAtual;
    private double valorDesbloqueio;
    private double valorPorMinuto;
    private Estacao estacao;

    protected Veiculo(int codigo, String modelo, double valorDesbloqueio, double valorPorMinuto, Estacao estacao) {
        super(codigo);
        validarCriacaoVeiculo(modelo, valorDesbloqueio, valorPorMinuto, estacao);
        this.modelo = modelo;
        this.estadoAtual = EstadoAtualVeiculo.DISPONIVEL;
        this.valorDesbloqueio = valorDesbloqueio;
        this.valorPorMinuto = valorPorMinuto;
        this.estacao = estacao;
    }
    
    protected Veiculo(String modelo, double valorDesbloqueio, double valorPorMinuto, Estacao estacao) throws EstacaoLotadaException {
        super();
        validarCriacaoVeiculo(modelo, valorDesbloqueio, valorPorMinuto, estacao);
        this.modelo = modelo;
        this.estadoAtual = EstadoAtualVeiculo.DISPONIVEL;
        this.valorDesbloqueio = valorDesbloqueio;
        this.valorPorMinuto = valorPorMinuto;
        this.estacao = estacao;
        estacao.adicionarVeiculo(this);
    }

    private void validarCriacaoVeiculo(String modelo, double valorDesbloqueio, double valorPorMinuto, Estacao estacao){
        if(modelo == null || modelo.isBlank()){
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

    public void retornarViagem() throws VeiculoNaoEmViagemException{ //TO DO: parâmetros do método Estacao estacao. Realizar exception EstacaoLotadaException
        if(getEstadoAtual() != EstadoAtualVeiculo.EM_VIAGEM){
            throw new VeiculoNaoEmViagemException();
        }
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
    }

    @Override 
    public void retornarManutencao() throws VeiculoNaoEmManutencaoException{
        if(getEstadoAtual() != EstadoAtualVeiculo.EM_MANUTENCAO){
            throw new VeiculoNaoEmManutencaoException();
        }
        this.estadoAtual = EstadoAtualVeiculo.DISPONIVEL;
    }

}
