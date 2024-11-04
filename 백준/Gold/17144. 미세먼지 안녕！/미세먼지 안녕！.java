import java.io.*;
import java.util.*;

public class Main {
    static int N, M, T;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}; // 상, 좌, 하, 우
    static int[] dy = {0, -1, 0, 1};
    static List<int[]> robots; 

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); 
        M = Integer.parseInt(st.nextToken()); 
        T = Integer.parseInt(st.nextToken()); 

        map = new int[N][M];
        robots = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1) {
                    robots.add(new int[]{i, j});
                }
            }
        }

        for(int t = 0; t < T; t++) {
            spreadDust(); // 먼지 확산
            operateAirPurifier(); // 공기청정기 작동
        }

        int answer = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] > 0) {
                    answer += map[i][j];
                }
            }
        }

        System.out.println(answer);
    }

    public static void spreadDust() {
        int[][] temp = new int[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] > 0) { 
                    int spreadAmount = map[i][j] / 5;
                    if(spreadAmount > 0) {
                        int spreadCount = 0;
                        for(int d = 0; d < 4; d++) {
                            int ni = i + dx[d];
                            int nj = j + dy[d];
                            if(isIn(ni, nj) && map[ni][nj] != -1) {
                                temp[ni][nj] += spreadAmount;
                                spreadCount++;
                            }
                        }
                        map[i][j] -= spreadAmount * spreadCount;
                    }
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                map[i][j] += temp[i][j];
            }
        }
    }

    public static void operateAirPurifier() {
        int upper = robots.get(0)[0];
        int lower = robots.get(1)[0];

        for(int i = upper -1; i > 0; i--) {
            map[i][0] = map[i-1][0];
        }
        for(int j = 0; j < M-1; j++) {
            map[0][j] = map[0][j+1];
        }
        for(int i = 0; i < upper; i++) {
            map[i][M-1] = map[i+1][M-1];
        }
        for(int j = M-1; j >1; j--) {
            map[upper][j] = map[upper][j-1];
        }
        map[upper][1] = 0; 

        for(int i = lower +1; i < N-1; i++) {
            map[i][0] = map[i+1][0];
        }
        for(int j = 0; j < M-1; j++) {
            map[N-1][j] = map[N-1][j+1];
        }
        for(int i = N-1; i > lower; i--) {
            map[i][M-1] = map[i-1][M-1];
        }
        for(int j = M-1; j >1; j--) {
            map[lower][j] = map[lower][j-1];
        }
        map[lower][1] = 0; 
    }

    public static boolean isIn(int x, int y) {
        return x >=0 && x < N && y >=0 && y < M;
    }
}
