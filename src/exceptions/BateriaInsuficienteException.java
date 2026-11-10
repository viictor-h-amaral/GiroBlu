package exceptions;

public class BateriaInsuficienteException extends ProblemaViagemException {
    public BateriaInsuficienteException() {
        super("A bateria do veículo deve ter no mínimo 20% para iniciar uma viagem.");
    }
    
}
