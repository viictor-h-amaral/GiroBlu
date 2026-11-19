package model;

import contracts.Salvavel;
import exceptions.ValorRecargaInvalidoException;

public class Carteira extends Salvavel{

    public static Carteira carregarCarteira(int codigo, double saldo) {
        return new Carteira(codigo, saldo);
    }

    public static Carteira criarCarteira() {
        return new Carteira();
    }

    private Carteira(int codigo, double saldo) {
        super(codigo);
        setSaldo(saldo);
    }

    private Carteira(){
        super();
        setSaldo(0);
    }

    private double saldo;
    public double getSaldo() {
        return saldo;
    }

    private void setSaldo(double saldo) {
        this.saldo = saldo;
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
