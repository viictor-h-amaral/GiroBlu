package mapper.mappers;

import java.io.IOException;

import helper.CsvHelper;
import mapper.CsvMapper;
import model.BicicletaMecanica;
import model.Veiculo;
import repository.repositories.EstacaoRepository;

public class VeiculoCsvMapper implements CsvMapper<Veiculo> {

    private static final String SEPARADOR = ";";
    private static final int COLUNA_CODIGO = 0;
    private static final int COLUNA_MODELO = 2;
    private static final int COLUNA_ESTADO_ATUAL = 3;
    private static final int COLUNA_VALOR_DESBLOQUEIO = 4;
    private static final int COLUNA_VALOR_POR_MINUTO = 5;
    private static final int COLUNA_ESTACAO = 6;
    private static final int QUANTIDADE_COLUNAS = 7;
    private static final String CABECALHO = "codigo;modelo;estadoAtual;valorDesbloqueio;valorPorMinuto;estacao";

    @Override
    public String getCabecalho() {
        return CABECALHO;
    }

    @Override
    public Veiculo fromCsv(String linha) throws IOException {
        if (linha == null) {
            throw new IOException("A linha CSV não pode ser nula");
        }

        String[] campos = linha.split(SEPARADOR, -1);
        if (campos.length != QUANTIDADE_COLUNAS) {
            throw new IOException("A linha deve conter exatamente cinco campos");
        }

        if (CsvHelper.temCaractereNaoSuportado(campos[COLUNA_MODELO])) {
            throw new IOException("Campo de texto inválido");
        }

        var estacaoRepository = new EstacaoRepository();
        try {
            return BicicletaMecanica.carregarBicicletaMecanica(
                    Integer.parseInt(campos[COLUNA_CODIGO]),
                    estacaoRepository.selectOne(Integer.parseInt(campos[COLUNA_ESTACAO]))// aqui vai precisar do SelectOne da Estacao
            );
        } catch (NumberFormatException e) {
            throw new IOException("Código ou valor inválido", e);
        }
    }

    @Override
    public String toCsv(Veiculo entidade) {
        validarVeiculo(entidade);

        String[] campos = new String[QUANTIDADE_COLUNAS];
        campos[COLUNA_CODIGO] = String.valueOf(entidade.getCodigo());
        campos[COLUNA_MODELO] = CsvHelper.stringParaCsv(entidade.getModelo());
        campos[COLUNA_ESTADO_ATUAL] = String.valueOf(entidade.getEstadoAtual().ordinal());
        campos[COLUNA_VALOR_DESBLOQUEIO] = String.valueOf(entidade.getValorDesbloqueio());
        campos[COLUNA_VALOR_POR_MINUTO] = String.valueOf(entidade.getValorPorMinuto());
        campos[COLUNA_ESTACAO] = String.valueOf(entidade.getEstacao().getCodigo());

        String linha = campos[0];
        for (int i = 1; i < campos.length; i++) {
            linha += SEPARADOR + campos[i];
        }
        return linha;
    }

    private void validarVeiculo(Veiculo veiculo) {
        if (veiculo == null) {
            throw new IllegalArgumentException("O veículo não pode ser nulo");
        }
        else if (CsvHelper.temCaractereNaoSuportado(veiculo.getModelo())) {
            throw new IllegalArgumentException(
                    "O campo de texto não pode conter ponto e vírgula (;), aspas (\") ou quebras de linha");
        }
    }
}
