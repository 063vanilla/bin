import java.util.Scanner;

public class KnightT {
    public boolean safe(int x, int y, int ans[][]) {
        int n = ans.length; // Board size
        return (x >= 0 && x < n && y >= 0 && y < n && ans[x][y] == -1);
    }

    public void kTour(int x, int y, int n) {
        int ans[][] = new int[n][n];

        // Initialize the chessboard with -1 (unvisited)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = -1;
            }
        }

        // Arrays to store all possible moves of a knight
        int xDir[] = {2, 1, -1, -2, -2, -1, 1, 2};
        int yDir[] = {1, 2, 2, 1, -1, -2, -2, -1};

        // Start the knight at the given position
        ans[x][y] = 0;

        // Solve the problem using backtracking
        if (!kTourHelper(x, y, 1, ans, xDir, yDir)) {
            System.out.println("Solution does not exist.");
        } else {
            printMatrix(ans);
        }
    }

    public boolean kTourHelper(int x, int y, int move, int ans[][], int xDir[], int yDir[]) {
        int n = ans.length;
        int nextX, nextY;

        // Base case: All squares are visited
        if (move == n * n) {
            return true;
        }

        // Try all 8 possible moves
        for (int k = 0; k < 8; k++) {
            nextX = x + xDir[k];
            nextY = y + yDir[k];

            if (safe(nextX, nextY, ans)) {
                ans[nextX][nextY] = move; // Mark the move
                if (kTourHelper(nextX, nextY, move + 1, ans, xDir, yDir)) {
                    return true; // Solution found
                } else {
                    ans[nextX][nextY] = -1; // Backtrack
                }
            }
        }
        return false;
    }

    public void printMatrix(int mat[][]) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the size of the chessboard
        System.out.print("Enter the size of the chessboard: ");
        int n = sc.nextInt();

        // Input the starting position of the knight
        System.out.print("Enter the starting position of the knight (x and y): ");
        int x = sc.nextInt();
        int y = sc.nextInt();

        System.out.println("\nThe tour of the knight across the chessboard:");

        // Create an instance of the Knight class and solve the tour
        KnightT kn = new KnightT();
        kn.kTour(x, y, n);

        sc.close();
    }
}
