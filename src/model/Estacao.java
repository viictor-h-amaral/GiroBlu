package model;

import java.util.ArrayList;

import contracts.Manutensivel;
import contracts.Salvavel;
import exceptions.EstacaoLotadaException;
import exceptions.VeiculoEmUsoException;

public class Estacao extends Salvavel {

    private String nome;
    private int limiteVagas;
    private ArrayList<Veiculo> veiculos = new ArrayList<>();
    private Endereco endereco;

    public static Estacao criarEstacao(String nome, int limiteVagas, String estado, String cidade, String logradouro, int numero){
        return new Estacao(nome, limiteVagas, estado, cidade, logradouro, numero);
    } 

    public static Estacao carregarEstacao(int codigo, String nome, int limiteVagas, String estado, String cidade, String logradouro, int numero){
        return new Estacao(codigo, nome, limiteVagas, estado, cidade, logradouro, numero);
    }

    private Estacao(String nome, int limiteVagas, String estado, String cidade, String logradouro, int numero){
        super();
        validarCriacaoEstacao(nome, limiteVagas);
        this.endereco = Endereco.criarEndereco(estado, cidade, logradouro, numero);
        this.nome = nome;
        this.limiteVagas = limiteVagas;
    }

    private Estacao(int codigo, String nome, int limiteVagas, String estado, String cidade, String logradouro, int numero){
        super(codigo);
        validarCriacaoEstacao(nome, limiteVagas);
        this.endereco = Endereco.carregarEndereco(codigo, estado, cidade, logradouro, numero);
        this.nome = nome;
        this.limiteVagas = limiteVagas;
    }

    private void validarCriacaoEstacao(String nome, int limiteVagas){
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome inválido. Não pode ser nulo ou vazio.");
        }
        else if(limiteVagas <= 0){
            throw new IllegalArgumentException("Limite de Vagas inválido. Deve ser maior que zero.");
        }
    }

    public String getNome(){
        return this.nome;
    }

    public int getLimiteVagas(){
        return this.limiteVagas;
    }

    public ArrayList<Veiculo> getVeiculos(){
        return veiculos;
    }
    
    public String getEstado(){
        return endereco.getEstado();
    }

    public String getCidade(){
        return endereco.getCidade();
    }

    public String getLogradouro(){
        return endereco.getLogradouro();
    }

    public int getNumero(){
        return endereco.getNumero();
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
