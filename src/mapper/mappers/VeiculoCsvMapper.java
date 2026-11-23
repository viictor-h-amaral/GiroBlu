package mapper.mappers;

import java.io.IOException;

import auxiliares.EstadoAtualVeiculo;
import helper.CsvHelper;
import mapper.CsvMapper;
import model.BicicletaEletrica;
import model.BicicletaMecanica;
import model.Estacao;
import model.PatineteEletrico;
import model.Veiculo;
import model.VeiculoEletrico;
import repository.repositories.EstacaoRepository;

public class VeiculoCsvMapper implements CsvMapper<Veiculo> {

    private static final String SEPARADOR = ";";
    private static final int COLUNA_CODIGO = 0;
    private static final int COLUNA_MODELO = 1;
    private static final int COLUNA_ESTADO_ATUAL = 2;
    private static final int COLUNA_VALOR_DESBLOQUEIO = 3;
    private static final int COLUNA_VALOR_POR_MINUTO = 4;
    private static final int COLUNA_ESTACAO = 5;
    private static final int COLUNA_BATERIA = 6;
    private static final int COLUNA_CONSUMO_POR_MINUTO = 7;
    private static final int QUANTIDADE_COLUNAS = 8;
    private static final String CABECALHO = "codigo;modelo;estadoAtual;valorDesbloqueio;valorPorMinuto;estacao;bateria;consumoPorMinuto";

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
            throw new IOException("A linha deve conter exatamente " + QUANTIDADE_COLUNAS + " campos");
        }

        if (CsvHelper.temCaractereNaoSuportado(campos[COLUNA_MODELO])) {
            throw new IOException("Campo de texto inválido");
        }

        try {
            int codigo = Integer.parseInt(campos[COLUNA_CODIGO]);
            int codigoEstado = Integer.parseInt(campos[COLUNA_ESTADO_ATUAL]);
            
            EstadoAtualVeiculo estadoAtual = null;
            for (EstadoAtualVeiculo estado : EstadoAtualVeiculo.values()) {
                if (estado.getEstadoAtual() == codigoEstado) {
                    estadoAtual = estado;
                    break;
                }
            }
            if (estadoAtual == null) {
                throw new IllegalArgumentException("Estado de veículo inválido: " + codigoEstado);
            }

            Estacao estacao = null;
            if (campos[COLUNA_ESTACAO].isBlank()) {
                if (estadoAtual != EstadoAtualVeiculo.EM_VIAGEM) {
                    throw new IOException("Somente veículos em viagem podem estar sem estação.");
                }
            } else {
                int codigoEstacao = Integer.parseInt(campos[COLUNA_ESTACAO]);
                var estacaoRepository = EstacaoRepository.getInstance();
                estacao = estacaoRepository.selectOne(codigoEstacao);
                if (estacao == null) {
                    throw new IOException("Estação não encontrada: " + codigoEstacao);
                }
            }

            Veiculo veiculo;
            if (campos[COLUNA_MODELO].equals(BicicletaMecanica.MODELO)) {
                veiculo = BicicletaMecanica.carregarBicicletaMecanica(codigo, estacao, estadoAtual);
            } 
            else if (campos[COLUNA_MODELO].equals(BicicletaEletrica.MODELO)) {
                int bateriaBicicleta = Integer.parseInt(campos[COLUNA_BATERIA]);
                veiculo = BicicletaEletrica.carregarBicicletaEletrica(codigo, bateriaBicicleta, estacao, estadoAtual);
            }
            else if (campos[COLUNA_MODELO].equals(PatineteEletrico.MODELO)) {
                int bateriaPatinete = Integer.parseInt(campos[COLUNA_BATERIA]);
                veiculo = PatineteEletrico.carregarPatineteEletrico(codigo, bateriaPatinete, estacao, estadoAtual);
            }
            else {
                throw new IOException("Tipo de veículo inválido: " + campos[COLUNA_MODELO]);
            }

            if (estacao != null) {
                estacao.adicionarVeiculo(veiculo);
            }

            return veiculo;

        } catch (NumberFormatException e) {
            throw new IOException("Código, estado, estação ou bateria inválido", e);
        } catch (IllegalArgumentException e) {
            throw new IOException("Dados do veículo inválidos", e);
        } catch (Exception e) {
            throw new IOException("Não foi possível carregar o veículo do Banco de dados devido a um erro: ", e);
        }
    }

    @Override
    public String toCsv(Veiculo entidade) {
        validarVeiculo(entidade);

        String[] campos = new String[QUANTIDADE_COLUNAS];
        campos[COLUNA_CODIGO] = String.valueOf(entidade.getCodigo());
        campos[COLUNA_MODELO] = CsvHelper.stringParaCsv(entidade.getModelo());
        campos[COLUNA_ESTADO_ATUAL] = String.valueOf(entidade.getEstadoAtual().getEstadoAtual());
        campos[COLUNA_VALOR_DESBLOQUEIO] = String.valueOf(entidade.getValorDesbloqueio());
        campos[COLUNA_VALOR_POR_MINUTO] = String.valueOf(entidade.getValorPorMinuto());
        if (entidade.getEstacao() == null) {
            campos[COLUNA_ESTACAO] = "";
        } else {
            campos[COLUNA_ESTACAO] = String.valueOf(entidade.getEstacao().getCodigo());
        }

        if (entidade instanceof VeiculoEletrico) {
            VeiculoEletrico eletrico = (VeiculoEletrico) entidade;
            campos[COLUNA_BATERIA] = String.valueOf(eletrico.getBateria());
            campos[COLUNA_CONSUMO_POR_MINUTO] = String.valueOf(eletrico.getConsumoPorMinuto());
        } 
        else {
            campos[COLUNA_BATERIA] = "";
            campos[COLUNA_CONSUMO_POR_MINUTO] = "";
        }

        String linha = campos[COLUNA_CODIGO];
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
