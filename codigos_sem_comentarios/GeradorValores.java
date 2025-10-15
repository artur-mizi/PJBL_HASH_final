import java.util.Random;

public class GeradorValores {

    private final int min = 100000000;
    private final int max = 999999999;
    /* 
    >> Random.nextInt() SEMPRE COMEÇA EM 0 (INCLUSO) E VAI ATE O MAX (EXLUIDO)

    >> Se quer incluir max precisa somar 1 ao intervalo

    >> POR ISSO SOMAMOS 1 NO INTERVALO E POSTERIORMENTE, AO USAR NEXTINT(), SOMAMOS "MIN"
    AO RESULTADO, ASSIM DESLOCAMOS VALOR ATE O INTERVALO QUE QUEREMOS (A PARTIR DE MIN ATE O MAX,
    COM AMBOS INCLUIDOS).

    >> EX - intervalo = (105 - 100 + 1) = 6 
        >> Random.nextInt(6) + 100 gera valores entre 100 (0 + 100) e 105 (5 + 100)
    */
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