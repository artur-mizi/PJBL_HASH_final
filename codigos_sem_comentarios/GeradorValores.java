import java.util.Random;

public class GeradorValores {

    private final int min = 100000000;
    private final int max = 999999999;

    private final int intervalo = max - min + 1;
    private final Random gerador;

    private static final int DEZ_MIL = 10_000;
    private static final int CEM_MIL = 100_000;
    private static final int QUINHENTOS_MIL = 500_000;

    public GeradorValores(int seed) {
        this.gerador = new Random(seed);
    }

    public Registro[] gerarDezMil() {
        Registro[] registros = new Registro[DEZ_MIL];

        for (int i = 0; i < DEZ_MIL; i++) {
            registros[i] = new Registro(this.gerador.nextInt(this.intervalo) + this.min);
        }

        return registros;
    }
    
    public Registro[] gerarCemMil() {
        Registro[] registros = new Registro[CEM_MIL];

        for (int i = 0; i < CEM_MIL; i++) {
            registros[i] = new Registro(this.gerador.nextInt(this.intervalo) + this.min);
        }

        return registros;
    }

    public Registro[] gerarQuinhentosMil() {
        Registro[] registros = new Registro[QUINHENTOS_MIL];

        for (int i = 0; i < QUINHENTOS_MIL; i++) {
            registros[i] = new Registro(this.gerador.nextInt(this.intervalo) + this.min);
        }

        return registros;
    }



}
