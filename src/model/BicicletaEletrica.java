package model;

public class BicicletaEletrica extends VeiculoEletrico{

    public static BicicletaEletrica criarBicicletaEletrica(Estacao estacao){
        return new BicicletaEletrica(estacao);
    }

    public static BicicletaEletrica carregarBicicletaEletrica(int codigo, Estacao estacao){
        return new BicicletaEletrica(codigo, estacao);
    }

    private BicicletaEletrica(int codigo, Estacao estacao){
        super(codigo, "Bicicleta Elétrica", 2, 0.30, 100, 1, estacao);
    }

    private BicicletaEletrica(Estacao estacao){
        super("Bicicleta Elétrica", 2, 0.30, 100, 1, estacao);
    }

}
