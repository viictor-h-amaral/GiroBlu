package model;

import contracts.Salvavel;

public abstract class Usuario extends Salvavel {

    public static String obterTipo(Usuario usuario){
        if(usuario instanceof Cliente){
            return Cliente.TIPO;
        }
        else if(usuario instanceof Administrador){
            return Administrador.TIPO;
        }
        throw new IllegalArgumentException("Tipo de usuário não encontrado.");
    }

    protected Usuario(String nome, String email){
        super();
        setNome(nome);
        setEmail(email);
    }

    protected Usuario(int codigo, String nome, String email) {
        super(codigo);
        setNome(nome);
        setEmail(email);
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

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email inválido. Não pode ser nulo ou vazio.");
        }
        else if (!email.contains("@")) {
            throw new IllegalArgumentException("Email inválido. Email deve conter '@'.");
        }
        this.email = email;
    }
}
