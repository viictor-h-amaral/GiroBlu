package model;

import exceptions.ProblemaViagemException;
import exceptions.VeiculoEmUsoException;
import auxiliares.EstadoAtualVeiculo;

public abstract class Veiculo {

    private int codigo;
    private String modelo;
    private EstadoAtualVeiculo estadoAtual;
    private double valorDesbloqueio;
    private double valorPorMinuto;

    public Veiculo(int codigo, String modelo, double valorDesbloqueio, double valorPorMinuto) {
        validarCriacaoVeiculo(codigo, modelo, valorDesbloqueio, valorPorMinuto);
        this.codigo = codigo;
        this.modelo = modelo;
        this.estadoAtual = EstadoAtualVeiculo.DISPONIVEL;
        this.valorDesbloqueio = valorDesbloqueio;
        this.valorPorMinuto = valorPorMinuto;
    }

    private void validarCriacaoVeiculo(int codigo, String modelo, double valorDesbloqueio, double valorPorMinuto){
        if(codigo <= 0){
            throw new IllegalArgumentException("Código inválido. Deve ser maior que zero.");
        }
        if(modelo == null || modelo.trim().isEmpty()){
            throw new IllegalArgumentException("Modelo inválido. Não pode ser nulo ou vazio.");
        }
        if(valorDesbloqueio <= 0){
            throw new IllegalArgumentException("Valor desbloqueio inválido. Deve ser maior que zero.");
        }
        if(valorPorMinuto <= 0){
            throw new IllegalArgumentException("Valor por minuto inválido. Deve ser maior que zero.");
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

    public void validarLiberarParaViagem() throws ProblemaViagemException {
        if(this.estadoAtual == EstadoAtualVeiculo.INDISPONIVEL){
            throw new VeiculoEmUsoException();
        }
    }

    public void liberarParaViagem() throws ProblemaViagemException{
        validarLiberarParaViagem();
        this.estadoAtual = EstadoAtualVeiculo.INDISPONIVEL;
    }

    public void devolverParaEstacao() {
        this.estadoAtual = EstadoAtualVeiculo.DISPONIVEL;
    }

    public double calcularValorPorTempoDeUso(int minutos) {
        return minutos * getValorPorMinuto();
    }

    public double calcularValorTotal(int minutos) {
        return calcularValorPorTempoDeUso(minutos) + getValorDesbloqueio();
    }

}
