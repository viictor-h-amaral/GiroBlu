package model;

import java.time.Duration;
import java.time.LocalDateTime;

import exceptions.VeiculoNaoEmViagemException;

public class Viagem {

    private int codigo;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFinal = null;
    private Cliente usuario;
    private Veiculo veiculo;

    public Viagem(int codigo, Cliente usuario, Veiculo veiculo) {
        validarCriacaoViagem(codigo, usuario, veiculo);
        
        this.codigo = codigo;
        this.usuario = usuario;
        this.veiculo = veiculo;

        this.dataHoraInicio = LocalDateTime.now();
    }

    private void validarCriacaoViagem(int codigo, Cliente usuario, Veiculo veiculo){
        if(codigo <= 0){
            throw new IllegalArgumentException("Código inválido. Deve ser maior que zero.");
        }
        else if(usuario == null){
            throw new IllegalArgumentException("Usuário inválido. O usuário não pode ser nulo.");
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

    public Cliente getUsuario() {
        return this.usuario;
    }

    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void finalizarViagem(LocalDateTime dataHoraFinal) throws VeiculoNaoEmViagemException{ // 
        this.dataHoraFinal = dataHoraFinal;
        this.veiculo.retornarViagem();
        this.usuario.finalizarViagem();
    }

    public int calcularMinutosViagem() {
        if (dataHoraFinal == null) {
            throw new IllegalStateException("A viagem ainda não foi finalizada.");
        }
        return (int) Duration.between(dataHoraInicio, dataHoraFinal).toMinutes();
    }

    public double calcularValorViagem() {
        int minutos = calcularMinutosViagem();
        double valorPorMinuto = usuario.getPlano().calcularTarifa(minutos, veiculo);
        return valorPorMinuto;
    }
}