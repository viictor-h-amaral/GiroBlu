package model;

public class Endereco {

    private int codigo;
    private String estado;
    private String cidade;
    private String logradouro;
    private int numero;

    public Endereco(int codigo, String estado, String cidade, String logradouro, int numero){
        validarCriacaoEndereco(codigo, estado, cidade, logradouro, numero);
        
        this.codigo = codigo;
        this.estado = estado;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.numero = numero;
    }

    private void validarCriacaoEndereco(int codigo, String estado, String cidade, String logradouro, int numero){
        if(codigo <= 0){
            throw new IllegalArgumentException("Código inválido. Deve ser maior que zero.");
        }
        if(estado == null || estado.isBlank()){
            throw new IllegalArgumentException("Estado inválido. Não pode ser nulo ou vazio.");
        }
        if(cidade == null || cidade.isBlank()){
            throw new IllegalArgumentException("Cidade inválido. Não pode ser nulo ou vazio.");
        }
        if(logradouro == null || logradouro.isBlank()){
            throw new IllegalArgumentException("Logradouro inválido. Não pode ser nulo ou vazio.");
        }
        if(numero <= 0){
            throw new IllegalArgumentException("Número inválido. Deve ser maior que zero.");
        }
    }

    public int getCodigo(){
        return this.codigo;
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
