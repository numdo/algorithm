import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dx = { 0, 1, 1 }; // 가로, 세로, 대각선
    static int[] dy = { 1, 0, 1 };
    static int count = 0; // 가능한 경로의 수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 상태는 가로로 (0, 0)에서 (0, 1)로 가로 놓인 상태에서 시작
        dfs(0, 1, 0);
        System.out.println(count);
    }

    // DFS 메서드
    public static void dfs(int x, int y, int type) {
        // 마지막 칸에 도달했을 때 경로 하나 완성
        if (x == N - 1 && y == N - 1) {
            count++;
            return;
        }

        // 가로, 세로, 대각선 상태에 따른 이동
        for (int dir = 0; dir < 3; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 가로에서 세로로는 못 이동, 세로에서 가로로는 못 이동
            if ((type == 0 && dir == 1) || (type == 1 && dir == 0)) {
                continue;
            }

            // 범위와 벽 체크
            if (isInMap(nx, ny) && map[nx][ny] == 0) {
                // 대각선으로 이동할 때는 추가적으로 체크
                if (dir == 2 && (map[x + 1][y] == 1 || map[x][y + 1] == 1)) {
                    continue;
                }

                // 다음 상태로 DFS 재귀 호출
                dfs(nx, ny, dir);
            }
        }
    }

    // 범위 체크 메서드
    public static boolean isInMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
