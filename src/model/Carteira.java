package model;

import exceptions.ValorRecargaInvalidoException;

public class Carteira {

    public Carteira(int codigo, double saldo) {
        validarCriacaoCarteira(codigo, saldo);
        this.codigo = codigo;
        this.saldo = saldo;
    }

    private void validarCriacaoCarteira(int codigo, double saldo) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("Código inválido. Deve ser maior que zero.");
        }
    }

    private int codigo;
    public int getCodigo() {
        return codigo;
    }

    private double saldo;
    public double getSaldo() {
        return saldo;
    }

    public void realizarRecarga(double valor) throws ValorRecargaInvalidoException {
        if (valor <= 0) {
            throw new ValorRecargaInvalidoException();
        }
        saldo += valor;
        System.out.println("Recarga de " + valor + " realizada com sucesso! Data: " + java.time.LocalDateTime.now());
    }

    public void realizarPagamento(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do pagamento deve ser positivo.");
        }
        saldo -= valor;
        System.out.println("Pagamento de " + valor + " realizado com sucesso! Data: " + java.time.LocalDateTime.now());
    }
}
