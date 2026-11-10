package exceptions;

public class ViagemEmAndamentoException extends ProblemaViagemException {
    public ViagemEmAndamentoException() {
        super("O usuário já possui uma viagem em andamento.");
    }
}
