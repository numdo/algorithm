import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int test = 1; test <= T; test++) {
            int n = Integer.parseInt(br.readLine().trim());
            int[][] arr = new int[n][n];
            int cnt = 1;
            int move = 0;
            int left = -1;
            int right = n - 1;
            int top = 0;
            int bottom = n - 1;
            while (cnt <= n * n) {
                move = ++left;
                while (move <= right) {
                    arr[top][move] = cnt++;
                    move++;
                }
                move = ++top;
                while (move <= bottom) {
                    arr[move][right] = cnt++;
                    move++;
                }
                move = --right;
                while (move >= left) {
                    arr[bottom][move] = cnt++;
                    move--;
                }
                move = --bottom;
                while (move >= top) {
                    arr[move][left] = cnt++;
                    move--;
                }
            }
            System.out.println("#" + test);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.printf("%d ", arr[i][j]);
                }
                System.out.println();
            }
        }
    }
}
