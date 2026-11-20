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
        setEstado(estado);
        setCidade(cidade);
        setLogradouro(logradouro);
        setNumero(numero);
    }

    private Endereco(int codigo, String estado, String cidade, String logradouro, int numero){
        super(codigo);
        setEstado(estado);
        setCidade(cidade);
        setLogradouro(logradouro);
        setNumero(numero);
    }

    public String getEstado(){
        return this.estado;
    }

    public void setEstado(String estado){
        if(estado == null || estado.isBlank()){
            throw new IllegalArgumentException("Estado inválido. Não pode ser nulo ou vazio.");
        }
        this.estado = estado;
    }

    public String getCidade(){
        return this.cidade;
    }

    public void setCidade(String cidade){
        if(cidade == null || cidade.isBlank()){
            throw new IllegalArgumentException("Cidade inválido. Não pode ser nulo ou vazio.");
        }
        this.cidade = cidade;
    }

    public String getLogradouro(){
        return this.logradouro;
    }

    public void setLogradouro(String logradouro){
        if(logradouro == null || logradouro.isBlank()){
            throw new IllegalArgumentException("Logradouro inválido. Não pode ser nulo ou vazio.");
        }
        this.logradouro = logradouro;
    }

    public int getNumero(){
        return this.numero;
    }

    public void setNumero(int numero){
        if(numero <= 0){
            throw new IllegalArgumentException("Número inválido. Deve ser maior que zero.");
        }
        this.numero = numero;
    }
}
