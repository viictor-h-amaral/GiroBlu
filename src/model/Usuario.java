package model;

public abstract class Usuario {

    public Usuario(int codigo, String nome, String email) {
        validarCriacaoUsuario(codigo, nome, email);
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
    }

    private void validarCriacaoUsuario(int codigo, String nome, String email) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("Código inválido. Deve ser maior que zero.");
        }
        else if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido. Não pode ser nulo ou vazio.");
        }
        else if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email inválido. Não pode ser nulo ou vazio.");
        }
        else if (!email.contains("@")) {
            throw new IllegalArgumentException("Email inválido. Email deve conter '@'.");
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

    private String email;
    public String getEmail() {
        return email;
    }
}
