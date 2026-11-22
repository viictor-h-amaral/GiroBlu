package model.Planos;

import model.Veiculo;

public class PlanoAvulso extends PlanoGratuito {

    public static final String NOME = "Plano Avulso";

    public static PlanoAvulso criarPlanoAvulso(){
        return new PlanoAvulso();
    }

    public static PlanoAvulso carregarPlanoAvulso(int codigo){
        return new PlanoAvulso(codigo);
    }

    private PlanoAvulso(int codigo) {
        super(codigo, NOME);
    }

    private PlanoAvulso(){
        super(NOME);
    }

    @Override
    public double calcularTarifa(int minutos, Veiculo veiculo) {
        return veiculo.calcularValorTotal(minutos);
    }

}
