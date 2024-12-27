import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] island;
	static int[][] visited;
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			island = new int[N][M];
			visited = new int[N][M];
			answer = 0;
			if (N == 0 || M == 0) {
				break;
			}
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					island[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j] == 0 && island[i][j] == 1) {
						bfs(i, j);
						answer++;
					}
				}
			}
			System.out.println(answer);
		}

	}

	public static void bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque();
		q.add(new int[] { x, y });
		visited[x][y] = 1;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int d = 0; d < 8; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				if (isIn(nx, ny) && visited[nx][ny] == 0 && island[nx][ny] == 1) {
					visited[nx][ny] = 1;
					q.offer(new int[] { nx, ny });
				}
			}
		}
	}

	public static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
