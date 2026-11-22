package model;

import auxiliares.EstadoAtualVeiculo;
import exceptions.BateriaInsuficienteException;
import exceptions.EstacaoLotadaException;
import exceptions.ProblemaViagemException;
import exceptions.VeiculoEmUsoException;

public abstract class VeiculoEletrico extends Veiculo{

    private int bateria;
    private int consumoPorMinuto;

    protected VeiculoEletrico(String modelo, double valorDesbloqueio, double valorPorMinuto, int bateria, int consumoPorMinuto, Estacao estacao) throws EstacaoLotadaException{
        super(modelo, valorDesbloqueio, valorPorMinuto, estacao);
        setBateria(bateria);
        setConsumoPorMinuto(consumoPorMinuto);
    }

    protected VeiculoEletrico(int codigo, String modelo, double valorDesbloqueio, double valorPorMinuto, int bateria, int consumoPorMinuto, Estacao estacao, EstadoAtualVeiculo estadoAtual){
        super(codigo, modelo, valorDesbloqueio, valorPorMinuto, estacao, estadoAtual);
        setBateria(bateria);
        setConsumoPorMinuto(consumoPorMinuto);
    }

    public int getBateria(){
        return this.bateria;
    }

    protected void setBateria(int bateria){
        if(bateria < 0 || bateria > 100){
            throw new IllegalArgumentException("A bateria deve estar entre 0 e 100.");
        }
        this.bateria = bateria;
    }

    public void recarregarBateria(Administrador administrador) throws VeiculoEmUsoException{
        enviarParaManutencao();
        setBateria(100);
    }


    public int getConsumoPorMinuto(){
        return this.consumoPorMinuto;
    }

    protected void setConsumoPorMinuto(int consumoPorMinuto){
        if(consumoPorMinuto <= 0){
            throw new IllegalArgumentException("O consumo por minuto deve ser maior que 0.");
        }
        this.consumoPorMinuto = consumoPorMinuto;
    }

    @Override 
    public void validarLiberarParaViagem() throws ProblemaViagemException{
        super.validarLiberarParaViagem();
        if(getBateria() < 20){
            throw new BateriaInsuficienteException();
        }
    }

    public void calcularBateria(int minutos){
        if (minutos < 0) {
            throw new IllegalArgumentException("Tempo de uso inválido. Deve ser maior ou igual a zero.");
        }
        int novaBateria = getBateria() - (minutos * getConsumoPorMinuto()) < 0 ?
                            0 : getBateria() - (minutos * getConsumoPorMinuto());
        setBateria(novaBateria);
    }
}
