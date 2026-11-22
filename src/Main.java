import model.Estacao;

public class Main {
    public static void main(String[] args) throws Exception {
        var estacao = Estacao.criarEstacao("Estação Central", 10, "SP", "São Paulo", "Rua A", 123);

        System.out.println(estacao.getCodigo());

        var repo = repository.repositories.EstacaoRepository.getInstance();
        repo.insert(estacao);

        System.out.println(estacao.getCodigo());
    }
}
