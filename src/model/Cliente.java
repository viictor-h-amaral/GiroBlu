package model;

import exceptions.SaldoInsuficienteException;
import exceptions.ViagemEmAndamentoException;

public class Cliente extends Usuario {

    public static String TIPO = "CLIENTE";

    public static Cliente criarCliente(String nome, String email, Plano plano, String cpf) {
        return new Cliente(nome, email, plano, false, cpf);
    }

    public static Cliente carregarCliente(int codigo, String nome, String email, Plano plano, boolean emViagem, String cpf, Carteira carteira) {
        return new Cliente(codigo, nome, email, plano, emViagem, cpf, carteira);
    }

    private Cliente(String nome, String email, Plano plano, boolean emViagem, String cpf) {
        super(nome, email);
        atualizarPlano(plano);
        this.carteira = Carteira.criarCarteira();
        setCpf(cpf);
        this.emViagem = emViagem;
    }

    private Cliente(int codigo, String nome, String email, Plano plano, boolean emViagem, String cpf, Carteira carteira) {
        super(codigo, nome, email);
        atualizarPlano(plano);
        setCarteira(carteira);
        setCpf(cpf);
        this.emViagem = emViagem;
    }

    private Carteira carteira;

    public Carteira getCarteira() {
        return carteira;
    }

    private void setCarteira(Carteira carteira){
        if(carteira == null || carteira.getCodigo() == null){
            throw new IllegalArgumentException("Nova carteira inválida. Não pode ser nula.");
        }
        this.carteira = carteira;
    }

    private Plano plano;

    public Plano getPlano() {
        return plano;
    }

    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf){
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF inválido. Não pode ser nulo ou vazio.");
        }
        this.cpf = cpf;
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
        if (novoPlano == null || novoPlano.getCodigo() == null) {
            throw new IllegalArgumentException("Novo plano inválido. Não pode ser nulo.");
        }
        else if (novoPlano.equals(plano)) {
            throw new IllegalArgumentException("Novo plano inválido. Plano solicitado é o plano atual.");
        }
        this.plano = novoPlano;
    }
}
