import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][][] dp; // dp[x][y][0]: 가로, dp[x][y][1]: 세로, dp[x][y][2]: 대각선

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N][3]; // 각 위치에서 파이프가 가로, 세로, 대각선으로 오는 경우의 수

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 상태 설정 (파이프는 처음 가로로 놓여 있음)
        dp[0][1][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (map[i][j] == 1) continue; // 벽이 있으면 이동 불가

                // 가로 상태에서 갈 수 있는 경우
                if (j - 1 >= 0) {
                    dp[i][j][0] += dp[i][j - 1][0]; // 이전 위치가 가로
                    dp[i][j][0] += dp[i][j - 1][2]; // 이전 위치가 대각선
                }

                // 세로 상태에서 갈 수 있는 경우
                if (i - 1 >= 0) {
                    dp[i][j][1] += dp[i - 1][j][1]; // 이전 위치가 세로
                    dp[i][j][1] += dp[i - 1][j][2]; // 이전 위치가 대각선
                }

                // 대각선 상태에서 갈 수 있는 경우
                if (i - 1 >= 0 && j - 1 >= 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0) {
                    dp[i][j][2] += dp[i - 1][j - 1][0]; // 이전 위치가 가로
                    dp[i][j][2] += dp[i - 1][j - 1][1]; // 이전 위치가 세로
                    dp[i][j][2] += dp[i - 1][j - 1][2]; // 이전 위치가 대각선
                }
            }
        }

        // 최종 도착지에서 가로, 세로, 대각선 모두에서 도착할 수 있는 경우의 수 합산
        int result = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
        System.out.println(result);
    }
}
