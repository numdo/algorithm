import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        
        // 사진 입력
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        moveMeteor();
    }

    // 유성 낙하 시뮬레이션
    public static void moveMeteor() {
        int minFall = N; // 유성이 떨어질 수 있는 최소 낙하 거리
        
        // 1. 각 열에서 유성과 땅 사이의 최소 거리를 계산
        for (int col = 0; col < M; col++) {
            int lastMeteor = -1;
            int firstGround = N;
            
            for (int row = 0; row < N; row++) {
                if (map[row][col] == 'X') {
                    lastMeteor = row;
                }
                if (map[row][col] == '#' && firstGround == N) {
                    firstGround = row;
                }
            }

            // 유성과 땅 사이의 거리 계산
            if (lastMeteor != -1 && firstGround != N) {
                minFall = Math.min(minFall, firstGround - lastMeteor - 1);
            }
        }

        // 2. 유성을 새로운 위치로 이동시켜 새로운 배열 구성
        char[][] newPhoto = new char[N][M];
        
        // 땅 복사 및 유성 이동
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '#') {
                    newPhoto[i][j] = '#';  // 땅은 그대로 복사
                } else {
                    newPhoto[i][j] = '.';  // 나머지는 빈 공간으로 채움
                }
            }
        }

        // 유성을 새로운 위치로 복사
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'X') {
                    newPhoto[i + minFall][j] = 'X';  // 유성을 최소 낙하 거리만큼 이동
                }
            }
        }

        // 최종 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(newPhoto[i]).append('\n');
        }
        System.out.print(sb);
    }
}
