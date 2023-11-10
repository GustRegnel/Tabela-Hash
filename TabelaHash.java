public class TabelaHash {
    private Registro[] tabela;
    private int tamanho;
    private int numeroInsercoes = 0;
    private int numeroColisoes = 0;
    private int numeroComparacoes = 0;
    private String funcaoHashSelecionada;

    public TabelaHash(int tamanho, String funcaoHashSelecionada) {
        this.tamanho = tamanho;
        this.funcaoHashSelecionada = funcaoHashSelecionada;
        tabela = new Registro[tamanho];
    }

    public void inserir(Registro registro) {
        int indice = calcularIndice(registro.getCodigo());
        if (tabela[indice] == null) {
            tabela[indice] = registro;
        } else {
            numeroColisoes++;

        }
        numeroInsercoes++;
    }

    public Registro buscar(int codigo) {
        int indice = calcularIndice(codigo);
        Registro registro = tabela[indice];
        if (registro != null && registro.getCodigo() == codigo) {
            numeroComparacoes++;
            return registro;
        } else {
            numeroComparacoes++;
        }
        return null;
    }

    private int calcularIndice(int codigo) {
        if (funcaoHashSelecionada.equals("Resto da Divisão")) {
            return funcaoHashRestoDaDivisao(codigo);
        } else if (funcaoHashSelecionada.equals("Multiplicação")) {
            return funcaoHashMultiplicacao(codigo);
        } else if (funcaoHashSelecionada.equals("Dobramento")) {
            return funcaoHashDobramento(codigo);
        }
        return 0;
    }

    private int funcaoHashRestoDaDivisao(int chave) {
        return chave % tamanho;
    }

    private int funcaoHashMultiplicacao(int chave) {
        double A = (Math.sqrt(5) - 1) / 2; 
        return (int) (tamanho * ((chave * A) % 1));
    }

    private int funcaoHashDobramento(int chave) {
        int somaDosDigitos = 0;
        while (chave > 0) {
            somaDosDigitos += chave % 10;
            chave /= 10;
        }
        return somaDosDigitos % tamanho;
    }

    public int getNumeroInsercoes() {
        return numeroInsercoes;
    }

    public int getNumeroColisoes() {
        return numeroColisoes;
    }

    public int getNumeroComparacoes() {
        return numeroComparacoes;
    }
}
