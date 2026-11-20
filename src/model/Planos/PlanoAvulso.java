package model.Planos;

import model.Veiculo;

public class PlanoAvulso extends PlanoGratuito {

    public static PlanoAvulso criarPlanoAvulso(){
        return new PlanoAvulso();
    }

    public static PlanoAvulso carregarPlanoAvulso(int codigo){
        return new PlanoAvulso(codigo);
    }

    private PlanoAvulso(int codigo) {
        super(codigo, "Plano Avulso");
    }

    private PlanoAvulso(){
        super("Plano Avulso");
    }

    @Override
    public double calcularTarifa(int minutos, Veiculo veiculo) {
        return veiculo.calcularValorTotal(minutos);
    }

}
