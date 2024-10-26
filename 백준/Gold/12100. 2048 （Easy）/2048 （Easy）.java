import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static final int M = 5;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int result;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		perm(0, new int[5]);
		System.out.println(result);
	}

	public static void perm(int depth, int[] temp) {
		if (depth == M) {
			int[][] newMap = cpyMap();
			simulate(temp, newMap);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					result = Math.max(result, newMap[i][j]);
				}
			}
			return;
		}
		for (int i = 0; i < 4; i++) {
			temp[depth] = i;
			perm(depth + 1, temp);
		}
	}

	public static int[][] cpyMap() {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			temp[i] = Arrays.copyOf(map[i], N);
		}
		return temp;
	}

	public static void simulate(int[] dir, int[][] newMap) {

		for (int d = 0; d < M; d++) {
			// 상
			if (dir[d] == 0) {
				for (int c = 0; c < N; c++) {
					int[] newColumn = new int[N];
					int idx = 0;
					boolean merged = false;
					for (int r = 0; r < N; r++) {
						if (newMap[r][c] != 0) {
							if (idx > 0 && newColumn[idx - 1] == newMap[r][c] && !merged) {
								newColumn[idx - 1] *= 2;
								merged = true;
							} else {
								newColumn[idx++] = newMap[r][c];
								merged = false;
							}
						}
					}
					for (int r = 0; r < N; r++) {
						newMap[r][c] = newColumn[r];
					}
				}

			}
			// 하
			else if (dir[d] == 1) {
				for (int c = 0; c < N; c++) {
					int[] newColumn = new int[N];
					int idx = N - 1;
					boolean merged = false;
					for (int r = N - 1; r >= 0; r--) {
						if (newMap[r][c] != 0) {
							if (idx < N - 1 && newColumn[idx + 1] == newMap[r][c] && !merged) {
								newColumn[idx + 1] *= 2;
								merged = true;
							} else {
								newColumn[idx--] = newMap[r][c];
								merged = false;
							}
						}
					}
					for (int r = N - 1; r >= 0; r--) {
						newMap[r][c] = newColumn[r];
					}
				}
			}
			// 좌
			else if (dir[d] == 2) {
				for (int r = 0; r < N; r++) {
					int[] newRow = new int[N];
					int idx = 0;
					boolean merged = false;
					for (int c = 0; c < N; c++) {
						if (newMap[r][c] != 0) {
							if (idx > 0 && newRow[idx - 1] == newMap[r][c] && !merged) {
								newRow[idx - 1] *= 2;
								merged = true;
							} else {
								newRow[idx++] = newMap[r][c];
								merged = false;
							}
						}
					}
					for (int c = 0; c < N; c++) {
						newMap[r][c] = newRow[c];
					}
				}
			}
			// 우
			else {
				for (int r = 0; r < N; r++) {
					int[] newRow = new int[N];
					int idx = N - 1;
					boolean merged = false;
					for (int c = N - 1; c >= 0; c--) {
						if (newMap[r][c] != 0) {
							if (idx < N - 1 && newRow[idx + 1] == newMap[r][c] && !merged) {
								newRow[idx + 1] *= 2;
								merged = true;
							} else {
								newRow[idx--] = newMap[r][c];
								merged = false;
							}
						}
					}
					for (int c = N - 1; c >= 0; c--) {
						newMap[r][c] = newRow[c];
					}
				}
			}
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
