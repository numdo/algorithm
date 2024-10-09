import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static int[] dx = { 0, 1, 1 };
	static int[] dy = { 1, 0, 1 };

	static class Pipe {
		int x, y, type;

		public Pipe(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}

	}

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
		System.out.println(bfs(new Pipe(0, 1, 0)));
	}

	public static int bfs(Pipe p) {
		Queue<Pipe> q = new LinkedList<>();
		q.add(p);
		int count = 0;
		while (!q.isEmpty()) {
			Pipe cur = q.poll();
			int cnt = 0;
			if (cur.x == N - 1 && cur.y == N - 1) {
				count++;
			}
			for (int dir = 0; dir < 3; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (isMap(nx, ny) && map[nx][ny] != 1) {
					cnt++;
					if (dir == 0 && cur.type != 1) {
						q.add(new Pipe(nx, ny, 0));
					} else if (dir == 1 && cur.type != 0) {
						q.add(new Pipe(nx, ny, 1));
					} else if (dir == 2 && cnt == 3) {
						q.add(new Pipe(nx, ny, 2));
					}
				}
			}

		}
		
		return count;
	}

	public static boolean isMap(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
