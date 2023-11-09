import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        int[] tamanhos = {50, 500, 5000, 50000, 500000};
        String[] funcoesHash = {"Resto da Divisão", "Multiplicação", "Dobramento"};

        for (int tamanho : tamanhos) {
            for (String funcaoHash : funcoesHash) {
                System.out.println("Tamanho da Tabela Hash: " + tamanho);
                System.out.println("Função de Hash: " + funcaoHash);

                TabelaHash tabelaHash = new TabelaHash(tamanho, funcaoHash);

                List<Registro> conjuntoDeDados = gerarConjuntoDeDados(tamanho);

                long tempoInsercao = TempoInsercao(tabelaHash, conjuntoDeDados);
                int numeroColisoes = tabelaHash.getNumeroColisoes();

                System.out.println("Número de Inserções: " + tabelaHash.getNumeroInsercoes());
                System.out.println("Número de Colisões: " + numeroColisoes);

                long tempoBusca = TempoBusca(tabelaHash, conjuntoDeDados);
                int numeroComparacoes = tabelaHash.getNumeroComparacoes();

                System.out.println("Tempo de Inserção: " + tempoInsercao + " ms");
                System.out.println("Tempo de Busca: " + tempoBusca + " ms");
                System.out.println("Número de Comparações Médio: " + (double) numeroComparacoes / conjuntoDeDados.size());

                System.out.println();
            }
        }
    }

    private static List<Registro> gerarConjuntoDeDados(int tamanho) {
        List<Registro> conjunto = new ArrayList<>();
        Random random = new Random(1234);

        for (int i = 0; i < tamanho; i++) {
            int codigo = random.nextInt(5000000);
            conjunto.add(new Registro(codigo));
        }

        return conjunto;
    }

    private static long TempoInsercao(TabelaHash tabelaHash, List<Registro> conjuntoDeDados) {
        long startTime = System.nanoTime();

        for (Registro registro : conjuntoDeDados) {
            tabelaHash.inserir(registro);
        }

        long endTime = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
    }

    private static long TempoBusca(TabelaHash tabelaHash, List<Registro> conjuntoDeDados) {
        long startTime = System.nanoTime();

        for (Registro registro : conjuntoDeDados) {
            tabelaHash.buscar(registro.getCodigo());
        }

        long endTime = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
    }
}
