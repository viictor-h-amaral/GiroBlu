package exceptions;

public class ValorRecargaInvalidoException extends GiroBluException {
    public ValorRecargaInvalidoException() {
        super("O valor da recarga deve ser positivo.");
    }
}
