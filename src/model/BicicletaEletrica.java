package model;

public class BicicletaEletrica extends VeiculoEletrico{

    public BicicletaEletrica(int codigo, String modelo, int estadoAtual, double valorDesbloqueio, double valorPorMinuto, int bateria, int consumoPorMinuto){
        super(codigo, "Bicicleta Elétrica", estadoAtual, 2, 0.30, 100, 1);
    }

}
