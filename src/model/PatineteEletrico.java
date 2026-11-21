package model;

import auxiliares.EstadoAtualVeiculo;
import exceptions.EstacaoLotadaException;

public class PatineteEletrico extends VeiculoEletrico{

    private static final double VALOR_DESBLOQUEIO = 3;
    private static final double VALOR_POR_MINUTO = 0.4;
    private static final int CONSUMO_POR_MINUTO = 2;
    public static final String MODELO = "Patinete Elétrico";

    public static PatineteEletrico criarPatineteEletrico(Estacao estacao) throws EstacaoLotadaException{
        return new PatineteEletrico(estacao);
    }

    public static PatineteEletrico carregarPatineteEletrico(int codigo, int bateria, Estacao estacao, EstadoAtualVeiculo estadoAtual){
        return new PatineteEletrico(codigo, bateria, estacao, estadoAtual);
    }

    private PatineteEletrico(Estacao estacao) throws EstacaoLotadaException{
        super(MODELO, VALOR_DESBLOQUEIO, VALOR_POR_MINUTO, 100, CONSUMO_POR_MINUTO, estacao);
    }

    private PatineteEletrico(int codigo, int bateria, Estacao estacao, EstadoAtualVeiculo estadoAtual){
        super(codigo, MODELO, VALOR_DESBLOQUEIO, VALOR_POR_MINUTO, bateria, CONSUMO_POR_MINUTO, estacao, estadoAtual);
    }

}
