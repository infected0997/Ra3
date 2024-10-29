import java.util.LinkedList;
import java.util.List;

class TabelaHash {
    private List<Registro>[] tabela;
    private int tamanho;
    private int colisoes;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
        this.colisoes = 0;
    }

    public int hashResto(String codigo) {
        return Integer.parseInt(codigo) % tamanho;
    }

    public int hashMultiplicacao(String codigo) {
        int key = Integer.parseInt(codigo);
        return (key * 31) % tamanho; // Usando 31 como multiplicador
    }

    public int hashDobramento(String codigo) {
        int key = Integer.parseInt(codigo);
        String keyStr = String.valueOf(key);

        // Soma os dígitos do código
        int soma = 0;
        for (char c : keyStr.toCharArray()) {
            soma += Character.getNumericValue(c);
        }

        return soma % tamanho; // Usando a soma dos dígitos
    }

    public void inserir(Registro registro, String tipoHash) {
        int index;
        if (tipoHash.equals("resto")) {
            index = hashResto(registro.getCodigo());
        } else if (tipoHash.equals("multiplicacao")) {
            index = hashMultiplicacao(registro.getCodigo());
        } else {
            index = hashDobramento(registro.getCodigo());
        }

        if (!tabela[index].isEmpty()) {
            colisoes++;
        }
        tabela[index].add(registro);
    }

    public Registro buscar(String codigo, String tipoHash) {
        int index;
        if (tipoHash.equals("resto")) {
            index = hashResto(codigo);
        } else if (tipoHash.equals("multiplicacao")) {
            index = hashMultiplicacao(codigo);
        } else {
            index = hashDobramento(codigo);
        }

        for (Registro r : tabela[index]) {
            if (r.getCodigo().equals(codigo)) {
                return r;
            }
        }
        return null; // não encontrado
    }

    public int getColisoes() {
        return colisoes;
    }

    public void resetColisoes() {
        colisoes = 0;
    }
}
