import java.util.*;

public class Dijkstra {
    static final int V = 9; // Number of vertices in the graph

    // Method to find the vertex with the minimum distance from the source
    int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE; // Initialize min value
        int min_index = -1;

        for (int v = 0; v < V; v++) {
            // If vertex is not in the shortest path tree and has a smaller distance
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index; // Return the vertex with the minimum distance
    }

    // Method to print the final shortest distances from the source
    public static void printSolution(int dist[]) {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    // Main method that implements Dijkstra's algorithm
    public void DijkstraAlgo(int graph[][], int src) {
        int dist[] = new int[V]; // Array to hold the shortest distance from the source
        Boolean sptSet[] = new Boolean[V]; // Boolean array to track processed vertices

        // Initialize all distances as INFINITE and sptSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE; // Set all distances to infinity
            sptSet[i] = false; // Mark all vertices as unprocessed
        }

        // Distance of the source vertex from itself is always 0
        dist[src] = 0;

        // Find the shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the unprocessed vertices
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist[] of adjacent vertices of the picked vertex
            for (int v = 0; v < V; v++) {
                // Update dist[v] if it's not in the sptSet, there is an edge from u to v,
                // and the total weight of path from src to v through u is smaller than current
                // value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // Print the calculated shortest distances from source
        printSolution(dist);
    }

    public static void main(String[] args) {
        // Scanner to take input for the graph matrix
        Scanner sc = new Scanner(System.in);
        int matrix[][] = new int[V][V];

        // Read graph input from the user
        System.out.println("Enter the graph as a matrix (rows: source, columns: destination):");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = sc.nextInt(); // Input the edge weights (0 for no edge)
            }
        }

        // Create an instance of the Dijkstra class and call the algorithm
        Dijkstra d = new Dijkstra();
        d.DijkstraAlgo(matrix, 0); // Assuming the source vertex is 0

        sc.close();
    }
}
