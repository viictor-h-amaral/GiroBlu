package model.Planos;

public class PlanoMensal extends PlanoPago {

    public PlanoMensal(int codigo, double precoMensal) {
        super(codigo, "Plano Mensal", precoMensal);
    }

    @Override
    public double calcularMensalidade() {
        return getPrecoMensal();
    }

}
