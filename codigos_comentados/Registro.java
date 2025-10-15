//Registro

//representa um registro com sua chave designada nas tabelas hash

public class Registro {

    private int codigo;

// Seta o registro como o codigo gerado
    public Registro(int codigo) {
        this.codigo = codigo;
    }

// retorna o codigo de um certo registro
    public int getCodigo() {
        return this.codigo;
    }

}