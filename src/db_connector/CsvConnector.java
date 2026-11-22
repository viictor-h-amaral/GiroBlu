package db_connector;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import helper.WriterHelper;
import mapper.CsvMapper;

public class CsvConnector {

    private File arquivo;
    private String cabecalho;

    public CsvConnector(String nomeArquivo, CsvMapper<?> mapeador) throws IOException {
        if (nomeArquivo == null || nomeArquivo.isEmpty()) {
            throw new IllegalArgumentException("Erro ao processar nome do arquivo vazio");
        }
        if (mapeador == null || mapeador.getCabecalho() == null
                || mapeador.getCabecalho().isEmpty()) {
            throw new IllegalArgumentException("Erro ao processar cabeçalho vazio");
        }

        cabecalho = mapeador.getCabecalho();
        File pasta = new File("database");
        if (!pasta.exists() && !pasta.mkdirs()) {
            throw new IOException("Não foi possível criar a pasta database/");
        }

        arquivo = new File(pasta, nomeArquivo);

        if (arquivo.exists()) {
            if (!arquivo.isFile()) {
                throw new IOException("O caminho do CSV não é um arquivo: " + arquivo);
            }
            return;
        }

        if (!arquivo.createNewFile()) {
            throw new IOException("Arquivo " + arquivo + " não existe e não foi possível criá-lo");
        }

        BufferedWriter escritor = WriterHelper.criarEscritor(arquivo);
        try {
            escritor.write(cabecalho);
            escritor.newLine();
        } finally {
            escritor.close();
        }
    }

    public File getArquivo() {
        return arquivo;
    }

    public String getCabecalho() {
        return cabecalho;
    }
}
