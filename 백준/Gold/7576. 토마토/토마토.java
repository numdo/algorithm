import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], Integer.MIN_VALUE);
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					q.add(new int[] { i, j, 0 });
					visited[i][j] = 0;
				}
				else if(map[i][j] == -1) {
					visited[i][j] = -1;
				}
			}
		}
		bfs();
		int cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(visited[i][j] == Integer.MIN_VALUE) {
					System.out.println(-1);
					return;
				}
				cnt = Integer.max(cnt,visited[i][j]);
			}
		}
		System.out.println(cnt);
	}

	public static void bfs() {
		while (!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] cur = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = cur[0] + dx[d];
					int ny = cur[1] + dy[d];
					if (isIn(nx, ny) && visited[nx][ny] < -1) {
						visited[nx][ny] = cur[2] + 1;
						q.add(new int[] { nx, ny, cur[2] + 1 });
					}
				}
			}
		}
	}

	public static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
