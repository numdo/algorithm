import java.io.*;
import java.util.*;

public class Main {
    static int N, M, D;
    static int[][] map;
    static int[] archerPositions;
    static int maxKills = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        archerPositions = new int[3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        placeArchers(0, 0);
        System.out.println(maxKills);
    }

    public static void placeArchers(int start, int depth) {
        if (depth == 3) {
            int kills = simulate();
            maxKills = Math.max(maxKills, kills);
            return;
        }

        for (int i = start; i < M; i++) {
            archerPositions[depth] = i;
            placeArchers(i + 1, depth + 1);
        }
    }

    public static int simulate() {
        int[][] tempMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, tempMap[i], 0, M);
        }

        int totalKills = 0;

        for (int round = 0; round < N; round++) {
            List<int[]> targets = new ArrayList<>();

            for (int archer : archerPositions) {
                int[] target = findTarget(tempMap, archer);
                if (target != null) {
                    targets.add(target);
                }
            }

            totalKills += attack(tempMap, targets);
            moveEnemies(tempMap);
        }

        return totalKills;
    }

    public static int[] findTarget(int[][] tempMap, int archerCol) {
        int minDist = Integer.MAX_VALUE;
        int[] target = null;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tempMap[i][j] == 1) {
                    int dist = Math.abs(N - i) + Math.abs(archerCol - j);
                    if (dist <= D) {
                        if (dist < minDist || (dist == minDist && j < (target == null ? M : target[1]))) {
                            minDist = dist;
                            target = new int[]{i, j};
                        }
                    }
                }
            }
        }

        return target;
    }

    public static int attack(int[][] tempMap, List<int[]> targets) {
        int kills = 0;
        for (int[] target : targets) {
            if (tempMap[target[0]][target[1]] == 1) {
                tempMap[target[0]][target[1]] = 0;
                kills++;
            }
        }
        return kills;
    }

    public static void moveEnemies(int[][] tempMap) {
        for (int i = N - 1; i > 0; i--) {
            tempMap[i] = Arrays.copyOf(tempMap[i - 1], M);
        }
        Arrays.fill(tempMap[0], 0);
    }
}
