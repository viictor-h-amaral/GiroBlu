package mapper.mappers;

import java.io.IOException;

import mapper.CsvMapper;
import model.Carteira;

public class CarteiraCsvMapper implements CsvMapper<Carteira>{

    private static final String SEPARADOR = ";";
    private static final int COLUNA_CODIGO = 0;
    private static final int COLUNA_SALDO = 1;
    private static final int QUANTIDADE_COLUNAS = 2;
    private static final String CABECALHO = "codigo;saldo";

    @Override
    public String getCabecalho() {
        return CABECALHO;
    }

    @Override 
    public Carteira fromCsv(String linha) throws IOException{
        if(linha == null){
            throw new IOException("A linha CSV não pode ser nula");
        }

        String[] campos = linha.split(SEPARADOR, -1);
        if (campos.length != QUANTIDADE_COLUNAS) {
            throw new IOException("A linha deve conter exatamente cinco campos");
        }

        try {
            return Carteira.carregarCarteira(
                    Integer.parseInt(campos[COLUNA_CODIGO]),
                    Double.parseDouble(campos[COLUNA_SALDO])
                );
        } catch (Exception e) {
            throw new IOException("Não foi possível carregar a Carteira do Banco de dados devido a: ", e);
        }
    }

    @Override 
    public String toCsv(Carteira entidade){
        validarCarteira(entidade);

        String[] campos = new String[QUANTIDADE_COLUNAS];
        campos[COLUNA_CODIGO] = String.valueOf(entidade.getCodigo());
        campos[COLUNA_SALDO] = String.valueOf(entidade.getSaldo());

        String linha = campos[COLUNA_CODIGO];
        for (int i = 1; i < QUANTIDADE_COLUNAS; i++) {
            linha += SEPARADOR + campos[i];
        }
        return linha;
    }

    private void validarCarteira(Carteira carteira){
        if (carteira == null) {
            throw new IllegalArgumentException("A carteira não pode ser nulo");
        }
    }
}
