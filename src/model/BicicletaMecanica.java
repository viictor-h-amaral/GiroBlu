package model;

public class BicicletaMecanica extends Veiculo{

    public static BicicletaMecanica criarBicicletaMecanica(Estacao estacao){
        return new BicicletaMecanica(estacao);
    }

    public static BicicletaMecanica carregarBicicletaMecanica(int codigo, Estacao estacao){
        return new BicicletaMecanica(codigo, estacao);
    }

    private BicicletaMecanica(Estacao estacao){
        super("Bicicleta Mecânica", 1, 0.15, estacao);
    }

    private BicicletaMecanica(int codigo, Estacao estacao){
        super(codigo, "Bicicleta Mecânica", 1, 0.15, estacao);
    }

}
