//nodes das listas, com metodos basicos
public class Node {
    private Registro dado;
    private Node proximo;

    //registro é salvo como dado no nó
    public Node(Registro registro) {
        this.dado = registro;
        this.proximo = null;
    }

    //retorna dado do nó
    public Registro getDado() {
        return this.dado;
    }

    public void setDado(Registro dado) {
        this.dado = dado;
    }

    //retorna proximo nó
    public Node getProximo() {
        return this.proximo;
    }

    //seta próximo nó
    public void setProximo(Node proximo) {
        this.proximo = proximo;
    }
}
