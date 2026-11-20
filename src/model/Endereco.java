package model;

import contracts.Salvavel;

public class Endereco extends Salvavel {

    private String estado;
    private String cidade;
    private String logradouro;
    private int numero;

    public static Endereco criarEndereco (String estado, String cidade, String logradouro, int numero){
        return new Endereco(estado, cidade, logradouro, numero);
    }

    public static Endereco carregarEndereco (int codigo, String estado, String cidade, String logradouro, int numero) {
        return new Endereco(codigo, estado, cidade, logradouro, numero);
    }

    private Endereco(String estado, String cidade, String logradouro, int numero){
        super();
        validarCriacaoEndereco(estado, cidade, logradouro, numero);
        this.estado = estado;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.numero = numero;
    }

    private Endereco(int codigo, String estado, String cidade, String logradouro, int numero){
        super(codigo);
        validarCriacaoEndereco(estado, cidade, logradouro, numero);
        this.estado = estado;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.numero = numero;
    }

    private void validarCriacaoEndereco(String estado, String cidade, String logradouro, int numero){
        if(estado == null || estado.isBlank()){
            throw new IllegalArgumentException("Estado inválido. Não pode ser nulo ou vazio.");
        }
        else if(cidade == null || cidade.isBlank()){
            throw new IllegalArgumentException("Cidade inválido. Não pode ser nulo ou vazio.");
        }
        else if(logradouro == null || logradouro.isBlank()){
            throw new IllegalArgumentException("Logradouro inválido. Não pode ser nulo ou vazio.");
        }
        else if(numero <= 0){
            throw new IllegalArgumentException("Número inválido. Deve ser maior que zero.");
        }
    }

    public String getEstado(){
        return this.estado;
    }

    public String getCidade(){
        return this.cidade;
    }

    public String getLogradouro(){
        return this.logradouro;
    }

    public int getNumero(){
        return this.numero;
    }

}
