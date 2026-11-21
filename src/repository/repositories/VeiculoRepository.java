package repository.repositories;

import java.io.IOException;

import mapper.mappers.VeiculoCsvMapper;
import model.Veiculo;
import model.VeiculoEletrico;
import repository.Repository;

public class VeiculoRepository extends Repository<Veiculo> {

    private static VeiculoRepository instance = null;

    private VeiculoRepository() throws IOException {
        super(new VeiculoCsvMapper(), "veiculos.csv");
    }

    public static VeiculoRepository getInstance() throws IOException {
        if (instance == null) {
            instance = new VeiculoRepository();
        }
        return instance;
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
                return String.valueOf(veiculo.getEstadoAtual().getEstadoAtual());
            case "estacao":
                return String.valueOf(veiculo.getEstacao().getCodigo());
            case "tipo":
                return Veiculo.obterTipo(veiculo);
            case "bateria":
                if (veiculo instanceof VeiculoEletrico) {
                    return String.valueOf(((VeiculoEletrico) veiculo).getBateria());
                }
                return "";
            case "consumoPorMinuto":
                if (veiculo instanceof VeiculoEletrico) {
                    return String.valueOf(((VeiculoEletrico) veiculo).getConsumoPorMinuto());
                }
                return "";
            default:
                throw new IllegalArgumentException("Campo informado é inválido! Informado: " + campo);
        }
    }
}