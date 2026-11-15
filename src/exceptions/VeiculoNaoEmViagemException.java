package exceptions;

public class VeiculoNaoEmViagemException extends ProblemaViagemException{

   public VeiculoNaoEmViagemException() {
        super("O veículo não está em uma viagem para retornar a estação.");
    }
}

