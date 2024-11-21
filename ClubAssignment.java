import java.util.*;

public class ClubAssignment {

    static class Node {
        int level;  // Current level in the decision tree
        int cost;   // Current cost
        int[] assignment; // Stores assignments
        boolean[] assignedClubs; // Tracks assigned clubs

        public Node(int level, int cost, int[] assignment, boolean[] assignedClubs) {
            this.level = level;
            this.cost = cost;
            this.assignment = assignment.clone();
            this.assignedClubs = assignedClubs.clone();
        }
    }

    // Function to calculate the lower bound for a given node
    private static int calculateLowerBound(int[][] costMatrix, boolean[] assignedClubs, int N) {
        int bound = 0;
        for (int i = 0; i < N; i++) {
            int minCost = Integer.MAX_VALUE;

            if (!assignedClubs[i]) {
                for (int j = 0; j < N; j++) {
                    if (!assignedClubs[j]) {
                        minCost = Math.min(minCost, costMatrix[i][j]);
                    }
                }
                bound += minCost;
            }
        }
        return bound;
    }

    public static int[] assignClubs(int[][] costMatrix) {
        int N = costMatrix.length;

        // Priority Queue for Branch and Bound
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));

        // Initializing root node
        Node root = new Node(0, 0, new int[N], new boolean[N]);
        pq.add(root);

        int[] bestAssignment = new int[N];
        int minCost = Integer.MAX_VALUE;

        // Perform Branch and Bound
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // Only executed when all nodes are assigned
            if (current.level == N) {
                if (current.cost < minCost) {
                    minCost = current.cost;
                    bestAssignment = current.assignment.clone();
                }
                continue;
            }

            // Executed first
            for (int i = 0; i < N; i++) {
                if (!current.assignedClubs[i]) {
                    Node child = new Node(current.level + 1, current.cost, current.assignment, current.assignedClubs);

                    child.assignment[current.level] = i;
                    child.cost += costMatrix[current.level][i];
                    child.assignedClubs[i] = true;

                    // Calculate bound and add to the queue if promising
                    int bound = child.cost + calculateLowerBound(costMatrix, child.assignedClubs, N);
                    if (bound < minCost) {
                        pq.add(child);
                    }
                }
            }
        }

        System.out.println("Minimum Cost: " + minCost);
        return bestAssignment;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take number of students and clubs as input
        System.out.print("Enter the number of students (and clubs): ");
        int N = sc.nextInt();

        // Initialize the cost matrix
        int[][] costMatrix = new int[N][N];
        System.out.println("Enter the cost matrix:");

        // Fill the cost matrix
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                costMatrix[i][j] = sc.nextInt();
            }
        }

        // Solve the assignment problem
        int[] assignment = assignClubs(costMatrix);

        // Display the optimal assignment
        System.out.println("Optimal Assignment:");
        for (int i = 0; i < assignment.length; i++) {
            // Adjust output to 1-based indexing
            System.out.println("Student " + (i + 1) + " -> Club " + (assignment[i] + 1));
        }

        sc.close();
    }
}
