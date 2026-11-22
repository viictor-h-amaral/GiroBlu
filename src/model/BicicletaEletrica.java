package model;

import auxiliares.EstadoAtualVeiculo;
import exceptions.EstacaoLotadaException;

public class BicicletaEletrica extends VeiculoEletrico{

    private static final double VALOR_DESBLOQUEIO = 2;
    private static final double VALOR_POR_MINUTO = 0.3;
    private static final int CONSUMO_POR_MINUTO = 1;
    public static final String MODELO = "Bicicleta Elétrica";

    public static BicicletaEletrica criarBicicletaEletrica(Estacao estacao) throws EstacaoLotadaException{
        return new BicicletaEletrica(estacao);
    }

    public static BicicletaEletrica carregarBicicletaEletrica(int codigo, int bateria, Estacao estacao, EstadoAtualVeiculo estadoAtual){
        return new BicicletaEletrica(codigo, bateria, estacao, estadoAtual);
    }

    private BicicletaEletrica(int codigo, int bateria, Estacao estacao, EstadoAtualVeiculo estadoAtual){
        super(codigo, MODELO, VALOR_DESBLOQUEIO, VALOR_POR_MINUTO, bateria, CONSUMO_POR_MINUTO, estacao, estadoAtual);
    }

    private BicicletaEletrica(Estacao estacao) throws EstacaoLotadaException{
        super(MODELO, VALOR_DESBLOQUEIO, VALOR_POR_MINUTO, 100, CONSUMO_POR_MINUTO, estacao);
    }
}
