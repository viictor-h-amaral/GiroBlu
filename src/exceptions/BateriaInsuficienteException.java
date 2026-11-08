package exceptions;

public class BateriaInsuficienteException extends GiroBluException {
    public BateriaInsuficienteException() {
        super("A bateria do veículo deve ter no mínimo 20% para iniciar uma viagem.");
    }
}
