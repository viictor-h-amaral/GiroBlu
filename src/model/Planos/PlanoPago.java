package model.Planos;

import model.Plano;

public abstract class PlanoPago extends Plano {

    protected PlanoPago(String nome, double precoMensal) {
        super(nome);
        setPrecoMensal(precoMensal);
    }

    protected PlanoPago(int codigo, String nome, double precoMensal) {
        super(codigo, nome);
        setPrecoMensal(precoMensal);
    }

    private double precoMensal;

    public double getPrecoMensal() {
        return precoMensal;
    }

    protected void setPrecoMensal(double precoMensal){
        if (precoMensal <= 0) {
            throw new IllegalArgumentException("Preço da mensalidade inválido. Deve ser maior que zero.");
        }
        this.precoMensal = precoMensal;
    }
}
