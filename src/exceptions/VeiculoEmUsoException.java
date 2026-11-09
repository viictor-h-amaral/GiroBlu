package exceptions;

public class VeiculoEmUsoException extends ProblemaViagemException {
    public VeiculoEmUsoException() {
        super("O veículo não pode ser enviado para manutenção nem recarregado enquanto estiver em uso.");
    }
}
