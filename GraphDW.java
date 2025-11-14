public class GraphDW 
{
    int[][] adjMatrix;
    int numVertices;

    public GraphDW(int numVertices) 
    {
        this.numVertices = numVertices;
        adjMatrix = new int[numVertices][numVertices];

        // Initialize the adjacency matrix with infinity
        for (int i = 0; i < numVertices; i++) 
        {
            for (int j = 0; j < numVertices; j++) 
            {
                if (i == j) 
                {
                    adjMatrix[i][j] = 0;
                } 
                else 
                {
                    adjMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }

    public void addEdge(int src, int dest, int weight) 
    {
        adjMatrix[src][dest] = weight;
    }

    public int getEdge(int src, int dest) 
    {
        return adjMatrix[src][dest];
    }

    public int getNumVertices() 
    {
        return numVertices;
    }

    public int[][] getAdjMatrix() 
    {
        return adjMatrix;
    }

    /** Generates a random, Directed Weighted graph with the given parameters.
     * @param numVertices Number of vertices in the graph
     * @param maxWeight Maximum weight for any edge
     * @param density Probability of an edge existing between any two vertices (0.0 to 1.0)
     */
    static public GraphDW randGraphDW(int numVertices, int maxWeight, double density) 
    {
        GraphDW graph = new GraphDW(numVertices);

        if (numVertices <= 1) return graph;

        // Ensure weak connectivity by creating a simple spanning backbone
        // Add a directed edge from i -> i+1 for i=0..n-2. The underlying
        // undirected graph will then be connected. Additional random edges
        // are added afterwards according to `density`.
        for (int i = 0; i < numVertices - 1; i++) {
            int weight = 1 + (int)(Math.random() * maxWeight);
            graph.addEdge(i, i + 1, weight);
        }

        // Add remaining random edges (may overwrite backbone edges with
        // another random weight which is acceptable).
        for (int i = 0; i < numVertices; i++) 
        {
            for (int j = 0; j < numVertices; j++) 
            {
                if (i != j && Math.random() < density) 
                {
                    int weight = 1 + (int)(Math.random() * maxWeight);
                    graph.addEdge(i, j, weight);
                }
            }
        }

        return graph;
    }

    // public boolean isConnected() 
    // {
    //     // A directed graph is strongly connected if there is a path from any vertex to every other vertex.
    //     for (int i = 0; i < numVertices; i++) 
    //     {
    //         boolean[] visited = new boolean[numVertices];
    //         dfs(i, visited);
    //         for (boolean v : visited) 
    //         {
    //             if (!v) return false;
    //         }
    //     }
    //     return true;
    // }
    
}
