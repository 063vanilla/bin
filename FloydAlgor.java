import java.util.*;
public class FloydAlgor {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter the number of vertices: ");
int n = sc.nextInt();
int[][] arr = new int[n][n];
System.out.println("Enter the adjacency matrix (enter 9999 for no direct path");

for (int i = 0; i < n; i++) {
for (int j = 0; j < n; j++) {
arr[i][j] = sc.nextInt();
}
}
findMin(arr, n);
System.out.println("The shortest paths matrix is: ");
for (int i = 0; i < n; i++) {
for (int j = 0; j < n; j++) {
System.out.print(arr[i][j] + " ");
}
System.out.println();
}
sc.close();
}
public static void findMin(int[][] arr, int n) {
for (int k = 0; k < n; k++) {
for (int i = 0; i < n; i++) {
for (int j = 0; j < n; j++) {
if (arr[i][j] > arr[i][k] + arr[k][j]) {
arr[i][j] = arr[i][k] + arr[k][j];
}
}
}
}
}
}