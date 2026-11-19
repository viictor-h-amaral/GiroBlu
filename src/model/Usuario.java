package model;

import contracts.Salvavel;

public abstract class Usuario extends Salvavel {

    protected Usuario(String nome, String email){
        super();
        validarCriacaoUsuario(nome, email);
        this.nome = nome;
        this.email = email;
    }

    protected Usuario(int codigo, String nome, String email) {
        super(codigo);
        validarCriacaoUsuario(nome, email);
        this.nome = nome;
        this.email = email;
    }

    private void validarCriacaoUsuario(String nome, String email) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido. Não pode ser nulo ou vazio.");
        }
        else if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email inválido. Não pode ser nulo ou vazio.");
        }
        else if (!email.contains("@")) {
            throw new IllegalArgumentException("Email inválido. Email deve conter '@'.");
        }
    }

    private String nome;
    public String getNome() {
        return nome;
    }

    private String email;
    public String getEmail() {
        return email;
    }
}
