package model;

import java.util.ArrayList;
import java.util.List;

import contracts.Manutensivel;
import contracts.Salvavel;
import exceptions.EstacaoLotadaException;
import exceptions.VeiculoEmUsoException;

public class Estacao extends Salvavel {

    private String nome;
    private int limiteVagas;
    private List<Veiculo> veiculos = new ArrayList<>();
    private Endereco endereco;

    public static Estacao criarEstacao(String nome, int limiteVagas, String estado, String cidade, String logradouro, int numero){
        return new Estacao(nome, limiteVagas, estado, cidade, logradouro, numero);
    } 

    public static Estacao carregarEstacao(int codigo, String nome, int limiteVagas, String estado, String cidade, String logradouro, int numero){
        return new Estacao(codigo, nome, limiteVagas, estado, cidade, logradouro, numero);
    }

    private Estacao(String nome, int limiteVagas, String estado, String cidade, String logradouro, int numero){
        super();
        this.endereco = Endereco.criarEndereco(estado, cidade, logradouro, numero);
        setNome(nome);
        setLimiteVagas(limiteVagas);
    }

    private Estacao(int codigo, String nome, int limiteVagas, String estado, String cidade, String logradouro, int numero){
        super(codigo);
        this.endereco = Endereco.carregarEndereco(codigo, estado, cidade, logradouro, numero);
        setNome(nome);
        setLimiteVagas(limiteVagas);
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome inválido. Não pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    public int getLimiteVagas(){
        return this.limiteVagas;
    }

    public void setLimiteVagas(int limiteVagas){
        if(limiteVagas <= 0){
            throw new IllegalArgumentException("Limite de Vagas inválido. Deve ser maior que zero.");
        }
        this.limiteVagas = limiteVagas;
    }

    public List<Veiculo> getVeiculos(){
        return veiculos;
    }
    
    public String getEstado(){
        return endereco.getEstado();
    }

    public void setEstado(String estado){
        endereco.setEstado(estado);
    }

    public String getCidade(){
        return endereco.getCidade();
    }

    public void setCidade(String cidade){
        endereco.setCidade(cidade);
    }

    public String getLogradouro(){
        return endereco.getLogradouro();
    }

    public void setLogradouro(String logradouro){
        endereco.setLogradouro(logradouro);
    }

    public int getNumero(){
        return endereco.getNumero();
    }

    public void setNumero(int numero){
        endereco.setNumero(numero);
    }

    public void adicionarVeiculo(Veiculo veiculo) throws EstacaoLotadaException{
        if(veiculos.size() >= getLimiteVagas()){
                throw new EstacaoLotadaException(getNome(), getLimiteVagas());
        }
        veiculos.add(veiculo);
    }

    public void retirarVeiculo(Veiculo veiculo){
        if(!veiculos.contains(veiculo)){
            throw new IllegalStateException("O veículo não existe na estação " + getNome());
        }
        veiculos.remove(veiculo);
    }

    public void enviarVeiculosParaManutencao(ArrayList<Manutensivel> manutensiveis) throws VeiculoEmUsoException {
        ArrayList<String> erros = new ArrayList<>();

        for (Manutensivel manutensivel : manutensiveis) {
            try {
                manutensivel.enviarParaManutencao();
            } catch (Exception e) {
                if(manutensivel instanceof Veiculo){
                    Veiculo veiculo = (Veiculo) manutensivel;
                    erros.add(("\n modelo: " + veiculo.getModelo()
                    + ", codigo: " + veiculo.getCodigo()));    
                } 
            }
        }

        if (!erros.isEmpty()) {
            throw new VeiculoEmUsoException("Os seguintes veículos não podem ser enviados para manuteção: " + erros);
        }
    }
}
