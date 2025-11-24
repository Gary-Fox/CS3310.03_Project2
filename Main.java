import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int numVertices = 5;
        int maxWeight = 10;
        // KEEP DENSITY BETWEEN 0.0 AND 1.0, excluive.
        double density = 0.5;

        GraphDW graph = new GraphDW(numVertices);
        graph = GraphDW.randGraphDW(numVertices, maxWeight, density);

        GraphDW graph2 = GraphDW.randGraphDW(10, 50, 0.9);

        printBreak();
        printGraphDW(GraphDW.randGraphDW(10, 50, 0.1));
        printGraphDW(graph2);
        printGraphDW(graph);

        // Running Dijkstra starting from vertex 0
        int source = 0;
        Dijkstra.run(graph, source);
        printBreak();
    }

    public class Dijkstra {
        // using Integer.MAX_VALUE for "no edge"
        public static int[] run(GraphDW g, int src) {
            int n = g.numVertices;
            int[][] w = g.adjMatrix;

            int[] dist = new int[n];
            int[] parent = new int[n];
            boolean[] used = new boolean[n];

            Arrays.fill(dist, Integer.MAX_VALUE);
            Arrays.fill(parent, -1);
            dist[src] = 0;

            for (int it = 0; it < n; it++) {
                // Used to pick unused vertex with the smalest distance
                int u = -1, best = Integer.MAX_VALUE;
                for (int v = 0; v < n; v++) {
                    if (!used[v] && dist[v] < best) {
                        best = dist[v];
                        u = v;
                    }
                }
                if (u == -1)
                    break;
                used[u] = true;

                for (int v = 0; v < n; v++) {
                    int wt = w[u][v];
                    if (wt == Integer.MAX_VALUE)
                        continue; // no edg
                    if (dist[u] == Integer.MAX_VALUE)
                        continue;
                    int nd = dist[u] + wt;
                    if (nd < dist[v]) {
                        dist[v] = nd;
                        parent[v] = u;
                    }
                }
            }

            System.out.println("Dijkstra from " + src + ":");
            for (int v = 0; v < n; v++) {
                System.out.print("to " + v + " = ");
                if (dist[v] == Integer.MAX_VALUE) {
                    System.out.print("inf");
                } else {
                    System.out.print(dist[v]);
                }
                System.out.print(" | path: ");
                printPath(v, parent);
                System.out.println();
            }
            return dist;
        }

        private static void printPath(int v, int[] parent) {
            int[] stack = new int[parent.length + 1];
            int k = 0, cur = v;
            while (cur != -1 && k < stack.length) {
                stack[k++] = cur;
                cur = parent[cur];
            }
            for (int i = k - 1; i >= 0; i--) {
                System.out.print(stack[i]);
                if (i > 0)
                    System.out.print(" -> ");
            }
        }
    }

public static double[][] floydWarshall(double[][] dist) {
    int n = dist.length;
    double[][] dp = new double[n][n];

    // Copy matrix
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            dp[i][j] = dist[i][j];
        }
    }

    // Floyd–Warshall
    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][k] + dp[k][j] < dp[i][j]) {
                    dp[i][j] = dp[i][k] + dp[k][j];
                }
            }
        }
    }

    return dp;
}

    public static void printGraphDW(GraphDW graph) {
        int numVertices = graph.getNumVertices();
        int[][] adjMatrix = graph.getAdjMatrix();

        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j] == Integer.MAX_VALUE) {
                    System.out.print("∞ ");
                } else {
                    System.out.print(adjMatrix[i][j] + " ");
                }
            }
            System.out.println();

        }
        printBreak();
    }

    public static void printBreak() {
        System.out.print("##################################################");
        System.out.println("");
    }

}
