package auxiliares;

public enum TipoOperacaoCarteira {

    CREDITO(1),
    DEBITO(2);

    private final int numero;

    TipoOperacaoCarteira(int numero){
        if(numero <= 0 || numero > 2){
            throw new IllegalArgumentException("Tipo de operação deve ser 1 (crédito) ou 2 (débito).");
        }
        this.numero = numero;
    }

    public int getNumero() {
        return this.numero;
    }

    public static TipoOperacaoCarteira intParaEnum(int numero) {
        TipoOperacaoCarteira[] opcoesEnum = values();
        for (TipoOperacaoCarteira tipoOperacao : opcoesEnum) {
            if (numero == tipoOperacao.getNumero()) {
                return tipoOperacao;
            }
        }
        throw new IllegalArgumentException("Tipo de operação inexistente: " + numero);
    }
}
