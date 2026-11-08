package model;

public abstract class Veiculo {

    private int codigo;
    private String modelo;
    private int estadoAtual;
    private double valorDesbloqueio;
    private double valorPorMinuto;

    public Veiculo(int codigo, String modelo, int estadoAtual, double valorDesbloqueio, double valorPorMinuto){
        this.codigo = codigo;
        this.modelo = modelo;
        this.estadoAtual = estadoAtual;
        this.valorDesbloqueio = valorDesbloqueio;
        this.valorPorMinuto = valorPorMinuto;

    }

    public int getCodigo(){
        return this.codigo;
    }

    public String getModelo(){
        return this.modelo;
    }

    public int getEstadoAtual(){
        return this.estadoAtual;
    }

    public double getValorDesbloqueio(){
        return this.valorDesbloqueio;
    }

    public double getValorPorMinuto(){
        return this.valorPorMinuto;
    }

    public void enviarParaManutencao(){

    }

    public void validarLiberarParaViagem(){

    }

    public void liberarParaViagem(){

    }

    public void devolverParaEstacao(){

    }

    public double calcularValorPorTempoDeUso(int minutos){
        return minutos * this.valorPorMinuto;
    }

    private double calcularValorTotal(int minutos){

    }

}
