package model.Planos;

import model.Plano;

public abstract class PlanoPago extends Plano {

    public PlanoPago(int codigo, String nome, double precoMensal) {
        super(codigo, nome);
        validarPrecoMensal(precoMensal);

        this.precoMensal = precoMensal;
    }

    private void validarPrecoMensal(double precoMensal) {
        if (precoMensal <= 0) {
            throw new IllegalArgumentException("Preço da mensalidade inválido. Deve ser maior que zero.");
        }
    }

    private double precoMensal;
    public double getPrecoMensal() {
        return precoMensal;
    }

}
