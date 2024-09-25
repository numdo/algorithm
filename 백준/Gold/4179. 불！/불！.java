import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static boolean[][] fire;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] startJ = new int[2];
		int[] startF = new int[2];
		Queue<int[]> fireQ = new LinkedList<>();

		map = new char[N][M];
		visited = new boolean[N][M];
		fire = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'J') {
					startJ[0] = i;
					startJ[1] = j;
				} else if (map[i][j] == 'F') {
					fireQ.add(new int[] { i, j });
					fire[i][j] = true;
				}
			}
		}
		int result = bfs(startJ[0], startJ[1], fireQ);
//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(visited[i][j] + " ");
//			}
//			System.out.println();
//		}
		if (result == -1) {
			System.out.println("IMPOSSIBLE");
		} else {
			System.out.println(result);
		}

	}

	public static int bfs(int x, int y, Queue<int[]> fireQ) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { x, y, 0 });
		visited[x][y] = true;
		while (!q.isEmpty()) {
			int fireSize = fireQ.size();

			for (int i = 0; i < fireSize; i++) {
				int[] cur = fireQ.poll();

				for (int dir = 0; dir < 4; dir++) {
					int nx = cur[0] + dx[dir];
					int ny = cur[1] + dy[dir];

					if (isMap(nx, ny) && !fire[nx][ny] && map[nx][ny] != '#') {
						fire[nx][ny] = true;
						fireQ.offer(new int[] { nx, ny });
					}
				}
			}
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();

				for (int dir = 0; dir < 4; dir++) {
					int nx = cur[0] + dx[dir];
					int ny = cur[1] + dy[dir];

					if (isMap(nx, ny)) {
						if (!visited[nx][ny] && !fire[nx][ny] && map[nx][ny] != '#') {
							visited[nx][ny] = true;
							q.offer(new int[] { nx, ny, cur[2]+1 });
						}
					} else {
						return cur[2] + 1;
					}
				}
			}
			
		}
		return -1;
	}

	public static boolean isMap(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
