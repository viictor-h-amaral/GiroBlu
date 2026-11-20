package model;

import exceptions.EstacaoLotadaException;

public class BicicletaEletrica extends VeiculoEletrico{

    private static double VALOR_DESBLOQUEIO = 2;
    private static double VALOR_POR_MINUTO = 0.3;
    private static int CONSUMO_POR_MINUTO = 1;
    private static String MODELO = "Bicicleta Elétrica";

    public static BicicletaEletrica criarBicicletaEletrica(Estacao estacao) throws EstacaoLotadaException{
        return new BicicletaEletrica(estacao);
    }

    public static BicicletaEletrica carregarBicicletaEletrica(int codigo, int bateria, Estacao estacao){
        return new BicicletaEletrica(codigo, bateria, estacao);
    }

    private BicicletaEletrica(int codigo, int bateria, Estacao estacao){
        super(codigo, MODELO, VALOR_DESBLOQUEIO, VALOR_POR_MINUTO, bateria, CONSUMO_POR_MINUTO, estacao);
    }

    private BicicletaEletrica(Estacao estacao) throws EstacaoLotadaException{
        super(MODELO, VALOR_DESBLOQUEIO, VALOR_POR_MINUTO, 100, CONSUMO_POR_MINUTO, estacao);
    }
}
