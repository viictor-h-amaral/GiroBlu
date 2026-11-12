package contracts;

import exceptions.GiroBluException;

public interface Manutensivel {

    void enviarParaManutencao() throws GiroBluException;
    void retornarManutencao();

}
