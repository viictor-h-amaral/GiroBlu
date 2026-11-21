package model;

import exceptions.EstacaoLotadaException;

public class BicicletaMecanica extends Veiculo{

    public BicicletaMecanica(int codigo, Estacao estacao) throws EstacaoLotadaException{
        super(codigo, "Bicicleta Mecânica", 1, 0.15, estacao);
    }

}
