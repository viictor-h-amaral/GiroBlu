package mapper;

import java.io.IOException;

import contracts.Salvavel;

public interface CsvMapper<T extends Salvavel> {

    String getCabecalho();

    T fromCsv(String linha) throws IOException;

    String toCsv(T entidade);
}
