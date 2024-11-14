import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] cheeze;
	static boolean[][] visited;
	static int[][] melt;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean stop;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cheeze = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				cheeze[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int count = 0;
		while (true) {
			stop = false;
			visited = new boolean[N][M];
			melt = new int[N][M];
			boolean flag = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (cheeze[i][j] == 0) {
						bfs(i, j);
						flag = true;
					}
				}
				if (flag) {
					break;
				}
			}
			meltCheeze();
			if (!stop) {
				break;
			}
			count++;
		}
		System.out.println(count);
	}

	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { x, y });
		visited[x][y] = true;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];

				if (!isIn(nx, ny))
					continue;

				if (cheeze[nx][ny] == 1) {
					stop = true;
					melt[nx][ny] += 1;
				}
				if (cheeze[nx][ny] == 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new int[] { nx, ny });
				}
			}

		}
	}

	public static void meltCheeze() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (melt[i][j] >= 2) {
					cheeze[i][j] = 0;
				}
//				System.out.print(cheeze[i][j] + " ");
			}
//			System.out.println();
		}
	}

	public static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
