package repository.repositories;

import java.io.IOException;

import mapper.mappers.UsuarioCsvMapper;
import model.Administrador;
import model.Cliente;
import model.Usuario;
import repository.Repository;

public class UsuarioRepository extends Repository<Usuario>{

    private static final String NOME_ARQUIVO = "usuarios.csv";
    private static final String COLUNA_VAZIA = "";

    private static UsuarioRepository instance = null;

    private UsuarioRepository() throws IOException {
        super(new UsuarioCsvMapper(), NOME_ARQUIVO);
    }

    public static UsuarioRepository getInstance() throws IOException {
        if (instance == null) {
            instance = new UsuarioRepository();
        }
        return instance;
    }

    @Override 
    protected String obterValorCampo(Usuario usuario, String campo) {
        switch (campo) {
            case "codigo":
                return String.valueOf(usuario.getCodigo());

            case "tipo":
                return Usuario.obterTipo(usuario);

            case "nome":
                return String.valueOf(usuario.getNome());

            case "email":
                return String.valueOf(usuario.getEmail());

            case "plano":
                if(usuario instanceof Cliente){
                    return String.valueOf(((Cliente) usuario).getPlano().getCodigo());
                }
                return COLUNA_VAZIA;

            case "emViagem":
                if(usuario instanceof Cliente){
                    return String.valueOf(((Cliente) usuario).isEmViagem());
                }
                return COLUNA_VAZIA;

            case "cpf":
                if(usuario instanceof Cliente){
                    return String.valueOf(((Cliente) usuario).getCpf());
                }
                return COLUNA_VAZIA;

            case "carteira":
                if(usuario instanceof Cliente){
                    return String.valueOf(((Cliente) usuario).getCarteira().getCodigo());
                }
                return COLUNA_VAZIA;

            case "codigoAcesso":
                if(usuario instanceof Administrador){
                    return String.valueOf(((Administrador) usuario).getCodigoAcesso());
                }
                return COLUNA_VAZIA;

            default:
                throw new IllegalArgumentException("Campo informado é inválido! Informado: " + campo);
        }
    }

    @Override 
    public void insert(Usuario entidade) throws IOException {
        if(entidade instanceof Cliente){
            var cliente = (Cliente) entidade;
            var carteiraRepo = CarteiraRepository.getInstance();
            var carteira = cliente.getCarteira();
            carteiraRepo.insert(carteira);
        }

        super.insert(entidade);
    }

    @Override
    public void delete(int codigo) throws IOException{
        var usuario = selectOne(codigo);
        super.delete(codigo);
        
        if (!(usuario instanceof Cliente)){
            return;
        }

        int codigoCarteira = ((Cliente) usuario).getCarteira().getCodigo();
        CarteiraRepository.getInstance().delete(codigoCarteira);        
    }
}
