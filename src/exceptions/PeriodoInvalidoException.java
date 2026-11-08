package exceptions;

public class PeriodoInvalidoException extends GiroBluException {
    public PeriodoInvalidoException() {
        super("A data de término deve ser posterior à data de início.");
    }
}
