import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    static int N;
    static int[] number;
    static int[] operator;
    static int MIN;
    static int MAX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        for (int test = 1; test <= T; test++) {
            N = Integer.parseInt(br.readLine().trim());
            operator = new int[4];
            String[] cal = br.readLine().trim().split(" ");
            for (int i = 0; i < 4; i++) {
                operator[i] = Integer.parseInt(cal[i]);
            }

            number = new int[N];
            String[] line = br.readLine().trim().split(" ");
            for (int i = 0; i < N; i++) {
                number[i] = Integer.parseInt(line[i]);
            }

            MIN = Integer.MAX_VALUE;
            MAX = Integer.MIN_VALUE;

            dfs(1, number[0]);

            System.out.println("#" + test + " " + (MAX - MIN));
        }
    }

    public static void dfs(int index, int currentResult) {
        if (index == N) {
            MIN = Math.min(MIN, currentResult);
            MAX = Math.max(MAX, currentResult);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operator[i] > 0) {
                operator[i]--;
                int nextResult = currentResult;

                switch (i) {
                    case 0: // +
                        nextResult += number[index];
                        break;
                    case 1: // -
                        nextResult -= number[index];
                        break;
                    case 2: // *
                        nextResult *= number[index];
                        break;
                    case 3: // /
                        nextResult /= number[index];
                        break;
                }

                dfs(index + 1, nextResult);

                operator[i]++;  // 백트래킹을 위해 연산자 수 복원
            }
        }
    }
}