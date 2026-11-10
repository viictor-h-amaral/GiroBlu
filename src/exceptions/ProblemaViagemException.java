package exceptions;

public class ProblemaViagemException extends GiroBluException {

    public ProblemaViagemException(){
        super("Ocorreu um problema ao iniciar a viagem.");
    }

    public ProblemaViagemException(String mensagem){
        super(mensagem);
    }

}
