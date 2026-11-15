package exceptions;

public class VeiculoNaoEmManutencaoException extends GiroBluException{
    public VeiculoNaoEmManutencaoException(){
        super("O veículo não está em uma manutenção para retornar da manutenção.");
    }
}
