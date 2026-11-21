package mapper.mappers;

import java.io.IOException;

import helper.CsvHelper;
import mapper.CsvMapper;
import model.Estacao;
import repository.repositories.VeiculoRepository;

public class EstacaoCsvMapper implements CsvMapper<Estacao>{

    private static final String SEPARADOR = ";";
    private static final int COLUNA_CODIGO = 0;
    private static final int COLUNA_NOME = 1;
    private static final int COLUNA_LIMITE_VAGAS = 2;
    private static final int COLUNA_ESTADO = 3;
    private static final int COLUNA_CIDADE = 4;
    private static final int COLUNA_LOGRADOURO = 5;
    private static final int COLUNA_NUMERO = 6;

    private static final int QUANTIDADE_COLUNAS = 7;
    private static final String CABECALHO = "codigo;nome;limiteVagas;estado;cidade;logradouro;numero";

    @Override
    public String getCabecalho() {
        return CABECALHO;
    }

    @Override
    public Estacao fromCsv(String linha) throws IOException {
        if (linha == null) {
            throw new IOException("A linha CSV não pode ser nula");
        }

        String[] campos = linha.split(SEPARADOR, -1);
        if (campos.length != QUANTIDADE_COLUNAS) {
            throw new IOException("A linha deve conter exatamente cinco campos");
        }

        if (CsvHelper.temCaractereNaoSuportado(campos[COLUNA_NOME])) {
            throw new IOException("Campo de texto inválido");
        }

        var veiculoRepository = VeiculoRepository.getInstance();

        try {
            return Estacao.carregarEstacao(
                    Integer.parseInt(campos[COLUNA_CODIGO]),
                    campos[COLUNA_NOME],
                    Integer.parseInt(campos[COLUNA_LIMITE_VAGAS]),
                    campos[COLUNA_ESTADO],
                    campos[COLUNA_CIDADE],
                    campos[COLUNA_LOGRADOURO],
                    Integer.parseInt(campos[COLUNA_NUMERO]),
                    veiculoRepository.selectMany("estacao", campos[COLUNA_CODIGO])
            );
        } catch (Exception e) {
            throw new IOException("Não foi possível carregar a Estação do Banco de dados devido a: ", e);
        }
    }

    @Override
    public String toCsv(Estacao entidade) {
        validarEstacao(entidade);

        String[] campos = new String[QUANTIDADE_COLUNAS];
        campos[COLUNA_CODIGO] = String.valueOf(entidade.getCodigo());
        campos[COLUNA_NOME] = entidade.getNome();
        campos[COLUNA_LIMITE_VAGAS] = String.valueOf(entidade.getLimiteVagas());
        campos[COLUNA_ESTADO] = entidade.getEstado();
        campos[COLUNA_CIDADE] = entidade.getCidade();
        campos[COLUNA_LOGRADOURO] = entidade.getLogradouro();
        campos[COLUNA_NUMERO] = String.valueOf(entidade.getNumero());

        String linha = campos[COLUNA_CODIGO];
        for (int i = 1; i < QUANTIDADE_COLUNAS; i++) {
            linha += SEPARADOR + campos[i];
        }
        return linha;
    }

    private void validarEstacao(Estacao estacao){
        if (estacao == null) {
            throw new IllegalArgumentException("O veículo não pode ser nulo");
        }
        else if (CsvHelper.temCaractereNaoSuportado(estacao.getNome())
                || CsvHelper.temCaractereNaoSuportado(estacao.getLogradouro())
                || CsvHelper.temCaractereNaoSuportado(estacao.getCidade())
                || CsvHelper.temCaractereNaoSuportado(estacao.getEstado())) {
            throw new IllegalArgumentException(
                    "O campo de texto não pode conter ponto e vírgula (;), aspas (\") ou quebras de linha");
        }
    }

}
