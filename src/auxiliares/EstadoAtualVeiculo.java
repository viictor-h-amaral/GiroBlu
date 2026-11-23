package auxiliares;

public enum EstadoAtualVeiculo {

    DISPONIVEL(1),
    EM_MANUTENCAO(2),
    EM_VIAGEM(3);


    private final int numero;

    EstadoAtualVeiculo(int numero){
        if(numero <= 0 || numero > 3){
            throw new IllegalArgumentException("O estado atual deve ser 1 (disponível), 2 (manutenção) ou 3 (em viagem).");
        }
        this.numero = numero;
    }

    public int getNumero() {
        return this.numero;
    }

    public static EstadoAtualVeiculo intParaEnum(int numero) {
        EstadoAtualVeiculo[] opcoesEnum = values();
        for (EstadoAtualVeiculo estado : opcoesEnum) {
            if (numero == estado.getNumero()) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Tipo de operação inexistente: " + numero);
    }
}
