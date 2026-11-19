package mapper;

import java.io.IOException;

public interface CsvMapper<T> {

    String getCabecalho();

    T fromCsv(String linha) throws IOException;

    String toCsv(T entidade);
}
