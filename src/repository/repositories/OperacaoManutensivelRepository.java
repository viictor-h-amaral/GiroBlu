package repository.repositories;

import java.io.IOException;

import mapper.mappers.OperacaoManutensivelCsvMapper;
import model.OperacaoManutensivel;
import repository.Repository;

public class OperacaoManutensivelRepository extends Repository<OperacaoManutensivel> {

    private static final String NOME_ARQUIVO = "operacoes_manutencao.csv";

    private static OperacaoManutensivelRepository instance = null;

    private OperacaoManutensivelRepository() throws IOException {
        super(new OperacaoManutensivelCsvMapper(), NOME_ARQUIVO);
    }

    public static OperacaoManutensivelRepository getInstance() throws IOException {
        if (instance == null) {
            instance = new OperacaoManutensivelRepository();
        }
        return instance;
    }

    @Override
    protected String obterValorCampo(OperacaoManutensivel operacao, String campo) {
        switch (campo) {
            case "codigo":
                return String.valueOf(operacao.getCodigo());
            case "veiculo":
                return String.valueOf(operacao.getVeiculo().getCodigo());
            case "tipoOperacao":
                return String.valueOf(operacao.getTipoOperacao().getNumero());
            case "administrador":
                return String.valueOf(operacao.getAdministrador().getCodigo());
            case "dataOperacao":
                return String.valueOf(operacao.getDataoperacao());
            default:
                throw new IllegalArgumentException("Campo informado é inválido! Informado: " + campo);
        }
    }
}