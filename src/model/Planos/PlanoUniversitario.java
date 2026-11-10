package model.Planos;

import model.PatineteEletrico;
import model.Veiculo;

public class PlanoUniversitario extends PlanoGratuito {

    public PlanoUniversitario(int codigo, String instituicao, int matricula) {
        super(codigo, "Plano Universitário");
        validarCriacaoPlanoUniversitario(instituicao, matricula);

        this.instituicao = instituicao;
        this.matricula = matricula;
    }

    private void validarCriacaoPlanoUniversitario(String instituicao, int matricula) {
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

    @Override
    public double calcularTarifa(int minutos, Veiculo veiculo) {
        double desconto = 0.5;
        if (veiculo instanceof PatineteEletrico) {
            desconto = 0;
        }
        return veiculo.calcularValorTotal(minutos) * (1 - desconto);
    }
}
