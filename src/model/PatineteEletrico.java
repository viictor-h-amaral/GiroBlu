package model;

public class PatineteEletrico extends VeiculoEletrico{

    public static PatineteEletrico criarPatineteEletrico(Estacao estacao){
        return new PatineteEletrico(estacao);
    }

    public static PatineteEletrico carregarPatineteEletrico(int codigo, Estacao estacao){
        return new PatineteEletrico(codigo, estacao);
    }

    private PatineteEletrico(Estacao estacao){
        super("Patinete Elétrico", 3, 0.4, 100, 2, estacao);
    }

    private PatineteEletrico(int codigo, Estacao estacao){
        super(codigo, "Patinete Elétrico", 3, 0.4, 100, 2, estacao);
    }

}
