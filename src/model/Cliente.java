package model;

import exceptions.SaldoInsuficienteException;
import exceptions.ViagemEmAndamentoException;

public class Cliente extends Usuario {

    public Cliente(int codigo, String nome, String email, Plano plano, boolean emViagem, String cpf) {
        super(codigo, nome, email);
        validarCriacaoCliente(plano, cpf);

        this.plano = plano;
        this.carteira = new Carteira(codigo, 0.0);
        this.cpf = cpf;
        this.emViagem = emViagem;
    }

    private void validarCriacaoCliente(Plano plano, String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF inválido. Não pode ser nulo ou vazio.");
        }
        else if (plano == null){
            throw new IllegalArgumentException("Plano inválido. Não pode ser nulo.");
        }
    }

    private Carteira carteira;
    public Carteira getCarteira() {
        return carteira;
    }

    private Plano plano;
    public Plano getPlano() {
        return plano;
    }

    private String cpf;
    public String getCpf() {
        return cpf;
    }

    private boolean emViagem;
    public boolean isEmViagem() {
        return emViagem;
    }

    public void validarLiberarParaViagem() throws ViagemEmAndamentoException, SaldoInsuficienteException {
        if (emViagem) {
            throw new ViagemEmAndamentoException();
        }
        else if (carteira.getSaldo() < 5.0) {
            throw new SaldoInsuficienteException();
        }
    }

    public void liberarParaViagem() throws ViagemEmAndamentoException, SaldoInsuficienteException {
        validarLiberarParaViagem();
        emViagem = true;
    }

    public void validarFinalizarViagem() {
        if (!emViagem) {
            throw new IllegalStateException("O cliente não está em viagem.");
        }
    }

    public void finalizarViagem() {
        validarFinalizarViagem();
        emViagem = false;
    }

    public void atualizarPlano(Plano novoPlano) {
        if (novoPlano == null) {
            throw new IllegalArgumentException("Novo plano inválido. Não pode ser nulo.");
        }
        else if (novoPlano.equals(plano)) {
            throw new IllegalArgumentException("Novo plano inválido. Plano solicitado é o plano atual.");
        }
        this.plano = novoPlano;
    }
}
