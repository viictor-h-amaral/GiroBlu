package model;

public abstract class VeiculoEletrico extends Veiculo {

    private int bateria;
    private int consumoPorMinuto;

    public VeiculoEletrico(int codigo, String modelo, int estadoAtual, double valorDesbloqueio, double valorPorMinuto, int bateria, int consumoPorMinuto){
        super(codigo, modelo, estadoAtual, valorDesbloqueio, valorPorMinuto);
        this.bateria = bateria;
        this.consumoPorMinuto = consumoPorMinuto;
    }

    public int getBateria(){
        return this.bateria;
    }

    public void recarregarBateria(Administrador administrador){

    }

    public int getConsumoPorMinuto(){
        return this.consumoPorMinuto;
    }

    public void validarLiberarParaViagem(){

    }

    public void calcularBateria(int minutos){
        this.bateria = this.bateria - (minutos * this.consumoPorMinuto);
        if(this.bateria < 0){
            this.bateria = 0;
        }
    }
}
