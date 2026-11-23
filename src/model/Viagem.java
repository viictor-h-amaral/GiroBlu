package model;

import java.time.Duration;
import java.time.LocalDateTime;

import contracts.Salvavel;
import exceptions.EstacaoLotadaException;
import exceptions.PeriodoInvalidoException;
import exceptions.ProblemaViagemException;
import exceptions.VeiculoNaoEmViagemException;

public class Viagem extends Salvavel{

    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFinal = null;
    private Cliente viajante;
    private Veiculo veiculo;
    private Estacao estacaoOrigem;

    public static Viagem criarViagem(Cliente viajante, Veiculo veiculo) throws ProblemaViagemException{
        return new Viagem(viajante, veiculo);
    }

    public static Viagem carregarViagem(int codigo, Cliente viajante, Veiculo veiculo, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFinal){
        return new Viagem(codigo, viajante, veiculo, dataHoraInicio, dataHoraFinal);
    }

    private Viagem(Cliente viajante, Veiculo veiculo) throws ProblemaViagemException{
        super();
        setViajante(viajante);
        setVeiculo(veiculo);

        viajante.validarLiberarParaViagem();
        veiculo.validarLiberarParaViagem();

        setEstacaoOrigem(veiculo.getEstacao());
        getEstacaoOrigem().retirarVeiculo(veiculo);

        viajante.liberarParaViagem();
        veiculo.liberarParaViagem();

        setDataHoraInicio(LocalDateTime.now());
    }

    private Viagem(int codigo, Cliente viajante, Veiculo veiculo, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFinal){
        super(codigo);
        setViajante(viajante);
        setVeiculo(veiculo);
        setDataHoraInicio(dataHoraInicio);
        this.dataHoraFinal = dataHoraFinal;
        this.estacaoOrigem = null;
    }

    public Cliente getViajante() {
        return this.viajante;
    }
    
    public void setViajante(Cliente viajante){
        if(viajante == null || viajante.getCodigo() == null){
            throw new IllegalArgumentException("Viajante inválido. O usuário não pode ser nulo.");
        }
        this.viajante = viajante;
    }
    
    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public Estacao getEstacaoOrigem() {
        return this.estacaoOrigem;
    }

    private void setEstacaoOrigem(Estacao estacao){
        if (estacao == null) {
            throw new IllegalArgumentException("O veículo precisa estar associado a uma estação para iniciar a viagem.");
        }
        this.estacaoOrigem = estacao;
    }
    
    public void setVeiculo(Veiculo veiculo){
        if(veiculo == null || veiculo.getCodigo() == null){
            throw new IllegalArgumentException("Veículo inválido. O veículo não pode ser nulo.");
        }
        this.veiculo = veiculo;
    }

    public LocalDateTime getDataHoraInicio() {
        return this.dataHoraInicio;
    }

    private void setDataHoraInicio(LocalDateTime dataHoraInicio){
        if(dataHoraInicio == null){
            throw new IllegalArgumentException("Data e hora de início inválidos. Não pode ser nulo");
        }
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFinal() {
        return this.dataHoraFinal;
    }

    private void setDataHoraFinal(LocalDateTime dataHoraFinal) throws PeriodoInvalidoException{
        if(dataHoraFinal.isBefore(this.dataHoraInicio)){
            throw new PeriodoInvalidoException();
        }
        this.dataHoraFinal = dataHoraFinal;
    }

    public void finalizarViagem(Estacao estacao) throws VeiculoNaoEmViagemException, VeiculoNaoEmViagemException, EstacaoLotadaException, PeriodoInvalidoException{ 
        getVeiculo().validarRetornarViagem(estacao);
        getViajante().validarFinalizarViagem();
        
        retornarViagemVeiculo(estacao);
        getViajante().finalizarViagem();

        setDataHoraFinal(LocalDateTime.now());
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