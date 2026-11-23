package repository.repositories;

import java.io.IOException;

import mapper.mappers.OperacaoCarteiraCsvMapper;
import model.OperacaoCarteira;
import repository.Repository;

public class OperacaoCarteiraRepository extends Repository<OperacaoCarteira> {

    private static final String NOME_ARQUIVO = "operacoes_carteira.csv";

    private static OperacaoCarteiraRepository instance = null;

    private OperacaoCarteiraRepository() throws IOException {
        super(new OperacaoCarteiraCsvMapper(), NOME_ARQUIVO);
    }

    public static OperacaoCarteiraRepository getInstance() throws IOException {
        if (instance == null) {
            instance = new OperacaoCarteiraRepository();
        }
        return instance;
    }

    @Override 
    protected String obterValorCampo(OperacaoCarteira operacao, String campo) {
        switch (campo) {
            case "codigo":
                return String.valueOf(operacao.getCodigo());
            case "carteira":
                return String.valueOf(operacao.getCarteira().getCodigo());
            case "valorOperacao":
                return String.valueOf(operacao.getValorOperacao());
            case "tipoOperacao":
                return String.valueOf(operacao.getTipoOperacao().getNumero());
            case "dataOperacao":
                return String.valueOf(operacao.getDataOperacao());
            default:
                throw new IllegalArgumentException("Campo informado é inválido! Informado: " + campo);
        }
    }
}
