
import java.io.*;
import java.util.*;

public class Main {
	static int N;
    static int[][] grid;
    static int[] dx = {0, 0, -1, 1};  // 상하좌우
    static int[] dy = {1, -1, 0, 0};
    static Map<Integer, int[]> likeMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        likeMap = new HashMap<>();

        int[] score = {0, 1, 10, 100, 1000};

        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            int[] likes = new int[4];
            for (int j = 0; j < 4; j++) {
                likes[j] = Integer.parseInt(st.nextToken());
            }
            likeMap.put(student, likes);
            placeStudent(student, likes);
        }

        int totalSatisfaction = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int student = grid[i][j];
                int likeCount = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                        for (int liked : likeMap.get(student)) {
                            if (grid[nx][ny] == liked) {
                                likeCount++;
                                break;
                            }
                        }
                    }
                }
                totalSatisfaction += score[likeCount];
            }
        }

        System.out.println(totalSatisfaction);
    }

    private static void placeStudent(int student, int[] likes) {
        int maxLike = -1;
        int maxEmpty = -1;
        int targetRow = -1;
        int targetCol = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] != 0) continue;

                int likeCount = 0;
                int emptyCount = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                        if (grid[nx][ny] == 0) {
                            emptyCount++;
                        } else {
                            for (int liked : likes) {
                                if (grid[nx][ny] == liked) {
                                    likeCount++;
                                    break;
                                }
                            }
                        }
                    }
                }

                if (likeCount > maxLike) {
                    maxLike = likeCount;
                    maxEmpty = emptyCount;
                    targetRow = i;
                    targetCol = j;
                } 
                else if (likeCount == maxLike) {
                    if (emptyCount > maxEmpty) {
                        maxLike = likeCount;
                        maxEmpty = emptyCount;
                        targetRow = i;
                        targetCol = j;
                    } 
                    else if (emptyCount == maxEmpty) {
                        if (i < targetRow || (i == targetRow && j < targetCol)) {
                            maxLike = likeCount;
                            maxEmpty = emptyCount;
                            targetRow = i;
                            targetCol = j;
                        }
                    }
                }
            }
        }
        grid[targetRow][targetCol] = student;
    }

}
