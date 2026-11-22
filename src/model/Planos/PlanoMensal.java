package model.Planos;

import model.Veiculo;

public class PlanoMensal extends PlanoPago {

    public static final String NOME = "Plano Mensal";

    public static PlanoMensal criarPlanoMensal(double precoMensal){
        return new PlanoMensal(precoMensal);
    }

    public static PlanoMensal carregarPlanoMensal(int codigo, double precoMensal){
        return new PlanoMensal(codigo, precoMensal);
    }

    private PlanoMensal(double precoMensal) {
        super(NOME, precoMensal);
    }

    private PlanoMensal(int codigo, double precoMensal) {
        super(codigo, NOME, precoMensal);
    }

    @Override
    public double calcularMensalidade() {
        return getPrecoMensal();
    }

    @Override
    public double calcularTarifa(int minutos, Veiculo veiculo) {
        if (minutos <= 15){
            return 0.0;
        }
        return veiculo.calcularValorPorTempoDeUso(minutos - 15);
    }

}
