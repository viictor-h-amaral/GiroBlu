package repository.repositories;

import java.io.IOException;

import mapper.mappers.PlanosCsvMapper;
import model.Plano;
import model.Planos.PlanoPago;
import model.Planos.PlanoUniversitario;
import repository.Repository;

public class PlanoRepository extends Repository<Plano> {

    private static final String NOME_ARQUIVO = "planos.csv";
    private static final String COLUNA_VAZIA = "";

    private static PlanoRepository instance = null;

    private PlanoRepository() throws IOException {
        super(new PlanosCsvMapper(), NOME_ARQUIVO);
    }

    public static PlanoRepository getInstance() throws IOException {
        if (instance == null) {
            instance = new PlanoRepository();
        }
        return instance;
    }

    @Override 
    protected String obterValorCampo(Plano plano, String campo) {
        switch (campo) {
            case "codigo":
                return String.valueOf(plano.getCodigo());
            case "nome":
                return plano.getNome();
            case "precoMensal":
                if (plano instanceof PlanoPago) {
                    return String.valueOf(((PlanoPago) plano).getPrecoMensal());
                }
                return COLUNA_VAZIA;
            case "instituicao":
                if (plano instanceof PlanoUniversitario) {
                    return String.valueOf(((PlanoUniversitario) plano).getInstituicao());
                }
                return COLUNA_VAZIA;
            case "matricula":
                if (plano instanceof PlanoUniversitario) {
                    return String.valueOf(((PlanoUniversitario) plano).getMatricula());
                }
                return COLUNA_VAZIA;
            default:
                throw new IllegalArgumentException("Campo informado é inválido! Informado: " + campo);
        }
    }
}
