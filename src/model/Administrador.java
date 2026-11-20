package model;

public class Administrador extends Usuario {

    public static Administrador criarAdministrador(String nome, String email, int codigoAcesso){
        return new Administrador(nome, email, codigoAcesso);
    }

    public static Administrador carregarAdministrador(int codigo, String nome, String email, int codigoAcesso){
        return new Administrador(codigo, nome, email, codigoAcesso);
    }

    private Administrador(String nome, String email, int codigoAcesso) {
        super(nome, email);
        validarCriacaoAdministrador(codigoAcesso);
        this.codigoAcesso = codigoAcesso;
    }

    private Administrador(int codigo, String nome, String email, int codigoAcesso) {
        super(codigo, nome, email);
        validarCriacaoAdministrador(codigoAcesso);
        this.codigoAcesso = codigoAcesso;
    }

    private void validarCriacaoAdministrador(int codigoAcesso) {
        if (codigoAcesso <= 0) {
            throw new IllegalArgumentException("Código de acesso inválido. Deve ser maior que zero.");
        }
    }

    private int codigoAcesso;
    public int getCodigoAcesso() {
        return codigoAcesso;
    }
}
