package repository.repositories;

import java.io.IOException;

import mapper.mappers.CarteiraCsvMapper;
import model.Carteira;
import repository.Repository;

public class CarteiraRepository extends Repository<Carteira>{

private static final String NOME_ARQUIVO = "carteiras.csv";

    private static CarteiraRepository instance = null;

    private CarteiraRepository() throws IOException {
        super(new CarteiraCsvMapper(), NOME_ARQUIVO);
    }

    public static CarteiraRepository getInstance() throws IOException {
        if (instance == null) {
            instance = new CarteiraRepository();
        }
        return instance;
    }

    @Override 
    protected String obterValorCampo(Carteira carteira, String campo) {
        switch (campo) {
            case "codigo":
                return String.valueOf(carteira.getCodigo());
            case "saldo":
                return String.valueOf(carteira.getSaldo());
            default:
                throw new IllegalArgumentException("Campo informado é inválido! Informado: " + campo);
        }
    }
}
