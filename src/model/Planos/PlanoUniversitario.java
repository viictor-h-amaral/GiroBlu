package model.Planos;

public class PlanoUniversitario extends PlanoGratuito {

    public PlanoUniversitario(int codigo, String instituicao, int matricula) {
        super(codigo, "Plano Universitário");
        ValidarCriacaoPlanoUniversitario(instituicao, matricula);

        this.instituicao = instituicao;
        this.matricula = matricula;
    }

    private void ValidarCriacaoPlanoUniversitario(String instituicao, int matricula) {
        if (instituicao == null || instituicao.isBlank()) {
            throw new IllegalArgumentException("Instituição inválida. Não pode ser nula ou vazia.");
        }
        if (matricula <= 0) {
            throw new IllegalArgumentException("Matrícula inválida. Deve ser maior que zero.");
        }
    }

    private String instituicao;
    public String getInstituicao() {
        return instituicao;
    }

    private int matricula;
    public int getMatricula() {
        return matricula;
    }
}
