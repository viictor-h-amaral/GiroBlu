package mapper.mappers;

import java.io.IOException;

import helper.CsvHelper;
import mapper.CsvMapper;
import model.Administrador;
import model.Cliente;
import model.Usuario;
import repository.repositories.PlanoRepository;


public class UsuarioCsvMapper implements CsvMapper<Usuario>{

    private static final String SEPARADOR = ";";
    private static final int COLUNA_CODIGO = 0;
    private static final int COLUNA_TIPO = 1;
    private static final int COLUNA_NOME = 2;
    private static final int COLUNA_EMAIL = 3;
    private static final int COLUNA_PLANO = 4;
    private static final int COLUNA_EM_VIAGEM = 5;
    private static final int COLUNA_CPF = 6;
    private static final int COLUNA_CODIGO_ACESSO = 7;
    private static final int QUANTIDADE_COLUNAS = 8;
    private static final String CABECALHO = "codigo;tipo;nome;email;plano;emViagem;cpf;codigoAcesso";

    @Override 
    public String getCabecalho(){
        return CABECALHO;
    }

    @Override 
    public Usuario fromCsv(String linha) throws IOException{
        if(linha == null){
            throw new IOException("A linha CSV não pode ser nula");
        }

        String[] campos = linha.split(SEPARADOR, -1);
        if(campos.length != QUANTIDADE_COLUNAS){
            throw new IOException("A linha deve conter exatamente oito campos");
        }

        if(CsvHelper.temCaractereNaoSuportado(campos[COLUNA_NOME]) 
            || CsvHelper.temCaractereNaoSuportado(campos[COLUNA_EMAIL]) 
            || CsvHelper.temCaractereNaoSuportado(campos[COLUNA_CPF])){
            throw new IOException("Campo de texto inválido");
        }

        try {
            int codigo = Integer.parseInt(campos[COLUNA_CODIGO]);
            String tipo = campos[COLUNA_TIPO];
            String nome = campos[COLUNA_NOME];
            String email = campos[COLUNA_EMAIL];
            int codigoPlano = Integer.parseInt(campos[COLUNA_PLANO]);
            
            Usuario usuario;
            if(tipo.equals(Cliente.TIPO)){
                boolean emViagem = Boolean.parseBoolean(campos[COLUNA_EM_VIAGEM]);
                String cpf = campos[COLUNA_CPF];
                
                var planoRepository = PlanoRepository.getInstance();
                var plano = planoRepository.selectOne(codigoPlano);

                usuario = Cliente.carregarCliente(codigo, nome, email, plano, emViagem, cpf);
            }
            else if(tipo.equals(Administrador.TIPO)){
                int codigoAcesso = Integer.parseInt(campos[COLUNA_CODIGO_ACESSO]);
                usuario = Administrador.carregarAdministrador(codigo, nome, email, codigoAcesso);
            }
            else{
                throw new IOException("Tipo de usuário inválido: " + tipo);
            }

            return usuario;
    
        } catch (NumberFormatException e) {
            throw new IOException("Código, estado, estação ou bateria inválido", e);
        } catch (IllegalArgumentException e) {
            throw new IOException("Dados do veículo inválidos", e);
        } catch (Exception e) {
            throw new IOException("Não foi possível carregar o veículo do Banco de dados devido a um erro: ", e);
        }
    }

    @Override 
    public String toCsv(Usuario entidade){
        validarUsuario(entidade);

        String[] campos = new String[QUANTIDADE_COLUNAS];
        campos[COLUNA_CODIGO] = String.valueOf(entidade.getCodigo());
        campos[COLUNA_TIPO] = Usuario.obterTipo(entidade);
        campos[COLUNA_NOME] = CsvHelper.stringParaCsv(entidade.getNome());
        campos[COLUNA_EMAIL] = CsvHelper.stringParaCsv(entidade.getEmail());
        campos[COLUNA_PLANO] = "";
        campos[COLUNA_EM_VIAGEM] = "";
        campos[COLUNA_CPF] = "";
        campos[COLUNA_CODIGO_ACESSO] = "";

        if(entidade instanceof Cliente){
            Cliente cliente = (Cliente) entidade;
            campos[COLUNA_PLANO] = String.valueOf(cliente.getPlano().getCodigo());
            campos[COLUNA_EM_VIAGEM] = String.valueOf(cliente.isEmViagem());
            campos[COLUNA_CPF] = CsvHelper.stringParaCsv(cliente.getCpf());
        }
        else if(entidade instanceof Administrador){
            Administrador administrador = (Administrador) entidade;
            campos[COLUNA_CODIGO_ACESSO] = String.valueOf(administrador.getCodigoAcesso());
        }

        String linha = campos[COLUNA_CODIGO];
        for (int i = 1; i < campos.length; i++) {
            linha += SEPARADOR + campos[i];
        }
        return linha;
    }

    private void validarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("O usuário não pode ser nulo");
        }
        else if (CsvHelper.temCaractereNaoSuportado(usuario.getNome()) 
            || CsvHelper.temCaractereNaoSuportado(usuario.getEmail())) {
            throw new IllegalArgumentException(
                    "O campo de texto não pode conter ponto e vírgula (;), aspas (\") ou quebras de linha");
        }
        else if (usuario instanceof Cliente){
            Cliente cliente = (Cliente) usuario;
            if(CsvHelper.temCaractereNaoSuportado(cliente.getCpf())){
                throw new IllegalArgumentException(
                    "O campo de texto não pode conter ponto e vírgula (;), aspas (\") ou quebras de linha");
            }
        }
    }
}
