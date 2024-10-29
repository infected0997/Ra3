import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Definições
        int[] tamanhos = {10, 100, 1000};
        int[] quantidades = {1000000, 5000000, 20000000}; // Diferentes tamanhos de conjuntos de dados
        String seed = "42"; // Para reproduzibilidade
        Random random = new Random(seed.hashCode());

        for (int tamanho : tamanhos) {
            for (int quantidade : quantidades) {
                TabelaHash tabela = new TabelaHash(tamanho);
                System.out.println("Tamanho da tabela: " + tamanho + ", Quantidade de registros: " + quantidade);

                // Gerar e inserir registros
                for (int i = 0; i < quantidade; i++) {
                    String codigo = String.format("%09d", random.nextInt(1000000000));
                    tabela.inserir(new Registro(codigo), "resto"); // Teste com resto, altere conforme necessário
                }

                // Medir buscas
                for (int i = 0; i < 5; i++) { // Realizar 5 buscas
                    String codigoBusca = String.format("%09d", random.nextInt(1000000000));
                    long inicio = System.nanoTime();
                    tabela.buscar(codigoBusca, "resto"); // Teste com resto, altere conforme necessário
                    long tempoBusca = System.nanoTime() - inicio;
                    System.out.println("Busca " + (i + 1) + " - Tempo: " + tempoBusca + " ns");
                }

                System.out.println("Número de colisões para tabela de tamanho " + tamanho + ": " + tabela.getColisoes());
                tabela.resetColisoes(); // Resetar colisões para próximo teste
            }
        }
    }
}
