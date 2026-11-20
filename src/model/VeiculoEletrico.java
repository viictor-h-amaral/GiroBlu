package model;

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

    protected VeiculoEletrico(int codigo, String modelo, double valorDesbloqueio, double valorPorMinuto, int bateria, int consumoPorMinuto, Estacao estacao){
        super(codigo, modelo, valorDesbloqueio, valorPorMinuto, estacao);
        setBateria(bateria);
        setConsumoPorMinuto(consumoPorMinuto);
    }

    public int getBateria(){
        return this.bateria;
    }

    protected void setBateria(int bateria){
        if(bateria < 0){
            throw new IllegalArgumentException("A bateria não pode ser menor que 0.");
        }
        this.bateria = bateria;
    }

    public void recarregarBateria() throws VeiculoEmUsoException{ //TODO: parâmetros do método (Administrador administrador)
        enviarParaManutencao();
        this.bateria = 100;
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
        this.bateria = getBateria() - (minutos * getConsumoPorMinuto());
        if(getBateria() < 0){
            this.bateria = 0;
        }
    }
}
