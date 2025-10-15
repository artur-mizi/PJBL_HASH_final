public class Main {
    public static final int MIL = 1000;
    public static final int DEZ_MIL = 10_000;
    public static final int CEM_MIL = 100_000;

    //insere valores gerados na tabela, e retorna numero de colisoes
    public static long inserir_tabela(HashLinear tabela, Registro[] vetor) {
        long total_colisoes = 0;
        for (Registro registro : vetor) {
            total_colisoes += tabela.inserirRegistro(registro);
        }
        return total_colisoes;
    }

    //insere valores gerados na tabela, e retorna numero de colisoes
    public static long inserir_tabela(HashEncadeado tabela, Registro[] vetor) {
        long total_colisoes = 0;
        for (Registro registro : vetor) {
            total_colisoes += tabela.inserirRegistro(registro);
        }
        return total_colisoes;
    }
    
    //insere valores gerados na tabela, e retorna numero de colisoes
    public static long inserir_tabela(DoubleChainedHash tabela, Registro[] vetor) {
        long total_colisoes = 0;
        for (Registro registro : vetor) {
            total_colisoes += tabela.inserirRegistro(registro);
        }
        return total_colisoes;
    }

    //busca todos os valores do vetor na tabela
    public static void buscar_tabela(HashLinear tabela, Registro[] vetor) {
        for (Registro registro : vetor) {
            tabela.buscarRegistro(registro);
        }
    }
    
    //busca todos os valores do vetor na tabela
    public static void buscar_tabela(HashEncadeado tabela, Registro[] vetor) {
        for (Registro registro : vetor) {
            tabela.buscarRegistro(registro);
        }
    }
    
    //busca todos os valores do vetor na tabela
    public static void buscar_tabela(DoubleChainedHash tabela, Registro[] vetor) {
        for (Registro registro : vetor) {
            tabela.buscarRegistro(registro);
        }
    }

    //considerando uma lista de tamanho 3, insere lista em ordem decrescente de tamanho
    public static ListaEncadeada[] inserir_sort_listas(ListaEncadeada[] listas, ListaEncadeada novaLista) {
        if (listas[0] == null) {
            listas[0] = novaLista;
        } else if (listas[1] == null) {
            if (novaLista.getQuantNodes() > listas[0].getQuantNodes()) {
                listas[1] = listas[0];
                listas[0] = novaLista;
            } else {
                listas[1] = novaLista;
            } 
        } else if (listas[2] == null) {
            if (novaLista.getQuantNodes() <= listas[1].getQuantNodes()) {
                listas[2] = novaLista;
            } else if (novaLista.getQuantNodes() <= listas[0].getQuantNodes()) {
                listas[2] = listas[1];
                listas[1] = novaLista;
            } else {
                listas[2] = listas[1];
                listas[1] = listas[0];
                listas[0] = novaLista; 
            }
        } else {
            if (!(novaLista.getQuantNodes() <= listas[2].getQuantNodes())) {
                if (novaLista.getQuantNodes() <= listas[1].getQuantNodes()) {
                    listas[2] = novaLista;
                } else if (novaLista.getQuantNodes() <= listas[0].getQuantNodes()) {
                    listas[2] = listas[1];
                    listas[1] = novaLista;
                } else {
                    listas[2] = listas[1];
                    listas[1] = listas[0];
                    listas[0] = novaLista;
                }
            }
        }
        return listas;
    }

    //retorna 3 maiores listas da tabela
    public static ListaEncadeada[] encontrar_maiores_listas(ListaEncadeada[] listas) {
        ListaEncadeada[] maiores_listas = new ListaEncadeada[3];
        for (ListaEncadeada lista : listas) {
            maiores_listas = inserir_sort_listas(maiores_listas, lista);
        }
        return maiores_listas;
    }

    //retorna maior gap de uma tabela
    public static int encontrar_maior_gap(int[] gaps) {
        int maior = gaps[0];
        for (int gap : gaps) {
            if (gap > maior)
                maior = gap;
        }
        return maior;
    }

    //retorna menor gap de uma tabela
    public static int encontrar_menor_gap(int[] gaps) {
        int menor = gaps[0];
        for (int gap : gaps) {
            if (gap < menor)
                menor = gap;
        }
        return menor;
    }

    //retorna média de gaps de uma tabela
    public static float encontrar_media_gaps(int[] gaps) {
        int quant_gaps = 0;
        int soma_gaps = 0;
        for (int gap : gaps) {
            quant_gaps++;
            soma_gaps += gap;
        }
        return (soma_gaps / quant_gaps);
    }


    //---INICIA EXECUÇÃO DE TODAS AS TABELAS E CALCULOS DE PERFORMANCE---
    public static void main(String[] args) {
        GeradorValores gerador_valores = new GeradorValores(1234);
        
        Registro[] vetor_dez_mil = gerador_valores.gerarDezMil();
        Registro[] vetor_cem_mil = gerador_valores.gerarCemMil();
        Registro[] vetor_quinhentos_mil = gerador_valores.gerarQuinhentosMil();
        long tempo_inicio;

        // ##########################
        // Hash Linear
        // ##########################

        System.out.println("\nHash Linear\n");
        System.out.println("Iniciando insercao de cem mil valores nas tabelas de HashLinear");



        long[] tempo_insercao_linear_100K = new long[3]; // [mil, dezMil, cem_MIL]
        long[] colisoes_insercao_linear_100K = new long[3]; // [mil, dezMil, cem_MIL]



        HashLinear tabela_linear_mil_100K = new HashLinear(MIL);
        HashLinear tabela_linear_dezMil_100K = new HashLinear(DEZ_MIL);
        HashLinear tabela_linear_cemMil_100K = new HashLinear(CEM_MIL);

        

        tempo_inicio = System.nanoTime();
        colisoes_insercao_linear_100K[0] = inserir_tabela(tabela_linear_mil_100K, vetor_cem_mil);
        tempo_insercao_linear_100K[0] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        colisoes_insercao_linear_100K[1] = inserir_tabela(tabela_linear_dezMil_100K, vetor_cem_mil);
        tempo_insercao_linear_100K[1] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        colisoes_insercao_linear_100K[2] = inserir_tabela(tabela_linear_cemMil_100K, vetor_cem_mil);
        tempo_insercao_linear_100K[2] = System.nanoTime() - tempo_inicio;


        System.out.println("SUCESSO!");
        System.out.println("Iniciando insercao de quinhentos mil valores nas tabelas de HashLinear");



        long[] tempo_insercao_linear_500K = new long[3]; // [mil, dezMil, cem_MIL]
        long[] colisoes_insercao_linear_500K = new long[3]; // [mil, dezMil, cem_MIL]



        HashLinear tabela_linear_mil_500K = new HashLinear(MIL);
        HashLinear tabela_linear_dezMil_500K = new HashLinear(DEZ_MIL);
        HashLinear tabela_linear_cemMil_500K = new HashLinear(CEM_MIL);

        

        tempo_inicio = System.nanoTime();
        colisoes_insercao_linear_500K[0] = inserir_tabela(tabela_linear_mil_500K, vetor_quinhentos_mil);
        tempo_insercao_linear_500K[0] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        colisoes_insercao_linear_500K[1] = inserir_tabela(tabela_linear_dezMil_500K, vetor_quinhentos_mil);
        tempo_insercao_linear_500K[1] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        colisoes_insercao_linear_500K[2] = inserir_tabela(tabela_linear_cemMil_500K, vetor_quinhentos_mil);
        tempo_insercao_linear_500K[2] = System.nanoTime() - tempo_inicio;


        System.out.println("SUCESSO!");
        System.out.println("Iniciando insercao de dez mil valores nas tabelas de HashLinear");



        long[] tempo_insercao_linear_10K = new long[3]; // [mil, dezMil, cem_MIL]
        long[] colisoes_insercao_linear_10K = new long[3]; // [mil, dezMil, cem_MIL]



        HashLinear tabela_linear_mil_10K = new HashLinear(MIL);
        HashLinear tabela_linear_dezMil_10K = new HashLinear(DEZ_MIL);
        HashLinear tabela_linear_cemMil_10K = new HashLinear(CEM_MIL);

        tempo_inicio = System.nanoTime();
        colisoes_insercao_linear_10K[0] = inserir_tabela(tabela_linear_mil_10K, vetor_dez_mil);
        tempo_insercao_linear_10K[0] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        colisoes_insercao_linear_10K[1] = inserir_tabela(tabela_linear_dezMil_10K, vetor_dez_mil);
        tempo_insercao_linear_10K[1] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        colisoes_insercao_linear_10K[2] = inserir_tabela(tabela_linear_cemMil_10K, vetor_dez_mil);
        tempo_insercao_linear_10K[2] = System.nanoTime() - tempo_inicio;

        
        System.out.println("SUCESSO!");








        System.out.println("Iniciando busca de cem mil valores nas tabelas de HashLinear");



        long[] tempo_busca_linear_100K = new long[3]; // [mil, dezMil, cem_MIL]



        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_linear_mil_100K, vetor_cem_mil);
        tempo_busca_linear_100K[0] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_linear_dezMil_100K, vetor_cem_mil);
        tempo_busca_linear_100K[1] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_linear_cemMil_100K, vetor_cem_mil);
        tempo_busca_linear_100K[2] = System.nanoTime() - tempo_inicio;

        
        System.out.println("SUCESSO!");
        System.out.println("Iniciando busca de quinhentos mil valores nas tabelas de HashLinear");



        long[] tempo_busca_linear_500K = new long[3]; // [mil, dezMil, cem_MIL]



        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_linear_mil_500K, vetor_quinhentos_mil);
        tempo_busca_linear_500K[0] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_linear_dezMil_500K, vetor_quinhentos_mil);
        tempo_busca_linear_500K[1] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_linear_cemMil_500K, vetor_quinhentos_mil);
        tempo_busca_linear_500K[2] = System.nanoTime() - tempo_inicio;

        
        System.out.println("SUCESSO!");
        System.out.println("Iniciando busca de dez mil valores nas tabelas de HashLinear");



        long[] tempo_busca_linear_10K = new long[3]; // [mil, dezMil, cem_MIL]



        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_linear_mil_10K, vetor_dez_mil);
        tempo_busca_linear_10K[0] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_linear_dezMil_10K, vetor_dez_mil);
        tempo_busca_linear_10K[1] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_linear_cemMil_10K, vetor_dez_mil);
        tempo_busca_linear_10K[2] = System.nanoTime() - tempo_inicio;

        
        System.out.println("SUCESSO!");








        // ##########################
        // Hash Encadeado
        // ##########################



        System.out.println("\nHash Encadeado\n");
        System.out.println("Iniciando insercao de cem mil valores nas tabelas de HashEncadeado");
        
        
        
        long[] tempo_insercao_encadeado_100K = new long[3]; // [mil, dezMil, cem_MIL]
        long[] colisoes_insercao_encadeado_100K = new long[3]; // [mil, dezMil, cem_MIL]



        HashEncadeado tabela_encadeado_mil_100K = new HashEncadeado(MIL);
        HashEncadeado tabela_encadeado_dezMil_100K = new HashEncadeado(DEZ_MIL);
        HashEncadeado tabela_encadeado_cem_MIL_100K = new HashEncadeado(CEM_MIL);

        tempo_inicio = System.nanoTime();
        colisoes_insercao_encadeado_100K[0] = inserir_tabela(tabela_encadeado_mil_100K, vetor_cem_mil);
        tempo_insercao_encadeado_100K[0] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        colisoes_insercao_encadeado_100K[1] = inserir_tabela(tabela_encadeado_dezMil_100K, vetor_cem_mil);
        tempo_insercao_encadeado_100K[1] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        colisoes_insercao_encadeado_100K[2] = inserir_tabela(tabela_encadeado_cem_MIL_100K, vetor_cem_mil);
        tempo_insercao_encadeado_100K[2] = System.nanoTime() - tempo_inicio;


        System.out.println("SUCESSO!");
        System.out.println("Iniciando insercao de quinhentos mil valores nas tabelas de HashEncadeado");
        
        
        
        long[] tempo_insercao_encadeado_500K = new long[3]; // [mil, dezMil, cem_MIL]
        long[] colisoes_insercao_encadeado_500K = new long[3]; // [mil, dezMil, cem_MIL]



        HashEncadeado tabela_encadeado_mil_500K = new HashEncadeado(MIL);
        HashEncadeado tabela_encadeado_dezMil_500K = new HashEncadeado(DEZ_MIL);
        HashEncadeado tabela_encadeado_cem_MIL_500K = new HashEncadeado(CEM_MIL);

        tempo_inicio = System.nanoTime();
        colisoes_insercao_encadeado_500K[0] = inserir_tabela(tabela_encadeado_mil_500K, vetor_quinhentos_mil);
        tempo_insercao_encadeado_500K[0] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        colisoes_insercao_encadeado_500K[1] = inserir_tabela(tabela_encadeado_dezMil_500K, vetor_quinhentos_mil);
        tempo_insercao_encadeado_500K[1] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        colisoes_insercao_encadeado_500K[2] = inserir_tabela(tabela_encadeado_cem_MIL_500K, vetor_quinhentos_mil);
        tempo_insercao_encadeado_500K[2] = System.nanoTime() - tempo_inicio;


        System.out.println("SUCESSO!");
        System.out.println("Iniciando insercao de dez mil valores nas tabelas de HashEncadeado");
        
        
        
        long[] tempo_insercao_encadeado_10K = new long[3]; // [mil, dezMil, cem_MIL]
        long[] colisoes_insercao_encadeado_10K = new long[3]; // [mil, dezMil, cem_MIL]



        HashEncadeado tabela_encadeado_mil_10K = new HashEncadeado(MIL);
        HashEncadeado tabela_encadeado_dezMil_10K = new HashEncadeado(DEZ_MIL);
        HashEncadeado tabela_encadeado_cem_MIL_10K = new HashEncadeado(CEM_MIL);

        tempo_inicio = System.nanoTime();
        colisoes_insercao_encadeado_10K[0] = inserir_tabela(tabela_encadeado_mil_10K, vetor_dez_mil);
        tempo_insercao_encadeado_10K[0] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        colisoes_insercao_encadeado_10K[1] = inserir_tabela(tabela_encadeado_dezMil_10K, vetor_dez_mil);
        tempo_insercao_encadeado_10K[1] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        colisoes_insercao_encadeado_10K[2] = inserir_tabela(tabela_encadeado_cem_MIL_10K, vetor_dez_mil);
        tempo_insercao_encadeado_10K[2] = System.nanoTime() - tempo_inicio;


        System.out.println("SUCESSO!");








        System.out.println("Iniciando busca de cem mil valores nas tabelas de HashEncadeado");


        
        long[] tempo_busca_encadeado_100K = new long[3]; // [mil, dezMil, cem_MIL]



        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_encadeado_mil_100K, vetor_cem_mil);
        tempo_busca_encadeado_100K[0] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_encadeado_dezMil_100K, vetor_cem_mil);
        tempo_busca_encadeado_100K[1] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_encadeado_cem_MIL_100K, vetor_cem_mil);
        tempo_busca_encadeado_100K[2] = System.nanoTime() - tempo_inicio;

        
        System.out.println("SUCESSO!");
        System.out.println("Iniciando busca de quinhentos mil de valores nas tabelas de HashEncadeado");


        
        long[] tempo_busca_encadeado_500K = new long[3]; // [mil, dezMil, cem_MIL]



        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_encadeado_mil_500K, vetor_quinhentos_mil);
        tempo_busca_encadeado_500K[0] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_encadeado_dezMil_500K, vetor_quinhentos_mil);
        tempo_busca_encadeado_500K[1] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_encadeado_cem_MIL_500K, vetor_quinhentos_mil);
        tempo_busca_encadeado_500K[2] = System.nanoTime() - tempo_inicio;

        
        System.out.println("SUCESSO!");
        System.out.println("Iniciando busca de dez mil valores nas tabelas de HashEncadeado");


        
        long[] tempo_busca_encadeado_10K = new long[3]; // [mil, dezMil, cem_MIL]



        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_encadeado_mil_10K, vetor_dez_mil);
        tempo_busca_encadeado_10K[0] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_encadeado_dezMil_10K, vetor_dez_mil);
        tempo_busca_encadeado_10K[1] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_encadeado_cem_MIL_10K, vetor_dez_mil);
        tempo_busca_encadeado_10K[2] = System.nanoTime() - tempo_inicio;

        
        System.out.println("SUCESSO!");








        // ##########################
        // Hash Double Chain
        // ##########################



        System.out.println("\nHash Double Chain\n");
        System.out.println("Iniciando insercao de cem mil valores nas tabelas de HashDoublbeChain");
        
        
        
        long[] tempo_insercao_doublechained_100K = new long[3]; // [mil, dezMil, cem_MIL]
        long[] colisoes_insercao_doublechained_100K = new long[3]; // [mil, dezMil, cem_MIL]



        DoubleChainedHash tabela_doublechained_mil_100K = new DoubleChainedHash(MIL);
        DoubleChainedHash tabela_doublechained_dezMil_100K = new DoubleChainedHash(DEZ_MIL);
        DoubleChainedHash tabela_doublechained_cem_MIL_100K = new DoubleChainedHash(CEM_MIL);

        tempo_inicio = System.nanoTime();
        colisoes_insercao_doublechained_100K[0] = inserir_tabela(tabela_doublechained_mil_100K, vetor_cem_mil);
        tempo_insercao_doublechained_100K[0] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        colisoes_insercao_doublechained_100K[1] = inserir_tabela(tabela_doublechained_dezMil_100K, vetor_cem_mil);
        tempo_insercao_doublechained_100K[1] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        colisoes_insercao_doublechained_100K[2] = inserir_tabela(tabela_doublechained_cem_MIL_100K, vetor_cem_mil);
        tempo_insercao_doublechained_100K[2] = System.nanoTime() - tempo_inicio;


        System.out.println("SUCESSO!");
        System.out.println("Iniciando insercao de quinhentos mil valores nas tabelas de DoubleChainedHash");
        
        
        
        long[] tempo_insercao_doublechained_500K = new long[3]; // [mil, dezMil, cem_MIL]
        long[] colisoes_insercao_doublechained_500K = new long[3]; // [mil, dezMil, cem_MIL]



        DoubleChainedHash tabela_doublechained_mil_500K = new DoubleChainedHash(MIL);
        DoubleChainedHash tabela_doublechained_dezMil_500K = new DoubleChainedHash(DEZ_MIL);
        DoubleChainedHash tabela_doublechained_cem_MIL_500K = new DoubleChainedHash(CEM_MIL);

        tempo_inicio = System.nanoTime();
        colisoes_insercao_doublechained_500K[0] = inserir_tabela(tabela_doublechained_mil_500K, vetor_quinhentos_mil);
        tempo_insercao_doublechained_500K[0] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        colisoes_insercao_doublechained_500K[1] = inserir_tabela(tabela_doublechained_dezMil_500K, vetor_quinhentos_mil);
        tempo_insercao_doublechained_500K[1] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        colisoes_insercao_doublechained_500K[2] = inserir_tabela(tabela_doublechained_cem_MIL_500K, vetor_quinhentos_mil);
        tempo_insercao_doublechained_500K[2] = System.nanoTime() - tempo_inicio;


        System.out.println("SUCESSO!");
        System.out.println("Iniciando insercao de dez mil valores nas tabelas de DoubleChainedHash");
        
        
        
        long[] tempo_insercao_doublechained_10K = new long[3]; // [mil, dezMil, cem_MIL]
        long[] colisoes_insercao_doublechained_10K = new long[3];



        DoubleChainedHash tabela_doublechained_mil_10K = new DoubleChainedHash(MIL);
        DoubleChainedHash tabela_doublechained_dezMil_10K = new DoubleChainedHash(DEZ_MIL);
        DoubleChainedHash tabela_doublechained_cem_MIL_10K = new DoubleChainedHash(CEM_MIL);

        tempo_inicio = System.nanoTime();
        colisoes_insercao_doublechained_10K[0] = inserir_tabela(tabela_doublechained_mil_10K, vetor_dez_mil);
        tempo_insercao_doublechained_10K[0] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        colisoes_insercao_doublechained_10K[1] = inserir_tabela(tabela_doublechained_dezMil_10K, vetor_dez_mil);
        tempo_insercao_doublechained_10K[1] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        colisoes_insercao_doublechained_10K[2] = inserir_tabela(tabela_doublechained_cem_MIL_10K, vetor_dez_mil);
        tempo_insercao_doublechained_10K[2] = System.nanoTime() - tempo_inicio;


        System.out.println("SUCESSO!");








        System.out.println("Iniciando busca de cem mil valores nas tabelas de DoubleChainedHash");


        
        long[] tempo_busca_doublechained_100K = new long[3]; // [mil, dezMil, cem_MIL]



        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_doublechained_mil_100K, vetor_cem_mil);
        tempo_busca_doublechained_100K[0] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_doublechained_dezMil_100K, vetor_cem_mil);
        tempo_busca_doublechained_100K[1] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_doublechained_cem_MIL_100K, vetor_cem_mil);
        tempo_busca_doublechained_100K[2] = System.nanoTime() - tempo_inicio;

        
        System.out.println("SUCESSO!");
        System.out.println("Iniciando busca de quinhentos mil valores nas tabelas de DoubleChainedHash");


        
        long[] tempo_busca_doublechained_500K = new long[3]; // [mil, dezMil, cem_MIL]



        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_doublechained_mil_500K, vetor_quinhentos_mil);
        tempo_busca_doublechained_500K[0] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_doublechained_dezMil_500K, vetor_quinhentos_mil);
        tempo_busca_doublechained_500K[1] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_doublechained_cem_MIL_500K, vetor_quinhentos_mil);
        tempo_busca_doublechained_500K[2] = System.nanoTime() - tempo_inicio;

        
        System.out.println("SUCESSO!");
        System.out.println("Iniciando busca de dez mil valores nas tabelas de DoubleChainedHash");


        
        long[] tempo_busca_doublechained_10K = new long[3]; // [mil, dezMil, cem_MIL]



        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_doublechained_mil_10K, vetor_dez_mil);
        tempo_busca_doublechained_10K[0] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_doublechained_dezMil_10K, vetor_dez_mil);
        tempo_busca_doublechained_10K[1] = System.nanoTime() - tempo_inicio;

        tempo_inicio = System.nanoTime();
        buscar_tabela(tabela_doublechained_cem_MIL_10K, vetor_dez_mil);
        tempo_busca_doublechained_10K[2] = System.nanoTime() - tempo_inicio;

        
        System.out.println("SUCESSO!");

        ListaEncadeada[] maiores_listas_encadeado_mil_100K = tabela_encadeado_mil_100K.maiores_listas();
        ListaEncadeada[] maiores_listas_encadeado_dezMil_100K = tabela_encadeado_dezMil_100K.maiores_listas();
        ListaEncadeada[] maiores_listas_encadeado_cem_MIL_100K = tabela_encadeado_cem_MIL_100K.maiores_listas();

        ListaEncadeada[] maiores_listas_encadeado_mil_500K = tabela_encadeado_mil_500K.maiores_listas();
        ListaEncadeada[] maiores_listas_encadeado_dezMil_500K = tabela_encadeado_dezMil_500K.maiores_listas();
        ListaEncadeada[] maiores_listas_encadeado_cem_MIL_500K = tabela_encadeado_cem_MIL_500K.maiores_listas();

        ListaEncadeada[] maiores_listas_encadeado_mil_10K = tabela_encadeado_mil_10K.maiores_listas();
        ListaEncadeada[] maiores_listas_encadeado_dezMil_10K = tabela_encadeado_dezMil_10K.maiores_listas();
        ListaEncadeada[] maiores_listas_encadeado_cem_MIL_10K = tabela_encadeado_cem_MIL_10K.maiores_listas();

        // gaps linear

        int[] gaps_linear_mil_100K = tabela_linear_mil_100K.encontrar_gaps();
        int[] gaps_linear_dezMil_100K = tabela_linear_dezMil_100K.encontrar_gaps();
        int[] gaps_linear_cem_MIL_100K = tabela_linear_cemMil_100K.encontrar_gaps();

        int[] gaps_linear_mil_500K = tabela_linear_mil_500K.encontrar_gaps();
        int[] gaps_linear_dezMil_500K = tabela_linear_dezMil_500K.encontrar_gaps();
        int[] gaps_linear_cem_MIL_500K = tabela_linear_cemMil_500K.encontrar_gaps();

        int[] gaps_linear_mil_10K = tabela_linear_mil_10K.encontrar_gaps();
        int[] gaps_linear_dezMil_10K = tabela_linear_dezMil_10K.encontrar_gaps();
        int[] gaps_linear_cem_MIL_10K = tabela_linear_cemMil_10K.encontrar_gaps();

        System.out.println("Resultados finais: \n");
        System.out.println("Dados da inserção: ");

        System.out.println("\n\nTempo de inserção linear com 10 mil valores: ");
        System.out.println("Tabela de tamanho mil: " + tempo_insercao_linear_10K[0]);
        System.out.println("Tabela de tamanho dez mil: " + tempo_insercao_linear_10K[1]);
        System.out.println("Tabela de tamanho cem mil: " + tempo_insercao_linear_10K[2]);

        System.out.println("\nTempo de inserção linear com 100 mil valores: ");
        System.out.println("Tabela de tamanho mil: " + tempo_insercao_linear_100K[0]);
        System.out.println("Tabela de tamanho dez mil: " + tempo_insercao_linear_100K[1]);
        System.out.println("Tabela de tamanho cem mil: " + tempo_insercao_linear_100K[2]);

        System.out.println("\nTempo de inserção linear com 500 mil de valores: ");
        System.out.println("Tabela de tamanho mil: " + tempo_insercao_linear_500K[0]);
        System.out.println("Tabela de tamanho dez mil: " + tempo_insercao_linear_500K[1]);
        System.out.println("Tabela de tamanho cem mil: " + tempo_insercao_linear_500K[2]);



        System.out.println("\n\nTempos de inserção encadeado com 10 mil valores: ");
        System.out.println("Tabela de tamanho mil: " + tempo_insercao_encadeado_10K[0]);
        System.out.println("Tabela de tamanho dez mil: " + tempo_insercao_encadeado_10K[1]);
        System.out.println("Tabela de tamanho cem mil: " + tempo_insercao_encadeado_10K[2]);
        
        System.out.println("\nTempos de inserção encadeado com 100 mil valores: ");
        System.out.println("Tabela de tamanho mil: " + tempo_insercao_encadeado_100K[0]);
        System.out.println("Tabela de tamanho dez mil: " + tempo_insercao_encadeado_100K[1]);
        System.out.println("Tabela de tamanho cem mil: " + tempo_insercao_encadeado_100K[2]);
        
        System.out.println("\nTempos de inserção encadeado com 500 mil de valores: ");
        System.out.println("Tabela de tamanho mil: " + tempo_insercao_encadeado_500K[0]);
        System.out.println("Tabela de tamanho dez mil: " + tempo_insercao_encadeado_500K[1]);
        System.out.println("Tabela de tamanho cem mil: " + tempo_insercao_encadeado_500K[2]);
        


        System.out.println("\n\nTempo de inserção double chained com 10 mil valores: ");
        System.out.println("Tabela de tamanho mil: " + tempo_insercao_doublechained_10K[0]);
        System.out.println("Tabela de tamanho dez mil: " + tempo_insercao_doublechained_10K[1]);
        System.out.println("Tabela de tamanho cem mil: " + tempo_insercao_doublechained_10K[2]);
        
        System.out.println("\nTempo de inserção double chained com 100 mil valores: ");
        System.out.println("Tabela de tamanho mil: " + tempo_insercao_doublechained_100K[0]);
        System.out.println("Tabela de tamanho dez mil: " + tempo_insercao_doublechained_100K[1]);
        System.out.println("Tabela de tamanho cem mil: " + tempo_insercao_doublechained_100K[2]);
        
        System.out.println("\nTempo de inserção double chained com 500 mil de valores: ");
        System.out.println("Tabela de tamanho mil: " + tempo_insercao_doublechained_500K[0]);
        System.out.println("Tabela de tamanho dez mil: " + tempo_insercao_doublechained_500K[1]);
        System.out.println("Tabela de tamanho cem mil: " + tempo_insercao_doublechained_500K[2]);

        



        System.out.println("\n\nDados de colisões: \n\n");
        
        
        System.out.println("Número de colisões na inserção linear com 10 mil valores: ");
        System.out.println("Tabela de tamanho mil: " + colisoes_insercao_linear_10K[0]);
        System.out.println("Tabela de tamanho dez mil: " + colisoes_insercao_linear_10K[1]);
        System.out.println("Tabela de tamanho cem mil: " + colisoes_insercao_linear_10K[2]);
        
        System.out.println("\nNúmero de colisões na inserção linear com 100 mil valores: ");
        System.out.println("Tabela de tamanho mil: " + colisoes_insercao_linear_100K[0]);
        System.out.println("Tabela de tamanho dez mil: " + colisoes_insercao_linear_100K[1]);
        System.out.println("Tabela de tamanho cem mil: " + colisoes_insercao_linear_100K[2]);
        
        System.out.println("\nNúmero de colisões na inserção linear com 500 mil de valores: ");
        System.out.println("Tabela de tamanho mil: " + colisoes_insercao_linear_500K[0]);
        System.out.println("Tabela de tamanho dez mil: " + colisoes_insercao_linear_500K[1]);
        System.out.println("Tabela de tamanho cem mil: " + colisoes_insercao_linear_500K[2]);
        
        
        
        System.out.println("\n\nNúmero de colisões na inserção encadeada com 10 mil valores: ");
        System.out.println("Tabela de tamanho mil: " + colisoes_insercao_encadeado_10K[0]);
        System.out.println("Tabela de tamanho dez mil: " + colisoes_insercao_encadeado_10K[1]);
        System.out.println("Tabela de tamanho cem mil: " + colisoes_insercao_encadeado_10K[2]);
        
        System.out.println("\nNúmero de colisões na inserção encadeada com 100 mil valores: ");
        System.out.println("Tabela de tamanho mil: " + colisoes_insercao_encadeado_100K[0]);
        System.out.println("Tabela de tamanho dez mil: " + colisoes_insercao_encadeado_100K[1]);
        System.out.println("Tabela de tamanho cem mil: " + colisoes_insercao_encadeado_100K[2]);
        
        System.out.println("\nNúmero de colisões na inserção encadeada com 500 mil de valores: ");
        System.out.println("Tabela de tamanho mil: " + colisoes_insercao_encadeado_500K[0]);
        System.out.println("Tabela de tamanho dez mil: " + colisoes_insercao_encadeado_500K[1]);
        System.out.println("Tabela de tamanho cem mil: " + colisoes_insercao_encadeado_500K[2]);
        


        System.out.println("\n\nNúmero de colisões na inserção doublechained com 10 mil valores: ");
        System.out.println("Tabela de tamanho mil: " + colisoes_insercao_doublechained_10K[0]);
        System.out.println("Tabela de tamanho dez mil: " + colisoes_insercao_doublechained_10K[1]);
        System.out.println("Tabela de tamanho cem mil: " + colisoes_insercao_doublechained_10K[2]);
        
        System.out.println("\nNúmero de colisões na inserção doublechained com 100 mil valores: ");
        System.out.println("Tabela de tamanho mil: " + colisoes_insercao_doublechained_100K[0]);
        System.out.println("Tabela de tamanho dez mil: " + colisoes_insercao_doublechained_100K[1]);
        System.out.println("Tabela de tamanho cem mil: " + colisoes_insercao_doublechained_100K[2]);
        
        System.out.println("\nNúmero de colisões na inserção doublechained com 500 mil de valores: ");
        System.out.println("Tabela de tamanho mil: " + colisoes_insercao_doublechained_500K[0]);
        System.out.println("Tabela de tamanho dez mil: " + colisoes_insercao_doublechained_500K[1]);
        System.out.println("Tabela de tamanho cem mil: " + colisoes_insercao_doublechained_500K[2]);
        
        
        
        System.out.println("\n\nDados de busca: \n\n");
        
        
        System.out.println("Tempo da busca linear com 10 mil valores: ");
        System.out.println("Tabela de tamanho mil: " + tempo_busca_linear_10K[0]);
        System.out.println("Tabela de tamanho dez mil: " + tempo_busca_linear_10K[1]);
        System.out.println("Tabela de tamanho cem mil: " + tempo_busca_linear_10K[2]);
        
        System.out.println("\nTempo da busca linear com 100 mil valores: ");
        System.out.println("Tabela de tamanho mil: " + tempo_busca_linear_100K[0]);
        System.out.println("Tabela de tamanho dez mil: " + tempo_busca_linear_100K[1]);
        System.out.println("Tabela de tamanho cem mil: " + tempo_busca_linear_100K[2]);
        
        System.out.println("\nTempo da busca linear com 500 mil de valores: ");
        System.out.println("Tabela de tamanho mil: " + tempo_busca_linear_500K[0]);
        System.out.println("Tabela de tamanho dez mil: " + tempo_busca_linear_500K[1]);
        System.out.println("Tabela de tamanho cem mil: " + tempo_busca_linear_500K[2]);
        
        
        
        System.out.println("\n\nTempo da busca encadeada com 10 mil valores: ");
        System.out.println("Tabela de tamanho mil: " + tempo_busca_encadeado_10K[0]);
        System.out.println("Tabela de tamanho dez mil: " + tempo_busca_encadeado_10K[1]);
        System.out.println("Tabela de tamanho cem mil: " + tempo_busca_encadeado_10K[2]);
        
        System.out.println("\nTempo da busca encadeada com 100 mil valores: ");
        System.out.println("Tabela de tamanho mil: " + tempo_busca_encadeado_100K[0]);
        System.out.println("Tabela de tamanho dez mil: " + tempo_busca_encadeado_100K[1]);
        System.out.println("Tabela de tamanho cem mil: " + tempo_busca_encadeado_100K[2]);
        
        System.out.println("\nTempo da busca encadeada com 500 mil de valores: ");
        System.out.println("Tabela de tamanho mil: " + tempo_busca_encadeado_500K[0]);
        System.out.println("Tabela de tamanho dez mil: " + tempo_busca_encadeado_500K[1]);
        System.out.println("Tabela de tamanho cem mil: " + tempo_busca_encadeado_500K[2]);
        


        System.out.println("\n\nTempo da busca doublechained com 10 mil valores: ");
        System.out.println("Tabela de tamanho mil: " + tempo_busca_doublechained_10K[0]);
        System.out.println("Tabela de tamanho dez mil: " + tempo_busca_doublechained_10K[1]);
        System.out.println("Tabela de tamanho cem mil: " + tempo_busca_doublechained_10K[2]);
        
        System.out.println("\nTempo da busca doublechained com 100 mil valores: ");
        System.out.println("Tabela de tamanho mil: " + tempo_busca_doublechained_100K[0]);
        System.out.println("Tabela de tamanho dez mil: " + tempo_busca_doublechained_100K[1]);
        System.out.println("Tabela de tamanho cem mil: " + tempo_busca_doublechained_100K[2]);
        
        System.out.println("\nTempo da busca doublechained com 500 mil de valores: ");
        System.out.println("Tabela de tamanho mil: " + tempo_busca_doublechained_500K[0]);
        System.out.println("Tabela de tamanho dez mil: " + tempo_busca_doublechained_500K[1]);
        System.out.println("Tabela de tamanho cem mil: " + tempo_busca_doublechained_500K[2]);



        System.out.println("\n\nMaiores listas encadeadas: \n\n");


        System.out.println("\nMaiores listas encadeadas 10 mil valores:");
        System.out.print("\n\nTabela de tamanho mil: 1. "); maiores_listas_encadeado_mil_10K[0].imprimir();
        System.out.print("\n\nTabela de tamanho mil: 2. "); maiores_listas_encadeado_mil_10K[1].imprimir();
        System.out.print("\n\nTabela de tamanho mil: 3. "); maiores_listas_encadeado_mil_10K[2].imprimir();
        System.out.print("\n\nTabela de tamanho dez mil: 1. "); maiores_listas_encadeado_dezMil_10K[0].imprimir();
        System.out.print("\n\nTabela de tamanho dez mil: 2. "); maiores_listas_encadeado_dezMil_10K[1].imprimir();
        System.out.print("\n\nTabela de tamanho dez mil: 3. "); maiores_listas_encadeado_dezMil_10K[2].imprimir();
        System.out.print("\n\nTabela de tamanho cem mil: 1. "); maiores_listas_encadeado_cem_MIL_10K[0].imprimir();
        System.out.print("\n\nTabela de tamanho cem mil: 2. "); maiores_listas_encadeado_cem_MIL_10K[1].imprimir();
        System.out.print("\n\nTabela de tamanho cem mil: 3. "); maiores_listas_encadeado_cem_MIL_10K[2].imprimir();
        
        System.out.println("\nMaiores listas encadeadas 100 mil valores:");
        System.out.print("\n\nTabela de tamanho mil: 1. "); maiores_listas_encadeado_mil_100K[0].imprimir();
        System.out.print("\n\nTabela de tamanho mil: 2. "); maiores_listas_encadeado_mil_100K[1].imprimir();
        System.out.print("\n\nTabela de tamanho mil: 3. "); maiores_listas_encadeado_mil_100K[2].imprimir();
        System.out.print("\n\nTabela de tamanho dez mil: 1. "); maiores_listas_encadeado_dezMil_100K[0].imprimir();
        System.out.print("\n\nTabela de tamanho dez mil: 2. "); maiores_listas_encadeado_dezMil_100K[1].imprimir();
        System.out.print("\n\nTabela de tamanho dez mil: 3. "); maiores_listas_encadeado_dezMil_100K[2].imprimir();
        System.out.print("\n\nTabela de tamanho cem mil: 1. "); maiores_listas_encadeado_cem_MIL_100K[0].imprimir();
        System.out.print("\n\nTabela de tamanho cem mil: 2. "); maiores_listas_encadeado_cem_MIL_100K[1].imprimir();
        System.out.print("\n\nTabela de tamanho cem mil: 3. "); maiores_listas_encadeado_cem_MIL_100K[2].imprimir();
        
        System.out.println("\nMaiores listas encadeadas 500 mil de valores:");
        System.out.print("\n\nTabela de tamanho mil: 1. "); maiores_listas_encadeado_mil_500K[0].imprimir();
        System.out.print("\n\nTabela de tamanho mil: 2. "); maiores_listas_encadeado_mil_500K[1].imprimir();
        System.out.print("\n\nTabela de tamanho mil: 3. "); maiores_listas_encadeado_mil_500K[2].imprimir();
        System.out.print("\n\nTabela de tamanho dez mil: 1. "); maiores_listas_encadeado_dezMil_500K[0].imprimir();
        System.out.print("\n\nTabela de tamanho dez mil: 2. "); maiores_listas_encadeado_dezMil_500K[1].imprimir();
        System.out.print("\n\nTabela de tamanho dez mil: 3. "); maiores_listas_encadeado_dezMil_500K[2].imprimir();
        System.out.print("\n\nTabela de tamanho cem mil: 1. "); maiores_listas_encadeado_cem_MIL_500K[0].imprimir();
        System.out.print("\n\nTabela de tamanho cem mil: 2. "); maiores_listas_encadeado_cem_MIL_500K[1].imprimir();
        System.out.print("\n\nTabela de tamanho cem mil: 3. "); maiores_listas_encadeado_cem_MIL_500K[2].imprimir();
        
        


        System.out.println("\n\nDados dos gaps: \n\n");

        
        System.out.println("Gaps com 100 mil valores: ");
        System.out.println("Maior gap na tabela de tamanho mil: " + encontrar_maior_gap(gaps_linear_mil_100K));
        System.out.println("Menor gap na tabela de tamanho mil: " + encontrar_menor_gap(gaps_linear_mil_100K));
        System.out.println("Media gap na tabela de tamanho mil: " + encontrar_media_gaps(gaps_linear_mil_100K));
        System.out.println("Maior gap na tabela de tamanho dez mil: " + encontrar_maior_gap(gaps_linear_dezMil_100K));
        System.out.println("Menor gap na tabela de tamanho dez mil: " + encontrar_menor_gap(gaps_linear_dezMil_100K));
        System.out.println("Media gap na tabela de tamanho dez mil: " + encontrar_media_gaps(gaps_linear_dezMil_100K));
        System.out.println("Maior gap na tabela de tamanho cem mil: " + encontrar_maior_gap(gaps_linear_cem_MIL_100K));
        System.out.println("Menor gap na tabela de tamanho cem mil: " + encontrar_menor_gap(gaps_linear_cem_MIL_100K));
        System.out.println("Media gap na tabela de tamanho cem mil: " + encontrar_media_gaps(gaps_linear_cem_MIL_100K));
        
        System.out.println("\nGaps com 500 mil valores: ");
        System.out.println("Maior gap na tabela de tamanho mil: " + encontrar_maior_gap(gaps_linear_mil_500K));
        System.out.println("Menor gap na tabela de tamanho mil: " + encontrar_menor_gap(gaps_linear_mil_500K));
        System.out.println("Media gap na tabela de tamanho mil: " + encontrar_media_gaps(gaps_linear_mil_500K));
        System.out.println("Maior gap na tabela de tamanho dez mil: " + encontrar_maior_gap(gaps_linear_dezMil_500K));
        System.out.println("Menor gap na tabela de tamanho dez mil: " + encontrar_menor_gap(gaps_linear_dezMil_500K));
        System.out.println("Media gap na tabela de tamanho dez mil: " + encontrar_media_gaps(gaps_linear_dezMil_500K));
        System.out.println("Maior gap na tabela de tamanho cem mil: " + encontrar_maior_gap(gaps_linear_cem_MIL_500K));
        System.out.println("Menor gap na tabela de tamanho cem mil: " + encontrar_menor_gap(gaps_linear_cem_MIL_500K));
        System.out.println("Media gap na tabela de tamanho cem mil: " + encontrar_media_gaps(gaps_linear_cem_MIL_500K));
        
        System.out.println("\nGaps com 10 mil valores: ");
        System.out.println("Maior gap na tabela de tamanho mil: " + encontrar_maior_gap(gaps_linear_mil_10K));
        System.out.println("Menor gap na tabela de tamanho mil: " + encontrar_menor_gap(gaps_linear_mil_10K));
        System.out.println("Media gap na tabela de tamanho mil: " + encontrar_media_gaps(gaps_linear_mil_10K));
        System.out.println("Maior gap na tabela de tamanho dez mil: " + encontrar_maior_gap(gaps_linear_dezMil_10K));
        System.out.println("Menor gap na tabela de tamanho dez mil: " + encontrar_menor_gap(gaps_linear_dezMil_10K));
        System.out.println("Media gap na tabela de tamanho dez mil: " + encontrar_media_gaps(gaps_linear_dezMil_10K));
        System.out.println("Maior gap na tabela de tamanho cem mil: " + encontrar_maior_gap(gaps_linear_cem_MIL_10K));
        System.out.println("Menor gap na tabela de tamanho cem mil: " + encontrar_menor_gap(gaps_linear_cem_MIL_10K));
        System.out.println("Media gap na tabela de tamanho cem mil: " + encontrar_media_gaps(gaps_linear_cem_MIL_10K));

    }
}
