package mapper.mappers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import mapper.CsvMapper;
import model.Cliente;
import model.Estacao;
import model.Viagem;
import repository.repositories.EstacaoRepository;
import repository.repositories.UsuarioRepository;
import repository.repositories.VeiculoRepository;

public class ViagemCsvMapper implements CsvMapper<Viagem> {

    private static final String SEPARADOR = ";";
    private static final int COLUNA_CODIGO = 0;
    private static final int COLUNA_VIAJANTE = 1;
    private static final int COLUNA_VEICULO = 2;
    private static final int COLUNA_ESTACAO_ORIGEM = 3;
    private static final int COLUNA_DATA_HORA_INICIO = 4;
    private static final int COLUNA_DATA_HORA_FINAL = 5;
    private static final int QUANTIDADE_COLUNAS = 6;
    private static final String CABECALHO = "codigo;viajante;veiculo;estacaoOrigem;dataHoraInicio;dataHoraFinal";

    @Override
    public String getCabecalho() {
        return CABECALHO;
    }

    public Viagem fromCsv(String linha) throws IOException {
        if (linha == null) {
            throw new IOException("A linha CSV não pode ser nula");
        }

        String[] campos = linha.split(SEPARADOR, -1);
        if (campos.length != QUANTIDADE_COLUNAS) {
            throw new IOException("A linha deve conter exatamente " + QUANTIDADE_COLUNAS + " campos");
        }

        try {
            var codigo = Integer.parseInt(campos[COLUNA_CODIGO]);

            var viajanteRepository = UsuarioRepository.getInstance();
            int codigoViajante = Integer.parseInt(campos[COLUNA_VIAJANTE]);
            var usuarioEncontrado = viajanteRepository.selectOne(codigoViajante);

            if (!(usuarioEncontrado instanceof Cliente viajante)) {
                throw new IOException("O viajante da viagem não é um cliente válido.");
            }

            var veiculoRepository = VeiculoRepository.getInstance();
            int codigoVeiculo = Integer.parseInt(campos[COLUNA_VEICULO]);
            var veiculo = veiculoRepository.selectOne(codigoVeiculo);

            var estacaoRepository = EstacaoRepository.getInstance();
            int codigoEstacaoOrigem = Integer.parseInt(campos[COLUNA_ESTACAO_ORIGEM]);
            Estacao estacaoOrigem = estacaoRepository.selectOne(codigoEstacaoOrigem);
            if (estacaoOrigem == null) {
                throw new IOException("Estação de origem não encontrada: " + codigoEstacaoOrigem);
            }

            var dataHoraInicio = LocalDateTime.parse(campos[COLUNA_DATA_HORA_INICIO]);
            LocalDateTime dataHoraFinal;
            if (campos[COLUNA_DATA_HORA_FINAL].isBlank()) {
                dataHoraFinal = null;
            } else {
                dataHoraFinal = LocalDateTime.parse(campos[COLUNA_DATA_HORA_FINAL]);
            }

            return Viagem.carregarViagem(codigo, viajante, veiculo, estacaoOrigem, dataHoraInicio, dataHoraFinal);
        } catch (NumberFormatException e) {
            throw new IOException("Código ou Código de Acesso inválidos", e);
        } catch (IllegalArgumentException e) {
            throw new IOException("Dados do usuário inválidos", e);
        } catch (DateTimeParseException e) {
            throw new IOException("Data e hora inválidos", e);
        } catch (Exception e) {
            throw new IOException("Não foi possível carregar o usuário do Banco de dados devido a um erro: ", e);
        }
    }

    @Override
    public String toCsv(Viagem entidade) {
        validarViagem(entidade);

        String[] campos = new String[QUANTIDADE_COLUNAS];
        campos[COLUNA_CODIGO] = String.valueOf(entidade.getCodigo());
        campos[COLUNA_VIAJANTE] = String.valueOf(entidade.getViajante().getCodigo());
        campos[COLUNA_VEICULO] = String.valueOf(entidade.getVeiculo().getCodigo());
        if (entidade.getEstacaoOrigem() == null) {
            throw new IllegalArgumentException("A estação de origem da viagem não pode ser nula");
        }
        campos[COLUNA_ESTACAO_ORIGEM] = String.valueOf(entidade.getEstacaoOrigem().getCodigo());
        campos[COLUNA_DATA_HORA_INICIO] = String.valueOf(entidade.getDataHoraInicio());
        if (entidade.getDataHoraFinal() == null) {
            campos[COLUNA_DATA_HORA_FINAL] = "";
        } else {
            campos[COLUNA_DATA_HORA_FINAL] = entidade.getDataHoraFinal().toString();
        }

        String linha = campos[COLUNA_CODIGO];
        for (int i = 1; i < campos.length; i++) {
            linha += SEPARADOR + campos[i];
        }
        return linha;
    }

    private void validarViagem(Viagem viagem) {
        if (viagem == null) {
            throw new IllegalArgumentException("A viagem não pode ser nula");
        }
    }
}
