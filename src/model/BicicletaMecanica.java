package model;

import exceptions.EstacaoLotadaException;

public class BicicletaMecanica extends Veiculo{

    private static double VALOR_DESBLOQUEIO = 1;
    private static double VALOR_POR_MINUTO = 0.15;
    private static String MODELO = "Bicicleta Mecânica";

    public static BicicletaMecanica criarBicicletaMecanica(Estacao estacao) throws EstacaoLotadaException{
        return new BicicletaMecanica(estacao);
    }

    public static BicicletaMecanica carregarBicicletaMecanica(int codigo, Estacao estacao){
        return new BicicletaMecanica(codigo, estacao);
    }

    private BicicletaMecanica(Estacao estacao) throws EstacaoLotadaException{
        super(MODELO, VALOR_DESBLOQUEIO, VALOR_POR_MINUTO, estacao);
    }

    private BicicletaMecanica(int codigo, Estacao estacao){
        super(codigo, MODELO, VALOR_DESBLOQUEIO, VALOR_POR_MINUTO, estacao);
    }

}
