package contracts;

import exceptions.GiroBluException;
import exceptions.VeiculoEmUsoException;
import exceptions.VeiculoNaoEmManutencaoException;

public interface Manutensivel {

    void enviarParaManutencao() throws VeiculoEmUsoException;
    void retornarManutencao() throws VeiculoNaoEmManutencaoException;

}
