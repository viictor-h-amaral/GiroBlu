package exceptions;

public class SaldoInsuficienteException extends GiroBluException {
    public SaldoInsuficienteException() {
        super("É necessário ter saldo mínimo de R$ 5,00 para iniciar uma viagem.");
    }
}
