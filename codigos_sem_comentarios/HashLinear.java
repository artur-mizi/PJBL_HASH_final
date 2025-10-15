public class HashLinear {

    private static final int MIL = 1000;
    private static final int UM_MILHAO = 1000000;
    private static final double FATOR_CARGA_MAX = 0.75;

    private Registro[] tabela;
    private boolean[] ocupado;
    private int tamanho_tabela;
    private int num_elementos;

    public HashLinear(int tamanho_tabela) {
        this.tamanho_tabela = tamanho_tabela;
        this.tabela = new Registro[tamanho_tabela];
        this.ocupado = new boolean[tamanho_tabela];
        this.num_elementos = 0;
    }

    public long inserirRegistro(Registro registro) {
        // se fator de carga exceder 0.75, faz rehash
        if ((double) num_elementos / tamanho_tabela >= FATOR_CARGA_MAX) {
            rehash();
        }

        int pos = hashPorDivisao(registro.getCodigo());
        long colisoes = 0;

        while (ocupado[pos]) {
            pos = (pos + 1);
            if (pos >= tamanho_tabela) pos = 0;
            colisoes++;

            if (colisoes >= tamanho_tabela) {
                rehash();
                return colisoes + inserirRegistro(registro);
            }
        }

        tabela[pos] = registro;
        ocupado[pos] = true;
        num_elementos++;
        return colisoes;
    }

    public boolean buscarRegistro(Registro registro) {
        int pos = hashPorDivisao(registro.getCodigo());
        int tentativas = 0;

        while (ocupado[pos]) {
            if (tabela[pos] != null && tabela[pos].getCodigo() == registro.getCodigo()) {
                return true;
            }
            pos = (pos + 1);
            if (pos >= tamanho_tabela) pos = 0;
            tentativas++;
            if (tentativas >= tamanho_tabela) break;
        }

        return false;
    }

    private void rehash() {
        int novo_tamanho = tamanho_tabela * 2;
        Registro[] antiga = tabela;
        boolean[] antigo_ocupado = ocupado;

        tabela = new Registro[novo_tamanho];
        ocupado = new boolean[novo_tamanho];
        tamanho_tabela = novo_tamanho;
        num_elementos = 0;

        for (int i = 0; i < antiga.length; i++) {
            if (antigo_ocupado[i] && antiga[i] != null) {
                inserirRegistro(antiga[i]);
            }
        }
    }

    private int hashPorDivisao(int codigo) {
        int primeira_fatia = codigo % 1000;
        if (primeira_fatia < 0) primeira_fatia = -primeira_fatia;

        int segunda_fatia = (codigo / 1000) % 1000;
        if (segunda_fatia < 0) segunda_fatia = -segunda_fatia;

        int terceira_fatia = codigo / 1000000;
        if (terceira_fatia < 0) terceira_fatia = -terceira_fatia;

        int resultado = (primeira_fatia + segunda_fatia + terceira_fatia) % tamanho_tabela;
        if (resultado < 0) resultado = -resultado;
        return resultado;
    }

    public int[] encontrar_gaps() {
        boolean em_gap = false;
        int quantidade_gaps = 0;
        for (int i = 0; i < this.tamanho_tabela; i++) {
            if (!this.ocupado[i]) {
                if (!em_gap) {
                    quantidade_gaps++;
                    em_gap = true;
                }
            } else {
                em_gap = false;
            }
        }
        em_gap = false;
        int gaps[] = new int[quantidade_gaps];
        int tamanho_gap = 0;
        int indice_inserir_gap = 0;
        for (int i = 0; i < this.tamanho_tabela; i++) {
            if (!this.ocupado[i]) {
                em_gap = true;
                tamanho_gap++;
            } else if (em_gap) {
                gaps[indice_inserir_gap] = tamanho_gap;
                em_gap = false;
                tamanho_gap = 0;
                indice_inserir_gap++;
            }
        }

        if (em_gap && indice_inserir_gap < quantidade_gaps) {
            gaps[indice_inserir_gap] = tamanho_gap;
        }

        return gaps;
    }

}

