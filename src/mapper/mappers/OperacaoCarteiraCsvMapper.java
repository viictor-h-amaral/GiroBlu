package mapper.mappers;

import java.io.IOException;
import java.time.LocalDateTime;

import auxiliares.TipoOperacaoCarteira;
import mapper.CsvMapper;
import model.OperacaoCarteira;
import repository.repositories.CarteiraRepository;

public class OperacaoCarteiraCsvMapper implements CsvMapper<OperacaoCarteira> {

    private static final String SEPARADOR = ";";

    private static final int COLUNA_CODIGO = 0;
    private static final int COLUNA_CARTEIRA = 1;
    private static final int COLUNA_TIPO_OPERACAO = 2;
    private static final int COLUNA_VALOR_OPERACAO = 3; 
    private static final int COLUNA_DATA_OPERACAO = 4;

    private static final int QUANTIDADE_COLUNAS = 5;
    private static final String CABECALHO = "codigo;carteira;tipoOperacao;valorOperacao;dataOperacao";

    @Override
    public String getCabecalho() {
        return CABECALHO;
    }

    @Override
    public OperacaoCarteira fromCsv(String linha) throws IOException {
        if (linha == null) {
            throw new IOException("A linha CSV não pode ser nula");
        }

        String[] campos = linha.split(SEPARADOR, -1);
        if (campos.length != QUANTIDADE_COLUNAS) {
            throw new IOException("A linha deve conter exatamente " + QUANTIDADE_COLUNAS + " campos");
        }

        try {
            int codigoCarteira = Integer.parseInt(campos[COLUNA_CARTEIRA]);
            var carteira = CarteiraRepository.getInstance().selectOne(codigoCarteira);

            int codigoTipoOperacao = Integer.parseInt(campos[COLUNA_TIPO_OPERACAO]);
            var tipoOperacao = TipoOperacaoCarteira.intParaEnum(codigoTipoOperacao);

            return OperacaoCarteira.carregarOperacaoCarteira(
                    Integer.parseInt(campos[COLUNA_CODIGO]),
                    carteira,
                    tipoOperacao,
                    Double.parseDouble(campos[COLUNA_VALOR_OPERACAO]),
                    LocalDateTime.parse(campos[COLUNA_DATA_OPERACAO])
            );
        } catch (Exception e) {
            throw new IOException("Não foi possível carregar a Estação do Banco de dados devido a: ", e);
        }
    }

    @Override
    public String toCsv(OperacaoCarteira entidade) {
        validarOperacaoCarteira(entidade);

        String[] campos = new String[QUANTIDADE_COLUNAS];

        campos[COLUNA_CODIGO] = String.valueOf(entidade.getCodigo());
        campos[COLUNA_CARTEIRA] = String.valueOf(entidade.getCarteira().getCodigo());
        campos[COLUNA_TIPO_OPERACAO] = String.valueOf(entidade.getTipoOperacao().getNumero());
        campos[COLUNA_VALOR_OPERACAO] = String.valueOf(entidade.getValorOperacao());
        campos[COLUNA_DATA_OPERACAO] = String.valueOf(entidade.getDataOperacao());

        String linha = campos[COLUNA_CODIGO];
        for (int i = 1; i < QUANTIDADE_COLUNAS; i++) {
            linha += SEPARADOR + campos[i];
        }
        return linha;
    }
    
    private void validarOperacaoCarteira(OperacaoCarteira operacaoCarteira){
        if (operacaoCarteira == null) {
            throw new IllegalArgumentException("A estação não pode ser nulo");
        }
    }
}
