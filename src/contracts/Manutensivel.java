package contracts;

import exceptions.VeiculoEmUsoException;
import exceptions.VeiculoNaoEmManutencaoException;
import model.Administrador;

public interface Manutensivel {

    void enviarParaManutencao(Administrador administrador) throws VeiculoEmUsoException;
    void retornarManutencao(Administrador administrador) throws VeiculoNaoEmManutencaoException;

}
