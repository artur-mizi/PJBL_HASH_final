public class HashEncadeado {

    private static final int MIL = 1000;
    private static final int UM_MILHAO = 1_000_000;

    private ListaEncadeada[] tabela;
    private final int tamanho_tabela;

    public HashEncadeado(int tamanho_tabela) {
        this.tabela = new ListaEncadeada[tamanho_tabela];
        this.tamanho_tabela = tamanho_tabela;

        for (int i = 0; i < tamanho_tabela; i++) {
            this.tabela[i] = new ListaEncadeada();
        }
    }

    public long inserirRegistro(Registro registro) {
        return this.tabela[hashPorDivisao(registro.getCodigo())].inserirRegistro(registro);
    }

    public boolean buscarRegistro(Registro registro) {
        return this.tabela[hashPorDivisao(registro.getCodigo())].buscarRegistro(registro);
    }

    private int hashPorDivisao(int codigo) {
        int primeira_fatia = codigo % MIL;
        int segunda_fatia = (codigo / MIL) % MIL;
        int terceira_fatia = codigo / UM_MILHAO;

        return (primeira_fatia + segunda_fatia + terceira_fatia) % this.tamanho_tabela;
    }
    // ######################################################

    public ListaEncadeada[] maiores_listas() {
        ListaEncadeada[] maiores_listas = new ListaEncadeada[3];
        maiores_listas = Main.encontrar_maiores_listas(tabela);
        return maiores_listas;
    }
}   