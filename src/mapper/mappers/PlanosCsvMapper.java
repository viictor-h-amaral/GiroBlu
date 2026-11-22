package mapper.mappers;

import java.io.IOException;

import helper.CsvHelper;
import mapper.CsvMapper;
import model.Plano;
import model.Veiculo;
import model.Planos.PlanoAvulso;
import model.Planos.PlanoMensal;
import model.Planos.PlanoPago;
import model.Planos.PlanoUniversitario;

public class PlanosCsvMapper implements CsvMapper<Plano> {

    private static final String SEPARADOR = ";";
    private static final int COLUNA_CODIGO = 0;
    private static final int COLUNA_NOME = 1;
    private static final int COLUNA_PRECO_MENSAL = 2;
    private static final int COLUNA_INSTITUICAO = 3;
    private static final int COLUNA_MATRICULA = 4;
    private static final int QUANTIDADE_COLUNAS = 5;
    private static final String CABECALHO = "codigo;nome;precoMensal;instituicao;matricula";

    @Override
    public String getCabecalho() {
        return CABECALHO;
    }

    @Override
    public Plano fromCsv(String linha) throws IOException {
        if (linha == null) {
            throw new IOException("A linha CSV não pode ser nula");
        }

        String[] campos = linha.split(SEPARADOR, -1);
        if (campos.length != QUANTIDADE_COLUNAS) {
            throw new IOException("A linha deve conter exatamente " + QUANTIDADE_COLUNAS + " campos");
        }

        if (CsvHelper.temCaractereNaoSuportado(campos[COLUNA_NOME])) {
            throw new IOException("Campo de texto inválido");
        }

        try {
            int codigo = Integer.parseInt(campos[COLUNA_CODIGO]);

            Plano plano;
            if (campos[COLUNA_NOME].equals(PlanoAvulso.NOME)) {
                plano = PlanoAvulso.carregarPlanoAvulso(codigo);
            } 
            else if (campos[COLUNA_NOME].equals(PlanoMensal.NOME)) {
                double valorMensalidade = Double.parseDouble(campos[COLUNA_PRECO_MENSAL]);
                plano = PlanoMensal.carregarPlanoMensal(codigo, valorMensalidade);
            }
            else if (campos[COLUNA_NOME].equals(PlanoUniversitario.NOME)) {
                String instituicao = campos[COLUNA_INSTITUICAO];
                int matricula = Integer.parseInt(campos[COLUNA_MATRICULA]);
                plano = PlanoUniversitario.carregarPlanoUniversitario(codigo, instituicao, matricula);
            }
            else {
                throw new IOException("Nome do plano inexistente: " + campos[COLUNA_NOME]);
            }

            return plano;
        } catch (NumberFormatException e) {
            throw new IOException("Erro ao converter campos numéricos: " + e.getMessage());
        }
    }

    @Override 
    public String toCsv(Plano entidade) {
        validarPlano(entidade);

        String[] campos = new String[QUANTIDADE_COLUNAS];
        campos[COLUNA_CODIGO] = String.valueOf(entidade.getCodigo());
        campos[COLUNA_NOME] = CsvHelper.stringParaCsv(entidade.getNome());
        campos[COLUNA_PRECO_MENSAL] = "";
        campos[COLUNA_INSTITUICAO] = "";
        campos[COLUNA_MATRICULA] = "";

        if (entidade instanceof PlanoPago){
            var planoPago = (PlanoPago) entidade;
            campos[COLUNA_PRECO_MENSAL] = String.valueOf(planoPago.getPrecoMensal());
        }
        else if (entidade instanceof PlanoUniversitario){
            var planoUniversitario = (PlanoUniversitario) entidade;
            campos[COLUNA_INSTITUICAO] = planoUniversitario.getInstituicao();
            campos[COLUNA_MATRICULA] = String.valueOf(planoUniversitario.getMatricula());
        }

        return String.join(SEPARADOR, campos);
    }

    private void validarPlano(Plano plano) {
        if (plano == null) {
            throw new IllegalArgumentException("O veículo não pode ser nulo");
        }
        else if (CsvHelper.temCaractereNaoSuportado(plano.getNome())) {
            throw new IllegalArgumentException(
                "O campo de texto não pode conter ponto e vírgula (;), aspas (\") ou quebras de linha");
        }
        else if (plano instanceof PlanoUniversitario){
            var universitario = (PlanoUniversitario) plano;
            if (CsvHelper.temCaractereNaoSuportado(universitario.getNome())){
                throw new IllegalArgumentException(
                    "O campo de texto não pode conter ponto e vírgula (;), aspas (\") ou quebras de linha");
        
            }
        }
    }
}
