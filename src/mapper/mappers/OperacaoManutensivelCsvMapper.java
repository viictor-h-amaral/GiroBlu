package mapper.mappers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import auxiliares.TipoOperacaoManutensivel;
import mapper.CsvMapper;
import model.Administrador;
import model.OperacaoManutensivel;
import model.Usuario;
import model.Veiculo;
import repository.repositories.UsuarioRepository;
import repository.repositories.VeiculoRepository;

public class OperacaoManutensivelCsvMapper implements CsvMapper<OperacaoManutensivel> {

    private static final String SEPARADOR = ";";
    private static final int COLUNA_CODIGO = 0;
    private static final int COLUNA_VEICULO = 1;
    private static final int COLUNA_TIPO_OPERACAO = 2;
    private static final int COLUNA_ADMINISTRADOR = 3;
    private static final int COLUNA_DATA_OPERACAO = 4;
    private static final int QUANTIDADE_COLUNAS = 5;
    private static final String CABECALHO = "codigo;veiculo;tipoOperacao;administrador;dataOperacao";

    @Override
    public String getCabecalho() {
        return CABECALHO;
    }

    @Override
    public OperacaoManutensivel fromCsv(String linha) throws IOException {
        if (linha == null) {
            throw new IOException("A linha CSV não pode ser nula");
        }

        String[] campos = linha.split(SEPARADOR, -1);
        if (campos.length != QUANTIDADE_COLUNAS) {
            throw new IOException("A linha deve conter exatamente " + QUANTIDADE_COLUNAS + " campos");
        }

        try {
            int codigo = Integer.parseInt(campos[COLUNA_CODIGO]);
            int codigoVeiculo = Integer.parseInt(campos[COLUNA_VEICULO]);
            Veiculo veiculo = VeiculoRepository.getInstance().selectOne(codigoVeiculo);
            if (veiculo == null) {
                throw new IOException("Veículo não encontrado: " + codigoVeiculo);
            }

            int codigoTipoOperacao = Integer.parseInt(campos[COLUNA_TIPO_OPERACAO]);
            TipoOperacaoManutensivel tipoOperacao = TipoOperacaoManutensivel.intParaEnum(codigoTipoOperacao);

            int codigoAdministrador = Integer.parseInt(campos[COLUNA_ADMINISTRADOR]);
            Usuario usuario = UsuarioRepository.getInstance().selectOne(codigoAdministrador);
            if (!(usuario instanceof Administrador administrador)) {
                throw new IOException("Administrador não encontrado: " + codigoAdministrador);
            }

            LocalDateTime dataOperacao = LocalDateTime.parse(campos[COLUNA_DATA_OPERACAO]);
            return OperacaoManutensivel.carregarOperacaoManutensivel(
                    codigo, veiculo, tipoOperacao, administrador, dataOperacao);
        } catch (NumberFormatException e) {
            throw new IOException("Código ou tipo de operação inválido", e);
        } catch (DateTimeParseException e) {
            throw new IOException("Data e hora da operação inválidas", e);
        } catch (IllegalArgumentException e) {
            throw new IOException("Dados da operação de manutenção inválidos", e);
        }
    }

    @Override
    public String toCsv(OperacaoManutensivel entidade) {
        validarOperacaoManutensivel(entidade);

        String[] campos = new String[QUANTIDADE_COLUNAS];
        campos[COLUNA_CODIGO] = String.valueOf(entidade.getCodigo());
        campos[COLUNA_VEICULO] = String.valueOf(entidade.getVeiculo().getCodigo());
        campos[COLUNA_TIPO_OPERACAO] = String.valueOf(entidade.getTipoOperacao().getNumero());
        campos[COLUNA_ADMINISTRADOR] = String.valueOf(entidade.getAdministrador().getCodigo());
        campos[COLUNA_DATA_OPERACAO] = String.valueOf(entidade.getDataoperacao());

        String linha = campos[COLUNA_CODIGO];
        for (int i = 1; i < QUANTIDADE_COLUNAS; i++) {
            linha += SEPARADOR + campos[i];
        }
        return linha;
    }

    private void validarOperacaoManutensivel(OperacaoManutensivel operacao) {
        if (operacao == null) {
            throw new IllegalArgumentException("A operação de manutenção não pode ser nula");
        }
    }
}