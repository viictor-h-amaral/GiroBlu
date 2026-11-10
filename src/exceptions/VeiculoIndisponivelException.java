package exceptions;

public class VeiculoIndisponivelException extends GiroBluException {
    public VeiculoIndisponivelException() {
        super("O veículo não está disponível para iniciar uma viagem.");
    }
}
