package model.Planos;

import model.Plano;

public abstract class PlanoGratuito extends Plano {

    public PlanoGratuito(int codigo, String nome) {
        super(codigo, nome);
    }

    @Override
    public final double calcularMensalidade() {
        return 0.0; 
    }

}
