package model;

import contracts.Salvavel;
import contracts.Tarifavel;

public abstract class Plano extends Salvavel implements Tarifavel {

    protected Plano(String nome){
        super();
        setNome(nome);
    }

    protected Plano(int codigo, String nome){
        super(codigo);
        setNome(nome);
    }

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido. Não pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    public abstract double calcularMensalidade(); 
}
