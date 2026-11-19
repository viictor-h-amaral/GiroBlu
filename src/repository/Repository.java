package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import db_connector.CsvConnector;
import helper.ReaderHelper;
import helper.WriterHelper;
import mapper.CsvMapper;
import contracts.Salvavel;

public abstract class Repository<T extends Salvavel> {

    protected File arquivo;
    protected CsvMapper<T> mapeador;

    public Repository(CsvMapper<T> mapeador, String nomeArquivo) throws IOException {
        this.mapeador = mapeador;
        CsvConnector conector = new CsvConnector(nomeArquivo, mapeador);
        arquivo = conector.getArquivo();
    }

    public T selectOne(int id) throws IOException{
        List<T> entity = selectAll();
        for (T e : entity) {
            if (e.getCodigo() == id) {
                return e;
            }
        }
        return null;
    };

    public List<T> selectMany(String campo, String valor) throws IOException {
        if (campo == null || valor == null) {
            throw new IllegalArgumentException("Valor procurado e campo a ser filtrado são obrigatórios!");
        }

        List<T> result = new ArrayList<>();
        for (T entidade : selectAll()) {
            String valorCampoFiltrado = obterValorCampo(entidade, campo);
            if (valor.equals(valorCampoFiltrado)) {
                result.add(entidade);
            }
        }
        return result;
    }

    protected String obterValorCampo(T entidade, String campo){
        switch (campo) {
            case "id":
                return String.valueOf(entidade.getCodigo());
            default:
                throw new IllegalArgumentException("Campo informado é inválido! Informado: " + campo);
        }
    }

    public List<T> selectAll() throws IOException {
        List<T> entidades = new ArrayList<T>();

        try (BufferedReader leitor = ReaderHelper.criarLeitor(arquivo)) {
            String cabecalho = leitor.readLine();
            if (!this.mapeador.getCabecalho().equals(cabecalho)) {
                throw new IOException("Cabeçalho CSV inválido! Esperado: " + this.mapeador.getCabecalho() + ", recebido: " + cabecalho);
            }

            String linha;
            int numeroLinha = 1;
            while ((linha = leitor.readLine()) != null) {
                numeroLinha++;
                try {
                    T entidadeLinha = mapeador.fromCsv(linha);
                    entidades.add(entidadeLinha);
                } catch (IOException e) {
                    throw new IOException("Registro inválido na linha " + numeroLinha, e);
                }
            }
        }

        return entidades;
    }

    public void insert(T entidade) throws IOException {
        if (entidade == null) {
            throw new IllegalArgumentException("O registro não pode ser nulo");
        }

        List<T> entidades = selectAll();
        int maiorId = 0;
        for (T e : entidades) {
            if (e.getCodigo() > maiorId) {
                maiorId = e.getCodigo();
            }
        }

        entidade.setCodigo(maiorId + 1);

        entidades.add(entidade);
        gravarArquivo(entidades);
    }

    public void update(T entidade) throws IOException{
        if (entidade == null) {
            throw new IllegalArgumentException("O registro não pode ser nulo");
        }

        List<T> entidades = selectAll();
        boolean encontrado = false;
        for (int i = 0; i < entidades.size(); i++) {
            if (entidades.get(i).getCodigo() == entidade.getCodigo()) {
                entidades.set(i, entidade);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new IOException("Registro não encontrado: " + entidade.getCodigo());
        }
        gravarArquivo(entidades);
    }

    public void delete(int id) throws IOException {
        List<T> entidades = selectAll();
        boolean removido = false;
        for (int i = 0; i < entidades.size(); i++) {
            if (entidades.get(i).getCodigo() == id) {
                entidades.remove(i);
                removido = true;
                break;
            }
        }

        if (!removido) {
            throw new IOException("Registro não encontrado: " + id);
        }
        gravarArquivo(entidades);
    }

    private void gravarArquivo(List<T> entidades) throws IOException {
        List<String> linhas = new ArrayList<String>();
        for (T entidade : entidades) {
            linhas.add(mapeador.toCsv(entidade));
        }

        try (BufferedWriter escritor = WriterHelper.criarEscritor(arquivo)) {
            escritor.write(mapeador.getCabecalho());
            escritor.newLine();

            for (String linha : linhas) {
                escritor.write(linha);
                escritor.newLine();
            }
        }
    }
}
