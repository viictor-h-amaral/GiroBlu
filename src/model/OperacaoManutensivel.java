package model;

import java.time.LocalDateTime;

import auxiliares.TipoOperacaoManutensivel;
import contracts.Salvavel;

public class OperacaoManutensivel extends Salvavel{

    private Veiculo veiculo;
    private TipoOperacaoManutensivel tipoOperacao;
    private Administrador administrador;
    private LocalDateTime dataOperacao;

    public static OperacaoManutensivel criarOperacaoManutensivel(Veiculo veiculo, TipoOperacaoManutensivel tipoOperacao, Administrador administrador){
        var agora = LocalDateTime.now();
        return new OperacaoManutensivel(veiculo, tipoOperacao, administrador, agora);
    }

    public static OperacaoManutensivel carregarOperacaoManutensivel(int codigo, Veiculo veiculo, TipoOperacaoManutensivel tipoOperacao, Administrador administrador, LocalDateTime dataOperacao){
        return new OperacaoManutensivel(codigo, veiculo, tipoOperacao, administrador, dataOperacao);
    }

    private OperacaoManutensivel(Veiculo veiculo, TipoOperacaoManutensivel tipoOperacao, Administrador administrador, LocalDateTime dataOperacao){
        super();
        setVeiculo(veiculo);
        setTipoOperacao(tipoOperacao);
        setAdministrador(administrador);
        setDataOperacao(dataOperacao);
    }

    private OperacaoManutensivel(int codigo, Veiculo veiculo, TipoOperacaoManutensivel tipoOperacao, Administrador administrador, LocalDateTime dataOperacao){
        super(codigo);
        setVeiculo(veiculo);
        setTipoOperacao(tipoOperacao);
        setAdministrador(administrador);
        setDataOperacao(dataOperacao);
    }

    public Veiculo getVeiculo(){
        return this.veiculo;
    }

    private void setVeiculo(Veiculo veiculo){
        if(veiculo == null || veiculo.getCodigo() == null){
            throw new IllegalArgumentException("Veículo inválido. Não pode ser nulo.");
        }
        this.veiculo = veiculo;
    }

    public TipoOperacaoManutensivel getTipoOperacao(){
        return this.tipoOperacao;
    }

    private void setTipoOperacao(TipoOperacaoManutensivel tipoOperacao){
        if(tipoOperacao == null){
            throw new IllegalArgumentException("Tipo de operação com manutensível não pode ser nulo.");
        }
        this.tipoOperacao = tipoOperacao;
    }

    public Administrador getAdministrador(){
        return this.administrador;
    }

    private void setAdministrador(Administrador administrador){
        if(administrador == null || administrador.getCodigo() == null){
            throw new IllegalArgumentException("Administrador inválido. Não pode ser nulo.");
        }
        this.administrador = administrador;
    }

    public LocalDateTime getDataoperacao(){
        return this.dataOperacao;
    }

    private void setDataOperacao(LocalDateTime dataOperacao){
         if (dataOperacao == null) {
            throw new IllegalArgumentException("Data da operação não pode ser nula.");
        }
        this.dataOperacao = dataOperacao;
    }

}
