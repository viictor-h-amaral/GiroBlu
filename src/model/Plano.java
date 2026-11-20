package model;

import contracts.Salvavel;
import contracts.Tarifavel;

public abstract class Plano extends Salvavel implements Tarifavel {

    protected Plano(String nome){
        super();
        validarCriacaoPlano(nome);
        this.nome = nome;
    }

    protected Plano(int codigo, String nome){
        super(codigo);
        validarCriacaoPlano(nome);
        this.nome = nome;
    }

    private void validarCriacaoPlano(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido. Não pode ser nulo ou vazio.");
        }
    }

    private String nome;
    public String getNome() {
        return nome;
    }

    public abstract double calcularMensalidade(); 
}
