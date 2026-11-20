package model.Planos;

import model.PatineteEletrico;
import model.Veiculo;

public class PlanoUniversitario extends PlanoGratuito {

    public static PlanoUniversitario criarPlanoUniversitario (String instituicao, int matricula){
        return new PlanoUniversitario(instituicao, matricula);
    }

    public static PlanoUniversitario carregarPlanoUniversitario (int codigo, String instituicao, int matricula){
        return new PlanoUniversitario(codigo, instituicao, matricula);
    }

    private PlanoUniversitario(String instituicao, int matricula) {
        super("Plano Universitário");
        this.instituicao = instituicao;
        this.matricula = matricula;
    }

    private PlanoUniversitario(int codigo, String instituicao, int matricula) {
        super(codigo, "Plano Universitário");
        this.instituicao = instituicao;
        this.matricula = matricula;
    }

    private String instituicao;

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao){
        if (instituicao == null || instituicao.isBlank()) {
            throw new IllegalArgumentException("Instituição inválida. Não pode ser nula ou vazia.");
        }
        this.instituicao = instituicao;
    }

    private int matricula;

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula){
        if (matricula <= 0) {
            throw new IllegalArgumentException("Matrícula inválida. Deve ser maior que zero.");
        }
        this.matricula = matricula;
    }

    @Override
    public double calcularTarifa(int minutos, Veiculo veiculo) {
        double desconto = 0.5;
        if (veiculo instanceof PatineteEletrico) {
            desconto = 0;
        }
        return veiculo.calcularValorTotal(minutos) * (1 - desconto);
    }
}
