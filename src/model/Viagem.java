package model;

import java.time.Duration;
import java.time.LocalDateTime;

import exceptions.EstacaoLotadaException;
import exceptions.ProblemaViagemException;
import exceptions.VeiculoNaoEmViagemException;

public class Viagem {

    private int codigo;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFinal = null;
    private Cliente viajante;
    private Veiculo veiculo;

    public Viagem(int codigo, Cliente viajante, Veiculo veiculo) throws ProblemaViagemException {
        validarCriacaoViagem(codigo, viajante, veiculo);

        viajante.validarLiberarParaViagem();
        veiculo.validarLiberarParaViagem();

        viajante.liberarParaViagem();
        veiculo.liberarParaViagem();
        
        this.codigo = codigo;
        this.viajante = viajante;
        this.veiculo = veiculo;

        this.dataHoraInicio = LocalDateTime.now();
    }

    private void validarCriacaoViagem(int codigo, Cliente viajante, Veiculo veiculo){
        if(codigo <= 0){
            throw new IllegalArgumentException("Código inválido. Deve ser maior que zero.");
        }
        else if(viajante == null){
            throw new IllegalArgumentException("Viajante inválido. O usuário não pode ser nulo.");
        }
        else if(veiculo == null){
            throw new IllegalArgumentException("Veículo inválido. O veículo não pode ser nulo.");
        }
    }

    public int getCodigo() {
        return this.codigo;
    }

    public LocalDateTime getDataHoraInicio() {
        return this.dataHoraInicio;
    }

    public LocalDateTime getDataHoraFinal() {
        return this.dataHoraFinal;
    }

    public Cliente getViajante() {
        return this.viajante;
    }

    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void finalizarViagem(Estacao estacao) throws VeiculoNaoEmViagemException, VeiculoNaoEmViagemException, EstacaoLotadaException{ 
        getVeiculo().validarRetornarViagem(estacao);
        getViajante().validarFinalizarViagem();
        
        retornarViagemVeiculo(estacao);
        getViajante().finalizarViagem();
        
        this.dataHoraFinal = LocalDateTime.now();
    }

    private void retornarViagemVeiculo(Estacao estacao) throws VeiculoNaoEmViagemException, EstacaoLotadaException{
        getVeiculo().retornarViagem(estacao);
        estacao.adicionarVeiculo(getVeiculo());
    }

    public int calcularMinutosViagem() {
        if (dataHoraFinal == null) {
            throw new IllegalStateException("A viagem ainda não foi finalizada.");
        }
        return (int) Duration.between(dataHoraInicio, dataHoraFinal).toMinutes();
    }

    public double calcularValorViagem() {
        int minutos = calcularMinutosViagem();
        double valorTotal = viajante.getPlano().calcularTarifa(minutos, veiculo);
        return valorTotal;
    }
}