package model;

import java.time.LocalDateTime;

import auxiliares.TipoOperacaoCarteira;
import contracts.Salvavel;

public class OperacaoCarteira extends Salvavel {

    public static OperacaoCarteira criarOperacaoCarteira(Carteira carteira, TipoOperacaoCarteira tipoOperacao,
            double valorOperacao) {
        var agora = LocalDateTime.now();
        return new OperacaoCarteira(carteira, tipoOperacao, valorOperacao, agora);
    }
    
    public static OperacaoCarteira carregarOperacaoCarteira(int codigo, Carteira carteira, TipoOperacaoCarteira tipoOperacao, double valorOperacao,
            LocalDateTime dataOperacao) {
        return new OperacaoCarteira(codigo, carteira, tipoOperacao, valorOperacao, dataOperacao);
    }

    private OperacaoCarteira(Carteira carteira, TipoOperacaoCarteira tipoOperacao, double valorOperacao,
            LocalDateTime dataOperacao) {
        super();
        setCarteira(carteira);
        setTipoOperacao(tipoOperacao);
        setValorOperacao(valorOperacao);
        setDataOperacao(dataOperacao);
    }

    private OperacaoCarteira(int codigo, Carteira carteira, TipoOperacaoCarteira tipoOperacao, double valorOperacao,
            LocalDateTime dataOperacao) {
        super(codigo);
        setCarteira(carteira);
        setTipoOperacao(tipoOperacao);
        setValorOperacao(valorOperacao);
        setDataOperacao(dataOperacao);
    }

    private Carteira carteira;

    public Carteira getCarteira(){
        return this.carteira;
    }

    private void setCarteira(Carteira carteira){
        if(carteira == null || carteira.getCodigo() == null){
            throw new IllegalArgumentException("Nova carteira inválida. Não pode ser nula.");
        }
        this.carteira = carteira;
    }

    private TipoOperacaoCarteira tipoOperacao;

    public TipoOperacaoCarteira getTipoOperacao(){
        return this.tipoOperacao;
    }

    private void setTipoOperacao(TipoOperacaoCarteira tipoOperacao){
        if (tipoOperacao == null){
            throw new IllegalArgumentException("Tipo da operação com a carteira não pode ser null.");
        }
        this.tipoOperacao = tipoOperacao;
    }

    private double valorOperacao;

    public double getValorOperacao() {
        return this.valorOperacao;
    }
    
    private void setValorOperacao(double valorOperacao) {
        if (valorOperacao < 0) {
            throw new IllegalArgumentException(
                    "Para representar valores negativos (débito), use o tipo de operação de Débito. Por padrão todos os valores de operações são positivas.");
        }
        this.valorOperacao = valorOperacao;
    }
    
    private LocalDateTime dataOperacao;

    public LocalDateTime getDataOperacao() {
        return this.dataOperacao;
    }

    private void setDataOperacao(LocalDateTime dataOperacao) {
        if (dataOperacao == null) {
            throw new IllegalArgumentException("Data da operação não pode ser nula.");
        }
        this.dataOperacao = dataOperacao;
    }
}
