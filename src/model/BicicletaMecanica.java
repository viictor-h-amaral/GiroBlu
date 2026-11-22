package model;

import auxiliares.EstadoAtualVeiculo;
import exceptions.EstacaoLotadaException;

public class BicicletaMecanica extends Veiculo{

    private static final double VALOR_DESBLOQUEIO = 1;
    private static final double VALOR_POR_MINUTO = 0.15;
    public static final String MODELO = "Bicicleta Mecânica";

    public static BicicletaMecanica criarBicicletaMecanica(Estacao estacao) throws EstacaoLotadaException{
        return new BicicletaMecanica(estacao);
    }

    public static BicicletaMecanica carregarBicicletaMecanica(int codigo, Estacao estacao, EstadoAtualVeiculo estadoAtual){
        return new BicicletaMecanica(codigo, estacao, estadoAtual);
    }

    private BicicletaMecanica(Estacao estacao) throws EstacaoLotadaException{
        super(MODELO, VALOR_DESBLOQUEIO, VALOR_POR_MINUTO, estacao);
    }

    private BicicletaMecanica(int codigo, Estacao estacao, EstadoAtualVeiculo estadoAtual){
        super(codigo, MODELO, VALOR_DESBLOQUEIO, VALOR_POR_MINUTO, estacao, estadoAtual);
    }

}
