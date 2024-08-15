
import java.io.*;

public class Solution {
    static final int N = 100;
    static int[][] testArr;
    static int[] dx = { 1, -1, 0 };
    static int[] dy = { 0, 0, 1 };

    public static void main(String[] args) throws IOException {
        // 파일 경로를 지정합니다.
        //String filePath = "res/input.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test = 1; test <= 10; test++) {
            int num = Integer.parseInt(br.readLine().trim());
            testArr = new int[N][N];
            for (int i = 0; i < N; i++) {
                String[] line = br.readLine().trim().split(" ");
                for (int j = 0; j < N; j++) {
                    testArr[i][j] = Integer.parseInt(line[j]);
                }
            }
            int result = findDestination();
            System.out.println("#" + num + " " + result);
        }

        // BufferedReader를 사용한 후에는 반드시 close 해줍니다.
        br.close();
    }

    public static int findDestination() {
        int x = 0;
        for (int i = 0; i < N; i++) {
            if (testArr[N - 1][i] == 2) {
                x = i;
                break;
            }
        }
        for (int i = N - 1; i >= 0; i--) {
            if (x - 1 >= 0 && testArr[i][x - 1] == 1) {
                while (x - 1 >= 0 && testArr[i][x - 1] == 1) {
                    x--;
                }
            } else if (x + 1 < N && testArr[i][x + 1] == 1) {
                while (x + 1 < N && testArr[i][x + 1] == 1) {
                    x++;
                }
            }
        }
        return x;
    }
}
