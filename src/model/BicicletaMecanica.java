package model;

public class BicicletaMecanica extends Veiculo{

    public BicicletaMecanica(int codigo, String modelo, int estadoAtual, double valorDesbloqueio, double valorPorMinuto){
        super(codigo, "Bicicleta Mecânica", estadoAtual, 1, 0.15);
    }

}
