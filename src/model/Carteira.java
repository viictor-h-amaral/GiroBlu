package model;

import java.io.IOException;

import auxiliares.TipoOperacaoCarteira;
import contracts.Salvavel;
import exceptions.ValorRecargaInvalidoException;
import repository.repositories.OperacaoCarteiraRepository;

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

    public void realizarRecarga(double valor) throws ValorRecargaInvalidoException, IOException {
        if (valor <= 0) {
            throw new ValorRecargaInvalidoException();
        }
        saldo += valor;
        var recarga = OperacaoCarteira.criarOperacaoCarteira(this, TipoOperacaoCarteira.CREDITO, valor);
        OperacaoCarteiraRepository.getInstance().insert(recarga);
    }

    public void realizarPagamento(double valor) throws IOException {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do pagamento deve ser positivo.");
        }
        saldo -= valor;
        var pagamento = OperacaoCarteira.criarOperacaoCarteira(this, TipoOperacaoCarteira.DEBITO, valor);
        OperacaoCarteiraRepository.getInstance().insert(pagamento);
    }
}
