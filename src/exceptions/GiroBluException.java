package exceptions;

public class GiroBluException extends Exception {
    public GiroBluException(String mensagem) {
        super(mensagem);
    }

    public GiroBluException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
