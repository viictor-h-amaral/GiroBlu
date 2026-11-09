package model;

public abstract class Plano {

    public Plano(int codigo, String nome){
        ValidarCriacaoPlano(codigo, nome);
        this.codigo = codigo;
        this.nome = nome;
    }

    private void ValidarCriacaoPlano(int codigo, String nome) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("Código inválido. Deve ser maior que zero.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido. Não pode ser nulo ou vazio.");
        }
    }

    private int codigo;
    public int getCodigo() {
        return codigo;
    }

    private String nome;
    public String getNome() {
        return nome;
    }

    public abstract double calcularMensalidade(); 
}
