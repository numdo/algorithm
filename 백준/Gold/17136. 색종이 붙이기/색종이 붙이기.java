import java.io.*;
import java.util.*;

public class Main {
	static final int N = 10;
	static int[][] map = new int[N][N];
	static int[] dx = { 1, 0, 1 };
	static int[] dy = { 0, 1, 1 };
	static boolean[][] visited = new boolean[N][N];
	static int[] paper = { 0, 5, 5, 5, 5, 5 };
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0, 0);

		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	public static void dfs(int x, int y, int count) {
		if (x == N) {
			result = Integer.min(result, count);
			return;
		}
		if (y == N) {
			dfs(x + 1, 0, count);
			return;
		}
		if (map[x][y] == 0) {
			dfs(x, y + 1, count);
		} else {
			for (int size = 5; size > 0; size--) {
				if (paper[size] > 0 && isFill(x, y, size)) {
					fill(x, y, size, 0);
					paper[size]--;
					dfs(x, y + 1, count + 1);
					fill(x, y, size, 1);
					paper[size]++;
				}
			}
		}

	}

	public static boolean isFill(int x, int y, int size) {
		if (x + size > N || y + size > N) {
			return false;
		}
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (map[i][j] != 1) {
					return false;
				}
			}
		}
		return true;
	}

	public static void fill(int x, int y, int size, int val) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				map[i][j] = val;
			}
		}
	}
}
