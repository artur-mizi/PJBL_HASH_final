public class ListaEncadeada {
    private Node raiz;
    private int quantNodes;

    public ListaEncadeada() {
        this.raiz = null;
        this.quantNodes = 0;
    }

    private boolean estaVazia() {
        return this.raiz == null;
    }

    public int getQuantNodes() {
        return this.quantNodes;
    }

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

    public long inserirRegistro(Registro registro) {
        long colisoes = this.quantNodes;
        Node novoNo = new Node(registro);
        novoNo.setProximo(this.raiz);
        this.raiz = novoNo;
        this.quantNodes ++;
        return colisoes;
    }

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
