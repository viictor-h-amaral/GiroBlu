package exceptions;

public class EstacaoLotadaException extends GiroBluException  {
    public EstacaoLotadaException(String nomeEstacao, int limiteVagas) {
        super("A estação " + nomeEstacao + " está lotada (" + limiteVagas + " vagas).");
    }
}
