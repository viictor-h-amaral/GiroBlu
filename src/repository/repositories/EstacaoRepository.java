package repository.repositories;

import java.io.IOException;

import mapper.mappers.EstacaoCsvMapper;
import model.Estacao;
import repository.Repository;

public class EstacaoRepository extends Repository<Estacao> {

    private static final String NOME_ARQUIVO = "estacao.csv";

    private static EstacaoRepository instance = null;

    private EstacaoRepository() throws IOException {
        super(new EstacaoCsvMapper(), NOME_ARQUIVO);
    }

    public static EstacaoRepository getInstance() throws IOException {
        if (instance == null) {
            instance = new EstacaoRepository();
        }
        return instance;
    }

    @Override 
    protected String obterValorCampo(Estacao estacao, String campo) {
        switch (campo) {
            case "codigo":
                return String.valueOf(estacao.getCodigo());
            case "nome":
                return estacao.getNome();
            case "limiteVagas":
                return String.valueOf(estacao.getLimiteVagas());
            case "estado":
                return estacao.getEstado();
            case "cidade":
                return estacao.getCidade();
            case "logradouro":
                return estacao.getLogradouro();
            case "numero":
                return String.valueOf(estacao.getNumero());
            default:
                throw new IllegalArgumentException("Campo informado é inválido! Informado: " + campo);
        }
    }
}
