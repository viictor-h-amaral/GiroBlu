package exceptions;

public class VeiculoIndisponivelException extends ProblemaViagemException {
    public VeiculoIndisponivelException() {
        super("O veículo não está disponível para iniciar uma viagem.");
    }
}
