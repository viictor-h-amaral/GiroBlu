package repository.repositories;

import java.io.IOException;

import auxiliares.EstadoAtualVeiculo;
import mapper.mappers.ViagemCsvMapper;
import model.Viagem;
import repository.Repository;

public class ViagemRepository extends Repository<Viagem>{

    private static final String NOME_ARQUIVO = "viagens.csv";

    private static ViagemRepository instance = null;

    private ViagemRepository() throws IOException{
        super(new ViagemCsvMapper(), NOME_ARQUIVO);
    }

    public static ViagemRepository getInstance() throws IOException{
        if(instance == null){
            instance= new ViagemRepository();
        }

        return instance;
    }

    @Override 
    protected String obterValorCampo(Viagem viagem, String campo){
        switch (campo) {
            case "codigo":
                return String.valueOf(viagem.getCodigo());
            case "viajante":
                return String.valueOf(viagem.getViajante().getCodigo());
            case "veiculo":
                return String.valueOf(viagem.getVeiculo().getCodigo());
            case "dataHoraInicio":
                return String.valueOf(viagem.getDataHoraInicio());
            case "dataHoraFinal":
                return String.valueOf(viagem.getDataHoraFinal());
            default:
                throw new IllegalArgumentException("Campo informado é inválido! Informado: " + campo);
        } 
    }

    @Override 
    public void insert(Viagem entidade) throws IOException {
        if (entidade == null) {
            throw new IllegalArgumentException("A viagem não pode ser nula.");
        }

        var cliente = entidade.getViajante();
        var veiculo = entidade.getVeiculo();
        var estacaoOrigem = entidade.getEstacaoOrigem();
        if (estacaoOrigem == null) {
            throw new IllegalArgumentException("Só é possível inserir uma viagem recém-criada, com estação de origem definida.");
        }

        boolean tentativaInsercaoViagem = false;

        try {
            UsuarioRepository.getInstance().update(cliente);
            VeiculoRepository.getInstance().update(veiculo);
            tentativaInsercaoViagem = true;
            super.insert(entidade);
        } catch (IOException erroGravacao) {
            if (tentativaInsercaoViagem && entidade.getCodigo() != null) {
                try {
                    super.delete(entidade.getCodigo());
                } catch (Exception erroRollback) {
                    erroGravacao.addSuppressed(erroRollback);
                }
            }

            try {
                if (cliente.isEmViagem()) {
                    cliente.finalizarViagem();
                }
                UsuarioRepository.getInstance().update(cliente);
            } catch (Exception erroRollback) {
                erroGravacao.addSuppressed(erroRollback);
            }

            try {
                if (veiculo.getEstadoAtual() == EstadoAtualVeiculo.EM_VIAGEM) {
                    veiculo.retornarViagem(estacaoOrigem);
                    estacaoOrigem.adicionarVeiculo(veiculo);
                }
                VeiculoRepository.getInstance().update(veiculo);
            } catch (Exception erroRollback) {
                erroGravacao.addSuppressed(erroRollback);
            }

            throw erroGravacao;
        }

    }
}