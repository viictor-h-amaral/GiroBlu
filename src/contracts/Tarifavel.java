package contracts;

import model.Veiculo;

public interface Tarifavel {
    double calcularTarifa(int minutos, Veiculo veiculo);
}
