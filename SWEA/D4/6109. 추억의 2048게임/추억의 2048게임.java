
import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader br = new BufferedReader(new FileReader("./res/SWEA6109_input.txt"));
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 격자의 크기
            String direction = st.nextToken();
            
            int[][] grid = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            // 이동 방향에 따라 타일을 이동 및 합치기 처리합니다.
            grid = moveAndMergeTiles(N, direction, grid);
            
            System.out.println("#" + t);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
	/**
	 * 방향에 따라 타일을 이동 및 합치는 메서드
	 * @param N 크기
	 * @param direction 이동 방향
	 * @param grid 현재 상태
	 * @return 이동이나 합친후 새로운 배열 상태
	 */
    private static int[][] moveAndMergeTiles(int N, String direction, int[][] grid) {
        if (direction.equals("left")) {
            grid = moveLeft(grid, N);
        } else if (direction.equals("right")) {
            grid = reverseGrid(grid, N);
            grid = moveLeft(grid, N);
            grid = reverseGrid(grid, N);
        } else if (direction.equals("up")) {
            grid = transposeGrid(grid, N);
            grid = moveLeft(grid, N);
            grid = transposeGrid(grid, N);
        } else if (direction.equals("down")) {
            grid = transposeGrid(grid, N);
            grid = reverseGrid(grid, N);
            grid = moveLeft(grid, N);
            grid = reverseGrid(grid, N);
            grid = transposeGrid(grid, N);
        }
        return grid;
    }
    /**
     * 행을 왼쪽으로 이동시키고 타일을 합치는 메서드
     * @param grid 현재 배열 상태
     * @param N 배열 크기
     * @return 왼쪽으로 이동 및 합친 후의 배열 상태
     */
    private static int[][] moveLeft(int[][] grid, int N) {
        for (int i = 0; i < N; i++) {
            grid[i] = compress(grid[i], N);
            grid[i] = merge(grid[i], N);
            grid[i] = compress(grid[i], N);
        }
        return grid;
    }
    /**
     * 주어진 행에서 0이 아닌 타일을 왼쪽으로 합치는 메서드
     * @param row
     * @param N
     * @return 압축된 새로운 행
     */
    private static int[] compress(int[] row, int N) {
        int[] newRow = new int[N];
        int index = 0;
        for (int num : row) {
            if (num != 0) {
                newRow[index++] = num;
            }
        }
        return newRow;
    }
    
    /**
     * 인접한 행의 동일한 숫자 합치는 메서드
     * @param row
     * @param N
     * @return 합쳐진 새로운 행
     */
    private static int[] merge(int[] row, int N) {
        for (int i = 0; i < N - 1; i++) {
            if (row[i] == row[i + 1] && row[i] != 0) {
                row[i] *= 2;
                row[i + 1] = 0;
            }
        }
        return row;
    }
    /**
     * 좌우 반전시키는 메서드
     * @param grid
     * @param N
     * @return 좌우 반전된 배열
     */
    private static int[][] reverseGrid(int[][] grid, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N / 2; j++) {
                int temp = grid[i][j];
                grid[i][j] = grid[i][N - j - 1];
                grid[i][N - j - 1] = temp;
            }
        }
        return grid;
    }
    /**
     * 주어진 행을 전치하는 메서드
     * @param grid
     * @param N
     * @return
     */
    private static int[][] transposeGrid(int[][] grid, int N) {
        int[][] newGrid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newGrid[i][j] = grid[j][i];
            }
        }
        return newGrid;
    }

}
