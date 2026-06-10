import java.io.File;

public class Main {
    public static void main(String[] args) {
        // Caminho especificado para o arquivo de entrada dentro da pasta dados
        String path = "dados" + File.separator + "entradas_do_problema.txt";

        File file = new File(path);
        if (!file.exists()) {
            System.err.println("Erro: O arquivo de entrada não foi encontrado em: " + file.getAbsolutePath());
            return;
        }

        // Inicializa o leitor da biblioteca algs4
        In in = new In(file);

        if (!in.hasNextLine()) {
            System.err.println("Erro: Arquivo vazio.");
            return;
        }

        int n = in.readInt(); // Número de salas
        int m = in.readInt(); // Número de teletransportadores

        // Criamos uma FlowNetwork com n + 1 vértices para usar a indexação de 1 a n diretamente
        FlowNetwork G = new FlowNetwork(n + 1);

        // A origem (source) é a sala 1 e o destino (sink) é a sala n
        int source = 1;
        int sink = n;

        // Lendo os teletransportadores e adicionando as arestas com capacidade 1
        for (int i = 0; i < m; i++) {
            if (!in.hasNextLine()) break;
            int a = in.readInt();
            int b = in.readInt();
            // Capacidade 1 garante que cada teletransportador só pode ser usado uma vez no total
            FlowEdge edge = new FlowEdge(a, b, 1.0);
            G.addEdge(edge);
        }
        in.close();

        // Executa o algoritmo de Ford-Fulkerson
        FordFulkerson maxFlow = new FordFulkerson(G, source, sink);
        int totalDays = (int) maxFlow.value();

        // Imprime o número máximo de dias (fluxo máximo)
        System.out.println(totalDays);

        // Recuperação dos caminhos disjuntos
        for (int d = 0; d < totalDays; d++) {
            Queue<Integer> pathQueue = new Queue<>();
            int current = source;
            pathQueue.enqueue(current);

            while (current != sink) {
                boolean moved = false;
                for (FlowEdge e : G.adj(current)) {
                    // Verifica se a aresta sai do vértice atual e se tem fluxo passando por ela
                    if (e.from() == current && e.flow() > 0.0) {

                        // CORREÇÃO: Enviamos um delta POSITIVO (1.0) na direção do vértice de ORIGEM (e.from())
                        // Na lógica do algs4, isso reduz o fluxo direto da aresta em 1.0 sem violar o 'delta >= 0'
                        e.addResidualFlowTo(e.from(), 1.0);

                        current = e.to();
                        pathQueue.enqueue(current);
                        moved = true;
                        break;
                    }
                }
                if (!moved) {
                    break;
                }
            }

            // Imprime o tamanho do caminho e os vértices visitados
            System.out.println(pathQueue.size());
            StringBuilder sb = new StringBuilder();
            while (!pathQueue.isEmpty()) {
                sb.append(pathQueue.dequeue());
                if (!pathQueue.isEmpty()) {
                    sb.append(" ");
                }
            }
            System.out.println(sb.toString());
        }
    }
}