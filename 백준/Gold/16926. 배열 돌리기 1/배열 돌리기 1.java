import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        // 배열 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 레이어별로 회전하기
        int layers = Math.min(N, M) / 2;  // 레이어 수 계산
        for (int layer = 0; layer < layers; layer++) {
            int numElements = 2 * (N + M - 4 * layer) - 4; // 해당 레이어의 원소 수
            int rotations = R % numElements;  // 실제 필요한 회전 수

            // 레이어 회전
            for (int r = 0; r < rotations; r++) {
                rotateLayer(layer);
            }
        }

        // 결과 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 레이어를 한 번 회전하는 함수 (반시계 방향)
    public static void rotateLayer(int layer) {
        int top = layer;
        int left = layer;
        int bottom = N - layer - 1;
        int right = M - layer - 1;

        // 첫 번째 값을 저장해 둠
        int temp = map[top][left];

        // 위쪽 가로 (왼쪽으로 이동)
        for (int i = left; i < right; i++) {
            map[top][i] = map[top][i + 1];
        }

        // 오른쪽 세로 (아래로 이동)
        for (int i = top; i < bottom; i++) {
            map[i][right] = map[i + 1][right];
        }

        // 아래쪽 가로 (오른쪽으로 이동)
        for (int i = right; i > left; i--) {
            map[bottom][i] = map[bottom][i - 1];
        }

        // 왼쪽 세로 (위로 이동)
        for (int i = bottom; i > top + 1; i--) {
            map[i][left] = map[i - 1][left];
        }

        // 마지막으로 저장했던 temp 값을 제자리에 넣음
        map[top + 1][left] = temp;
    }
}
