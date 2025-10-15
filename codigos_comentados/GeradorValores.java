import java.util.Random;

//Gerador de valores

//gera numeros aleatorios de 100000000 ate 999999999
public class GeradorValores {

    private final int min = 100000000;
    private final int max = 999999999;

    private final int intervalo = max - min + 1;
    private final Random gerador;

    private static final int DEZ_MIL = 10_000;
    private static final int CEM_MIL = 100_000;
    private static final int QUINHENTOS_MIL = 500_000;

    // gera valores a partir de uma seed especifica (para garantir os mesmos valores toda execução)
    public GeradorValores(int seed) {
        this.gerador = new Random(seed);
    }

    // método para gerar 10 000 valores aleatorios
    public Registro[] gerarDezMil() {
        Registro[] registros = new Registro[DEZ_MIL];

        for (int i = 0; i < DEZ_MIL; i++) {
            registros[i] = new Registro(this.gerador.nextInt(this.intervalo) + this.min);
        }

        return registros;
    }
    
    // método para gerar 100 000 valores aleatorios
    public Registro[] gerarCemMil() {
        Registro[] registros = new Registro[CEM_MIL];

        for (int i = 0; i < CEM_MIL; i++) {
            registros[i] = new Registro(this.gerador.nextInt(this.intervalo) + this.min);
        }

        return registros;
    }

    // método para gerar 500 000 valores aleatorios
    public Registro[] gerarQuinhentosMil() {
        Registro[] registros = new Registro[QUINHENTOS_MIL];

        for (int i = 0; i < QUINHENTOS_MIL; i++) {
            registros[i] = new Registro(this.gerador.nextInt(this.intervalo) + this.min);
        }

        return registros;
    }


}