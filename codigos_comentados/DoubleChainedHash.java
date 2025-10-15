 //DoubleChainedHash
 
 //implementa hashing duplo (double hashing), usando duas funções hash para distribuir valores em duas tabelas diferentes

public class DoubleChainedHash{

    private static final int MIL = 1000;
    private static final int UM_MILHAO = 1_000_000;
    private static final int LIMITE_IDEAL_LISTA = 100;
    
    private ListaEncadeada[] tabelaUm;
    private ListaEncadeada[] tabelaDois;
    private int tamanho;

    //cada instancia do double chained hash cria duas tabelas, com uma lista encadeada para cada index da tabela
    public DoubleChainedHash(int tamanho) {
        this.tabelaUm = new ListaEncadeada[tamanho];
        this.tabelaDois = new ListaEncadeada[tamanho];
        this.tamanho = tamanho;
    }

    //busca registro na primeira tabela usando a função um, depois na segunda usando a função dois
    public boolean buscarRegistro(Registro registro) {

        ListaEncadeada lista_um = this.tabelaUm[hashFuncaoUm(registro.getCodigo())];
        if (lista_um != null && lista_um.buscarRegistro(registro)) {
            return true;
        }

        ListaEncadeada lista_dois = this.tabelaDois[hashFuncaoDois(registro.getCodigo())];
        if (lista_dois != null && lista_dois.buscarRegistro(registro)) {
            return true;
        }

        return false;
    }

    // insere valores na primeira tabela usando a primeira função hash, quando o limite ideal da lista é estourado, passa a lista para a segunda tabela com kickLista()
    // retorna numero de colisoes que ocorreram
    public long inserirRegistro(Registro registro) {

        int index = this.hashFuncaoUm(registro.getCodigo());
        long colisoes = 0;

        if (this.tabelaUm[index] == null) {
            ListaEncadeada novaLista = new ListaEncadeada();
            colisoes += novaLista.inserirRegistro(registro);
            this.tabelaUm[index] = novaLista;
        } else if (this.tabelaUm[index].getQuantNodes() < LIMITE_IDEAL_LISTA) {
            colisoes += this.tabelaUm[index].inserirRegistro(registro);
        } else {
            ListaEncadeada novaLista = new ListaEncadeada();
            colisoes += novaLista.inserirRegistro(registro);

            colisoes += kickLista(tabelaUm[index]);
            tabelaUm[index] = novaLista;
        }
        return colisoes;
    }

    // recebe uma  lista que estava na primeira tabela, aplica a segunda função hash nos valores da lista, e os redistribui na segunda tabela
    // retorna numero de colisoes encontrado
    private long kickLista(ListaEncadeada lista) {

        Registro[] registros = lista.coletarRegistros();
        long colisoes = 0;

        for (Registro registro : registros) {
            int index = hashFuncaoDois(registro.getCodigo());

            if (this.tabelaDois[index] != null) {
                colisoes += this.tabelaDois[index].inserirRegistro(registro);
            } else {
                ListaEncadeada novaLista = new ListaEncadeada();
                colisoes += novaLista.inserirRegistro(registro);
                this.tabelaDois[index] = novaLista;
            }
        }
        return colisoes;
    }

    //primeira função hash, usando um xor com o resto do tamanho da tabela 
    private int hashFuncaoUm(int codigo) {
        int primeira_fatia = codigo % MIL;
        int segunda_fatia = (codigo / MIL) % MIL;
        int terceira_fatia = codigo / UM_MILHAO;

        int hash = (primeira_fatia ^ segunda_fatia ^ terceira_fatia) % this.tamanho;
        if (hash < 0) {hash += this.tamanho;}
        return hash;
    }

    //segunda função hash, multiplicando cada fatia por um numero primo para distanciar ao maximo seus valores
    private int hashFuncaoDois(int codigo) {
        int primeira_fatia = codigo % MIL;
        int segunda_fatia = (codigo / MIL) % MIL;
        int terceira_fatia = codigo / UM_MILHAO;

        int hash = (primeira_fatia * 131 + segunda_fatia * 137 + terceira_fatia * 149) % this.tamanho;
        if (hash < 0) {hash += this.tamanho;}
        return hash;
    }
}