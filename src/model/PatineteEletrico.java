package model;

public class PatineteEletrico extends VeiculoEletrico{

    public PatineteEletrico(int codigo, String modelo, int estadoAtual, double valorDesbloqueio, double valorPorMinuto, int bateria, int consumoPorMinuto){
        super(codigo, "Patinete Elétrico", estadoAtual, 3, 0.4, 100, 2);
    }

}
