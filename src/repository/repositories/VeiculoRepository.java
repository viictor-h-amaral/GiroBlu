package repository.repositories;

import java.io.IOException;

import mapper.mappers.VeiculoCsvMapper;
import model.Veiculo;
import repository.Repository;

public class VeiculoRepository extends Repository<Veiculo> {

    public VeiculoRepository() throws IOException {
        super(new VeiculoCsvMapper(), "veiculos.csv");
    }

    @Override 
    protected String obterValorCampo(Veiculo veiculo, String campo) {
        switch (campo) {
            case "codigo":
                return String.valueOf(veiculo.getCodigo());
            case "modelo":
                return veiculo.getModelo();
            case "valorDesbloqueio":
                return String.valueOf(veiculo.getValorDesbloqueio());
            case "valorPorMinuto":
                return String.valueOf(veiculo.getValorPorMinuto());
            case "estadoAtual":
                return String.valueOf(veiculo.getEstadoAtual().ordinal());
            case "estacao":
                return String.valueOf(veiculo.getEstacao().getCodigo());
            default:
                throw new IllegalArgumentException("Campo informado é inválido! Informado: " + campo);
        }
    }
}