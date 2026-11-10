package auxiliares;

public enum EstadoAtualVeiculo {

    DISPONIVEL(1),
    EM_MANUTENCAO(2),
    EM_VIAGEM(3);


    private int estadoAtual;

    EstadoAtualVeiculo(int estadoAtual){
        if(estadoAtual <= 0){
            throw new IllegalArgumentException("O estado atual deve ser maior que 0.");
        }
        this.estadoAtual = estadoAtual;
    }

    public int getEstadoAtual(){
        return this.estadoAtual;
    }

    public void setEstadoAtual(int estadoAtual){
        this.estadoAtual = estadoAtual;
    }
}
