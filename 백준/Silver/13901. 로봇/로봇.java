import java.util.*;

public class Main {
    static int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dc = {0, 0, -1, 1}; 
    static int R, C;
    static boolean[][] visited;
    static int[][] room;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        room = new int[R][C];
        visited = new boolean[R][C];

        int k = sc.nextInt();
        for (int i = 0; i < k; i++) {
            int br = sc.nextInt();
            int bc = sc.nextInt();
            room[br][bc] = 1; // 장애물 위치 설정
        }

        int sr = sc.nextInt();
        int scPos = sc.nextInt();
        int[] directions = new int[4];
        for (int i = 0; i < 4; i++) {
            directions[i] = sc.nextInt() - 1; // 1~4를 0~3으로 맞춤
        }

        // 시작 위치 방문 표시
        visited[sr][scPos] = true;

        // 시뮬레이션 시작
        int r = sr, c = scPos, dirIdx = 0;
        while (true) {
            boolean moved = false;
            for (int i = 0; i < 4; i++) {
                int dir = directions[(dirIdx + i) % 4];
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                if (nr >= 0 && nr < R && nc >= 0 && nc < C && room[nr][nc] == 0 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    r = nr;
                    c = nc;
                    dirIdx = (dirIdx + i) % 4; // 새로운 방향 인덱스 갱신
                    moved = true;
                    break;
                }
            }

            if (!moved) break; // 이동하지 못하면 종료
        }

        System.out.println(r + " " + c); // 마지막 위치 출력
    }
}
