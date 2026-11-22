package auxiliares;

public enum TipoOperacaoCarteira {

    CREDITO(1),
    DEBITO(2);


    private int tipoOperacaoCarteira;

    TipoOperacaoCarteira(int tipoOperacaoCarteira){
        if(tipoOperacaoCarteira <= 0){
            throw new IllegalArgumentException("Tipo de operação deve ser maior que 0.");
        }
        this.tipoOperacaoCarteira = tipoOperacaoCarteira;
    }

    public int getTipoOperacaoCarteira(){
        return this.tipoOperacaoCarteira;
    }

    public void setTipoOperacaoCarteira(int tipoOperacaoCarteira){
        this.tipoOperacaoCarteira = tipoOperacaoCarteira;
    }
}
