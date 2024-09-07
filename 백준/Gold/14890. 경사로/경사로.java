import java.io.*;
import java.util.*;

public class Main {
    static int N, L, answer = 0;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우 이동
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        
        // 지형 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 각 행과 열에 대해 경사로 설치 가능 여부 체크
        for (int i = 0; i < N; i++) {
            if (canPass(map[i])) { // 행을 체크
                answer++;
            }
            
            int[] col = new int[N];
            for (int j = 0; j < N; j++) {
                col[j] = map[j][i];
            }
            if (canPass(col)) { // 열을 체크
                answer++;
            }
        }

        // 결과 출력
        System.out.println(answer);
    }

    // 한 줄(행 또는 열)을 검사하는 함수
    public static boolean canPass(int[] line) {
        boolean[] visited = new boolean[N]; // 각 줄에 대한 방문 배열

        for (int i = 0; i < N - 1; i++) {
            if (line[i] == line[i + 1]) { 
                continue; // 높이가 같으면 지나갈 수 있음
            }
            
            // 올라가는 경사로를 설치할 수 있는지 확인
            if (line[i] + 1 == line[i + 1]) {
                if (!canInstallRamp(line, i - L + 1, i, visited, 1)) {
                    return false;
                }
            } 
            // 내려가는 경사로를 설치할 수 있는지 확인
            else if (line[i] - 1 == line[i + 1]) {
                if (!canInstallRamp(line, i + 1, i + L, visited, -1)) {
                    return false;
                }
            } else {
                return false; // 높이 차이가 1 이상이면 경사로 설치 불가
            }
        }
        return true;
    }

    // 경사로를 설치할 수 있는지 확인하는 함수
    public static boolean canInstallRamp(int[] line, int start, int end, boolean[] visited, int direction) {
        if (start < 0 || end >= N) {
            return false; // 범위를 벗어나면 설치 불가
        }
        
        for (int i = start; i <= end; i++) {
            if (i < 0 || i >= N || visited[i] || line[i] != line[start]) {
                return false; // 범위를 벗어나거나 이미 방문한 곳이거나 높이가 다르면 설치 불가
            }
        }
        
        for (int i = start; i <= end; i++) {
            visited[i] = true; // 경사로 설치 처리
        }
        
        return true;
    }
}
