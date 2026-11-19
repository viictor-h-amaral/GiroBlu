package repository;

import java.io.IOException;

import mapper.mappers.VeiculoCsvMapper;
import model.Veiculo;

public class VeiculoRepository extends Repository<Veiculo> {

    public VeiculoRepository() throws IOException {
        super(new VeiculoCsvMapper(), "veiculos.csv");
    }

    @Override 
    protected String obterValorCampo(Veiculo veiculo, String campo) {
        switch (campo) {
            case "id":
                return String.valueOf(veiculo.getId());
            case "marca":
                return veiculo.getMarca();
            case "modelo":
                return veiculo.getModelo();
            case "ano":
                return String.valueOf(veiculo.getAno());
            case "placa":
                return veiculo.getPlaca();
            default:
                throw new IllegalArgumentException("Campo informado é inválido! Informado: " + campo);
        }
    }
}