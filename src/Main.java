import model.Estacao;
import model.PatineteEletrico;

public class Main {
    public static void main(String[] args) throws Exception {
        var e1 = new Estacao(1, "null", 1, "a", "null", "null", 1);
        var v1 = new PatineteEletrico(1, e1);

        System.out.println("Estacao: " + v1.getEstadoAtual());
        System.out.println("Estacao: " + v1.getEstadoAtual().ordinal());
        System.out.println("Estacao: " + v1.getEstadoAtual().name());

        v1.enviarParaManutencao();
        System.out.println("Estacao: " + v1.getEstadoAtual().ordinal());
        v1.retornarManutencao();
        v1.liberarParaViagem();
        System.out.println("Estacao: " + v1.getEstadoAtual().ordinal());

    }
}
