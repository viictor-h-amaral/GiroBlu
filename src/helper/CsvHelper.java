package helper;

public class CsvHelper {

    public static String stringParaCsv(String valor) {
        if (valor == null) {
            return "";
        }
        return valor;
    }

    public static boolean temCaractereNaoSuportado(String valor) {
        if (valor == null) {
            return false;
        }
        return valor.contains(";") || valor.contains("\"")
                || valor.contains("\n") || valor.contains("\r");
    }
}
