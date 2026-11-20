import model.Estacao;
import repository.repositories.EstacaoRepository;

public class Main {
    public static void main(String[] args) throws Exception {
        var repoEstacao = new EstacaoRepository();
        var estacaoParaAtualizar = repoEstacao.selectOne(1);
        estacaoParaAtualizar.setNome("Fortaleza updated");
        repoEstacao.update(estacaoParaAtualizar);
        var x = repoEstacao.selectAll();

        for (Estacao ex : x) {
            System.out.println(
                ex.getCodigo() + " , " +
                ex.getNome() + " , " +
                ex.getLimiteVagas() + " , " +
                ex.getEstado() +" , " +
                ex.getCidade() +" , " +
                ex.getLogradouro() + " , " +
                ex.getNumero()
            );
        }
    }
}
