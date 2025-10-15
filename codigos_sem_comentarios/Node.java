public class Node {
    private Registro dado;
    private Node proximo;

    public Node(Registro registro) {
        this.dado = registro;
        this.proximo = null;
    }

    public Registro getDado() {
        return this.dado;
    }

    public void setDado(Registro dado) {
        this.dado = dado;
    }

    public Node getProximo() {
        return this.proximo;
    }

    public void setProximo(Node proximo) {
        this.proximo = proximo;
    }
}
