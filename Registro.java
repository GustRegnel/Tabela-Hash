public class Registro {
    private int codigo;

    private Registro proximo;

    public Registro(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setProximo(Registro proximo) {
        this.proximo = proximo;
    }

    public Registro getProximo(){
        return proximo;
    }
}
