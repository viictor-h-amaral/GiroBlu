package model.Planos;

import model.Plano;

public abstract class PlanoGratuito extends Plano {

    protected PlanoGratuito(int codigo, String nome) {
        super(codigo, nome);
    }

    protected PlanoGratuito(String nome) {
        super(nome);
    }

    @Override
    public final double calcularMensalidade() {
        return 0.0; 
    }

}
