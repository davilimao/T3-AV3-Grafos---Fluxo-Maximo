Trabalho Prático 3 – Fluxo Máximo
Problema

CSES 1711 – Distinct Routes

Link do problema:
https://cses.fi/problemset/task/1711

Integrantes do grupo
Gabriel Rangel Lustosa
Davi Lima de Oliveira Rocha

Equipe: E

Linguagem utilizada
Java
Como executar a solução
Compile o arquivo principal:
javac Main.java
Execute o programa:
java Main
Informe a entrada no formato especificado pelo problema ou redirecione um arquivo:
java Main < entrada.txt

Modelagem como rede de fluxo

O problema foi modelado como uma rede de fluxo em que cada sala representa um vértice e cada teleporte representa uma aresta direcionada com capacidade igual a 1. Dessa forma, um mesmo teleporte só pode ser utilizado por uma única rota.

Origem, sorvedouro, vértices, arestas e capacidades

Origem: sala 1.

Sorvedouro: sala n.

Vértices: salas do jogo.

Arestas: teleportes direcionados.

Capacidades: todas iguais a 1.

Algoritmo utilizado

Foi utilizado o algoritmo Ford-Fulkerson, buscando caminhos aumentantes até que não exista mais caminho entre a origem e o sorvedouro.

Papel do grafo residual

O grafo residual atualiza as capacidades após cada aumento de fluxo e cria arestas reversas, permitindo corrigir escolhas anteriores e garantindo o cálculo do fluxo máximo.

Conversão do fluxo para a resposta

O valor do fluxo máximo representa diretamente o número máximo de dias que o jogo pode ser realizado. As rotas são reconstruídas percorrendo apenas as arestas que receberam fluxo positivo.

Reconstrução dos caminhos

Após calcular o fluxo máximo, cada rota é recuperada utilizando as arestas com fluxo positivo, evitando que uma mesma aresta seja utilizada em mais de um caminho.

Complexidade

Tempo: O(E × Fluxo Máximo)

Memória: O(V + E)

Casos especiais

Não existir caminho entre origem e destino (fluxo igual a 0);
Existirem múltiplas soluções válidas;
Reconstrução correta das rotas sem reutilização de arestas.

Evidência:

<img width="524" height="621" alt="Captura de tela 2026-06-09 142335" src="https://github.com/user-attachments/assets/df6d95ff-def3-4c62-a9df-c3a97c72548c" />
