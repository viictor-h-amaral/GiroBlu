package model;

import exceptions.BateriaInsuficienteException;
import exceptions.VeiculoEmUsoException;

public abstract class VeiculoEletrico extends Veiculo{

    private int bateria;
    private int consumoPorMinuto;

    public VeiculoEletrico(int codigo, String modelo, double valorDesbloqueio, double valorPorMinuto, int bateria, int consumoPorMinuto){
        super(codigo, modelo, valorDesbloqueio, valorPorMinuto);
        validarCriacaoVeiculoEletrico(bateria, consumoPorMinuto);
        this.bateria = bateria;
        this.consumoPorMinuto = consumoPorMinuto;
    }

    private void validarCriacaoVeiculoEletrico(int bateria, int consumoPorMinuto){
        if(bateria < 0){
            throw new IllegalArgumentException("A bateria não pode ser menor que 0.");
        }
        if(consumoPorMinuto <= 0){
            throw new IllegalArgumentException("O consumo por minuto deve ser maior que 0.");
        }
    }

    public int getBateria(){
        return this.bateria;
    }

    public void recarregarBateria() throws VeiculoEmUsoException{ //TODO: parâmetros do método (Administrador administrador)
        enviarParaManutencao();
        this.bateria = 100;
    }


    public int getConsumoPorMinuto(){
        return this.consumoPorMinuto;
    }

    @Override 
    public void validarLiberarParaViagem() throws BateriaInsuficienteException{
        validarLiberarParaViagem();
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
