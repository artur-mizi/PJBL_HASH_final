 //HashEncadeado

 // implementa uma tabela hash com tratamento de colisões via encadeamento separado (listas)

public class HashEncadeado {

    private static final int MIL = 1000;
    private static final int UM_MILHAO = 1_000_000;

    private ListaEncadeada[] tabela;
    private final int tamanho_tabela;

    // itera a tabela, criando uma lista encadeada para cada entrada
    public HashEncadeado(int tamanho_tabela) {
        this.tabela = new ListaEncadeada[tamanho_tabela];
        this.tamanho_tabela = tamanho_tabela;

        for (int i = 0; i < tamanho_tabela; i++) {
            this.tabela[i] = new ListaEncadeada();
        }
    }
    //insere um registro (passado como parametro) na tabela, a partir da função hash, e retorna o numero de colisoes
    public long inserirRegistro(Registro registro) {
        return this.tabela[hashPorDivisao(registro.getCodigo())].inserirRegistro(registro);
    }

    //busca um registro na tabela a partir de sua chave e da função hash
    public boolean buscarRegistro(Registro registro) {
        return this.tabela[hashPorDivisao(registro.getCodigo())].buscarRegistro(registro);
    }

    //função hash principal para determinar a chave de um certo valor
    private int hashPorDivisao(int codigo) {
        int primeira_fatia = codigo % MIL;
        int segunda_fatia = (codigo / MIL) % MIL;
        int terceira_fatia = codigo / UM_MILHAO;

        //separa o valor de 9 digitos em 3 porções de 3 digitos e pega o resto da divisao deles com o tamanho da tabela
        return (primeira_fatia + segunda_fatia + terceira_fatia) % this.tamanho_tabela;
    }

    // retorna uma lista com as 3 maiores listas encadeadas da tabela
    public ListaEncadeada[] maiores_listas() {
        ListaEncadeada[] maiores_listas = new ListaEncadeada[3];
        maiores_listas = Main.encontrar_maiores_listas(tabela);
        return maiores_listas;
    }
}   