package model.Planos;

import model.Veiculo;

public class PlanoAvulso extends PlanoGratuito {

    public PlanoAvulso(int codigo) {
        super(codigo, "Plano Avulso");
    }

    @Override
    public double calcularTarifa(int minutos, Veiculo veiculo) {
        return veiculo.calcularValorTotal(minutos);
    }

}
