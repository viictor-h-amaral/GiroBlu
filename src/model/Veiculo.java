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

    protected Veiculo(int codigo, String modelo, double valorDesbloqueio, double valorPorMinuto, Estacao estacao, EstadoAtualVeiculo estadoAtual) {
        super(codigo);
        setModelo(modelo);
        setEstadoAtual(estadoAtual);
        setValorDesbloqueio(valorDesbloqueio);
        setValorPorMinuto(valorPorMinuto);        
    }
    
    protected Veiculo(String modelo, double valorDesbloqueio, double valorPorMinuto, Estacao estacao) throws EstacaoLotadaException {
        super();
        setModelo(modelo);
        setEstadoAtual(EstadoAtualVeiculo.DISPONIVEL);
        setValorDesbloqueio(valorDesbloqueio);
        setValorPorMinuto(valorPorMinuto);
        setEstacao(estacao);
        estacao.adicionarVeiculo(this);
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo){
        if(modelo == null || modelo.isBlank()){
            throw new IllegalArgumentException("Modelo inválido. Não pode ser nulo ou vazio.");
        }
        this.modelo = modelo;
    }

    public EstadoAtualVeiculo getEstadoAtual() {
        return this.estadoAtual;
    }

    private void setEstadoAtual(EstadoAtualVeiculo estado){
        if (estado == null) {
            throw new IllegalArgumentException("Estado atual inválido. Não pode ser nulo.");
        }
        this.estadoAtual = estado;
    }

    public double getValorDesbloqueio() {
        return this.valorDesbloqueio;
    }

    public void setValorDesbloqueio(double valorDesbloqueio){
        if(valorDesbloqueio < 0){
            throw new IllegalArgumentException("Valor desbloqueio inválido. Deve ser maior que zero.");
        }
        this.valorDesbloqueio = valorDesbloqueio;
    }

    public double getValorPorMinuto() {
        return this.valorPorMinuto;
    }

    public void setValorPorMinuto(double valorPorMinuto){
        if(valorPorMinuto < 0){
            throw new IllegalArgumentException("Valor desbloqueio inválido. Deve ser maior que zero.");
        }
        this.valorPorMinuto = valorPorMinuto;
    }

    public Estacao getEstacao(){
        return this.estacao;
    }

    private void setEstacao(Estacao estacao){
        if (estacao == null && estadoAtual != EstadoAtualVeiculo.EM_VIAGEM) {
            throw new IllegalArgumentException("Um veículo fora de viagem deve estar associado a uma estação.");
        }
        this.estacao = estacao;
    }

    public void validarLiberarParaViagem() throws ProblemaViagemException {
        if(getEstadoAtual() != EstadoAtualVeiculo.DISPONIVEL){
            throw new VeiculoIndisponivelException();
        }
    }

    public void liberarParaViagem() throws ProblemaViagemException{
        validarLiberarParaViagem();
        setEstadoAtual(EstadoAtualVeiculo.EM_VIAGEM);
        this.estacao = null;
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
        setEstacao(estacao);
        setEstadoAtual(EstadoAtualVeiculo.DISPONIVEL);
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
        setEstadoAtual(EstadoAtualVeiculo.EM_MANUTENCAO);
    }

    @Override 
    public void retornarManutencao() throws VeiculoNaoEmManutencaoException{
        if(getEstadoAtual() != EstadoAtualVeiculo.EM_MANUTENCAO){
            throw new VeiculoNaoEmManutencaoException();
        }
        setEstadoAtual(EstadoAtualVeiculo.DISPONIVEL);
    }

}
