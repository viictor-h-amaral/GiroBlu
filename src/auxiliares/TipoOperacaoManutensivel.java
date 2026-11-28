package auxiliares;

public enum TipoOperacaoManutensivel {

    ENVIAR_MANUTENCAO(1),
    RETORNAR_MANUTENCAO(2),
    CARREGAR_BATERIA(3);

    private final int numero;

    TipoOperacaoManutensivel(int numero){
        if(numero <= 0 || numero > 3){
            throw new IllegalArgumentException("Tipo de operação deve ser 1 (enviar para manutenção), 2 (retornar da manutenção) ou 3 (carregar bateria).");
        }
        this.numero = numero;
    }

    public int getNumero() {
        return this.numero;
    }

    public static TipoOperacaoManutensivel intParaEnum(int numero) {
        TipoOperacaoManutensivel[] opcoesEnum = values();
        for (TipoOperacaoManutensivel tipoOperacao : opcoesEnum) {
            if (numero == tipoOperacao.getNumero()) {
                return tipoOperacao;
            }
        }
        throw new IllegalArgumentException("Tipo de operação inexistente: " + numero);
    }
}
