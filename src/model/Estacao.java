package model;

import java.util.ArrayList;

import contracts.Manutensivel;
import exceptions.EstacaoLotadaException;
import exceptions.VeiculoEmUsoException;

public class Estacao {

    private int codigo;
    private String nome;
    private int limiteVagas;
    private ArrayList<Veiculo> veiculos = new ArrayList<>();
    private Endereco endereco;

    public Estacao(int codigo, String nome, int limiteVagas, String estado, String cidade, String logradouro, int numero){
        validarCriacaoEstacao(codigo, nome, limiteVagas);
        this.endereco = new Endereco(codigo, estado, cidade, logradouro, numero);
        this.codigo = codigo;
        this.nome = nome;
        this.limiteVagas = limiteVagas;
    }

    private void validarCriacaoEstacao(int codigo, String nome, int limiteVagas){
        if(codigo <= 0){
            throw new IllegalArgumentException("Código inválido. Deve ser maior que zero.");
        }
        else if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome inválido. Não pode ser nulo ou vazio.");
        }
        else if(limiteVagas <= 0){
            throw new IllegalArgumentException("Limite de Vagas inválido. Deve ser maior que zero.");
        }
    }

    public int getCodigo(){
        return this.codigo;
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
                    erros.add(("modelo: " + veiculo.getModelo()
                    + ", codigo: " + veiculo.getCodigo()));    
                } 
            }
        }

        if (!erros.isEmpty()) {
            throw new VeiculoEmUsoException("O seguintes veículos não podem ser enviados para manuteção: " + erros);
        }
    }
}
