import java.util.Random;

public class DataGathers 
{
    public static void main(String[] args) 
    {
        int n = 10;

        while(n <= 1000)
        {
            System.out.print(n + " ");
            testingFloyd(100, n);
            System.out.println();
            if(n == 100)
                n *= 10;
            else
            n += 10;
        }


        
    }
    
    static public void testingFloyd(int times, int size)
    {
        long timeSum = 0;

        for (int i = 0; i < times; i++)
        {
            Random rand = new Random();
            double dense = rand.nextDouble();
            int weight = rand.nextInt(200) + 1;    
            GraphDW newGraph = GraphDW.randGraphDW(size, weight, dense);
            long start = System.currentTimeMillis();
            Main.floydWarshall(newGraph.getAdjMatrix());
            long end = System.currentTimeMillis();
            timeSum += (end - start);    
        }

        System.out.print(timeSum / times + " ms");

    }
}
