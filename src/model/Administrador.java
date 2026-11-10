package model;

public class Administrador extends Usuario {

    public Administrador(int codigo, String nome, String email, int codigoAcesso) {
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
