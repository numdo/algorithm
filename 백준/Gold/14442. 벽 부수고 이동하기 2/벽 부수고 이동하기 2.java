import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int[][] map;
	static boolean[][][] visited;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[K + 1][N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		System.out.println(bfs());

	}

	public static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { 0, 0, 0, 1 });
		visited[0][0][0] = true;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[0] == N - 1 && cur[1] == M - 1) {
				return cur[3];
			}
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (!isIn(nx, ny) || visited[cur[2]][nx][ny])
					continue;
				if (map[nx][ny] == 0) {
					visited[cur[2]][nx][ny] = true;
					q.add(new int[] { nx, ny, cur[2], cur[3] + 1 });
				} else if(map[nx][ny] == 1 && cur[2] < K && !visited[cur[2]+1][nx][ny]) {
					visited[cur[2] + 1][nx][ny] = true;
					q.add(new int[] { nx, ny, cur[2] + 1, cur[3] + 1 });
				}
			}
		}
		return -1;
	}

	public static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
