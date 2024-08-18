
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] population;
    static List<List<Integer>> adjList;
    static boolean[] selected;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        adjList = new ArrayList<>();
        selected = new boolean[N + 1];

        // 인접 리스트 초기화
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        // 인구 수 입력
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        // 인접 구역 정보 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int count = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count; j++) {
                int neighbor = Integer.parseInt(st.nextToken());
                adjList.get(i).add(neighbor);
                adjList.get(neighbor).add(i); // 양방향 연결
            }
        }

        // 조합을 통해 모든 구역을 나눠서 계산
        for (int i = 1; i <= N / 2; i++) { // 구역 수의 절반까지만 탐색
            combine(1, 0, i);
        }

        // 결과 출력
        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
    }

    // 조합 생성 함수
    public static void combine(int index, int count, int target) {
        if (count == target) {
            // 두 선거구가 모두 연결되었는지 확인
            if (isConnected(true) && isConnected(false)) {
                calculateDifference();
            }
            return;
        }

        for (int i = index; i <= N; i++) {
            selected[i] = true;
            combine(i + 1, count + 1, target);
            selected[i] = false;
        }
    }

    // 연결성 확인 함수
    public static boolean isConnected(boolean flag) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        
        // flag가 true이면 선택된 선거구, false이면 선택되지 않은 선거구
        for (int i = 1; i <= N; i++) {
            if (selected[i] == flag) {
                queue.add(i);
                visited[i] = true;
                break;
            }
        }
        
        if (queue.isEmpty()) return false;

        int count = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            count++;
            
            for (int neighbor : adjList.get(current)) {
                if (!visited[neighbor] && selected[neighbor] == flag) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        
        for (int i = 1; i <= N; i++) {
            if (selected[i] == flag && !visited[i]) {
                return false;
            }
        }
        
        return true;
    }

    // 인구 차이 계산 함수
    public static void calculateDifference() {
        int population1 = 0, population2 = 0;
        for (int i = 1; i <= N; i++) {
            if (selected[i]) {
                population1 += population[i];
            } else {
                population2 += population[i];
            }
        }
        int diff = Math.abs(population1 - population2);
        minDiff = Math.min(minDiff, diff);
    }
}
