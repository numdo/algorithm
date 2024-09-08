import java.io.*;
import java.util.*;

public class Main {
	static int N, M, map[][], tempMap[][];
	static boolean[][] visited;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N][M];
		map = new int[N][M];
		tempMap = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = 0;
		while (true) {
			int count = 0;
			visited = new boolean[N][M];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] > 0 && !visited[i][j]) {
						tempMap = new int[N][M];
						bfs(i, j);
						count++;
					}
				}
			}
			if(count > 1) {
				break;
			}
			else if(count == 0) {
				answer = 0;
				break;
			}
			meltMap();
			answer++;
		}
		System.out.println(answer);
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

	}
	public static void meltMap() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(tempMap[i][j] >0) {
					map[i][j] -=tempMap[i][j];
					if(map[i][j] <0) {
						map[i][j] = 0;
					}

				}
			}
		}
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

				if (isMap(nx, ny) && !visited[nx][ny]) {
					if (map[nx][ny] > 0) {
						visited[nx][ny] = true;
						q.add(new int[] { nx, ny });
					} else {
						tempMap[cur[0]][cur[1]] += 1;
					}
				}
			}

		}

	}

	public static boolean isMap(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
