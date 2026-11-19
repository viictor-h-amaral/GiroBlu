package contracts;

public abstract class Salvavel {

    protected Integer codigo;

    public Integer getCodigo(){
        return this.codigo;
    }

    public void setCodigo(Integer codigo){
        if (codigo == null || codigo <= 0) {
            throw new IllegalArgumentException("Código deve ser maior que zero.");
        }
        this.codigo = codigo;
    }

    public Salvavel(Integer codigo){
        setCodigo(codigo);
    }
    
    public Salvavel() {
    }
}