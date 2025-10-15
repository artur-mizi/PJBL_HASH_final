// ############### IMPLEMENTACAO DA LISTA ENCADEADA #############
    //lista encadeada com metodos basicos, alem de uma checagem de quantidades de nodes, para verificar se estourou o limite estipulado
public class ListaEncadeada {
    private Node raiz;
    private int quantNodes;

    //inicia com quantidade de nós em 0
    public ListaEncadeada() {
        this.raiz = null;
        this.quantNodes = 0;
    }

    //checa se está vazia
    private boolean estaVazia() {
        return this.raiz == null;
    }

    //retorna quantidade de nós
    public int getQuantNodes() {
        return this.quantNodes;
    }

    //junta todos os nós em um vetor registros para passar pra outra tabela
    public Registro[] coletarRegistros() {
        Node atual = this.raiz;
        Registro[] registros = new Registro[this.quantNodes];

        int indexer = 0;
        while (atual != null) {
            registros[indexer] = atual.getDado();
            atual = atual.getProximo();
            indexer++;
        }

        return registros;
    }

    //insere um registro em um nó
    public long inserirRegistro(Registro registro) {
        long colisoes = this.quantNodes;
        Node novoNo = new Node(registro);
        novoNo.setProximo(this.raiz);
        this.raiz = novoNo;
        this.quantNodes ++;
        return colisoes;
    }

    //retorna true se existir na lista, e false se nao
    public boolean buscarRegistro(Registro registro) {

        if (this.estaVazia()) {
            return false;
        } else {

            Node atual = this.raiz;
            while (atual != null) {
                if (atual.getDado().getCodigo() == registro.getCodigo()) {
                    return true;
                } 
                atual = atual.getProximo();
            }

            return false;
                
        }

    }

    //imprime a lista de maneira formatada, com os valores de cada node
    public void imprimir() {
        if (this.raiz == null) {
            System.out.println("Lista vazia");
            return;
        }
        Node atual = this.raiz;
        while (atual.getProximo() != null) {
            System.out.print(atual.getDado().getCodigo() + " -> ");
            atual = atual.getProximo();
        }
        System.out.print("null\n");
    
    }
}
