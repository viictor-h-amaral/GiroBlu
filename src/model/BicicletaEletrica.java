package model;

import exceptions.EstacaoLotadaException;

public class BicicletaEletrica extends VeiculoEletrico {

    public BicicletaEletrica(int codigo, Estacao estacao) throws EstacaoLotadaException{
        super(codigo, "Bicicleta Elétrica", 2, 0.30, 100, 1, estacao);
    }

}
