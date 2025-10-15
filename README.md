# PJBL_HASH  
PUC-PR - Bacharelado em Ciência da Computação - Quarto Periodo - Turma A  

Resolução de Problemas Estruturados em Computação - Professor Andrey Cabral Meira  
PJBL HASH - RA3 - 14/10/2025    

ALUNOS: Joaquim dos Anjos Faraco (joaquimFdj), Artur Moretti Zimmermann (artur-mizi) e Bruno Navarro Ivatiuk (BrunoIvatiuk)   

Implementação de três diferentes tabelas de hash como classes Java e avaliação de suas respectivas performances para inserção e busca de conjuntos grandes de valores (até 10 milhões) com visualização em gráficos com MatPlotLib  

## 1: Tamanhos para vetores das tabelas:

1.000, 10.000, 100.000

## 2: Variações de funções Hash:

- Resto da divisão convencional, com o tamanho da tabela como divisor

- Xor entre fatias do número do registro, que também é passado por um resto de divisão

- Multiplicação de cada fatia do número do registro por um número primo, que serão somados e passados por um resto de divisão

- Rehashing linear, que redimensiona a tabela e redistribui seus registros

- Hashing encadeado com duas tabelas, que quando estoura o limite da lista na primeira tabela, realiza outro hashing e redistribui a lista na segunda tabela

## 3: Geração de conjuntos de dados:

Feito na classe GeradorValores.java, conjuntos sao gerados com mesmos valores através de uma seed, contendo 10 mil, 100 mil, e 500 mil.  
Os mesmos valores são inseridos em todas as tabelas.

## 4: Medição de tempo de inserção e de colisões:

Usando System.nanoTime() para marcar o tempo antes e depois de cada inserção, em nanosegundos, colisões foram medidas com um contador que incrementava toda vez que ocorreu um rehash ou foi criado um novo nó nas listas encadeadas.

## 5: Medição do tempo de busca e maiores listas

Usando System.nanoTime() para marcar o tempo antes e depois de cada busca, imprime as maiores listas no main, com os numeros gerados em cada node.

## 6: Gráficos de performance de cada tabela e cada método:

Gráficos criados usando ferramentas do pacote Office, a partir da saída do Main.
