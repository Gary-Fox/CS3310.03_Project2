

public class Main
{
    public static void main(String[] args)
    {

        int numVertices = 5;
        int maxWeight = 10;
        double density = 0.5;

        GraphDW graph = new GraphDW(numVertices);
        graph = GraphDW.randGraphDW(numVertices, maxWeight, density);

        //GraphDW graph2 = new GraphDW();
        GraphDW graph2 = GraphDW.randGraphDW(10, 50, 1.0);

        printGraphDW(graph);
        printGraphDW(GraphDW.randGraphDW(10, 50, 0.1));
        printGraphDW(graph2);
    }


class Dijkstra
{
    // Implementation of Dijkstra's algorithm will go here
} 

class FlyodWarshall
{
    // Implementation of Floyd-Warshall algorithm will go here
}


    public static void printGraphDW(GraphDW graph)
    {
        int numVertices = graph.getNumVertices();
        int[][] adjMatrix = graph.getAdjMatrix();

        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < numVertices; i++) 
        {
            for (int j = 0; j < numVertices; j++) 
            {
                if (adjMatrix[i][j] == Integer.MAX_VALUE) 
                {
                    System.out.print("∞ ");
                } 
                else 
                {
                    System.out.print(adjMatrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}