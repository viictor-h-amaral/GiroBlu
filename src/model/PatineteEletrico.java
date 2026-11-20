package model;

import exceptions.EstacaoLotadaException;

public class PatineteEletrico extends VeiculoEletrico{

    public PatineteEletrico(int codigo, Estacao estacao) throws EstacaoLotadaException{
        super(codigo, "Patinete Elétrico", 3, 0.4, 100, 2, estacao);
    }

}
