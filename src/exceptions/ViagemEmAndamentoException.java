package exceptions;

public class ViagemEmAndamentoException extends GiroBluException {
    public ViagemEmAndamentoException() {
        super("O usuário já possui uma viagem em andamento.");
    }
}
