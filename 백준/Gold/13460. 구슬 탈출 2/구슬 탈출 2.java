import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] map;
	static int endX, endY;
	static boolean[][][][] visited;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	static class Ball {
		int rx, ry, bx, by, count;

		public Ball(int rx, int ry, int bx, int by, int count) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.count = count;
		}

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][N][M];
		int redX = 0, redY = 0, blueX = 0, blueY = 0;
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'R') {
					redX = i;
					redY = j;
				} else if (map[i][j] == 'B') {
					blueX = i;
					blueY = j;
				} else if (map[i][j] == 'O') {
					endX = i;
					endY = j;
				}
			}
		}
		int result = bfs(redX, redY, blueX, blueY);
		System.out.println(result >10 ? -1 : result);

	}

	public static int bfs(int rx, int ry, int bx, int by) {
		Queue<Ball> q = new LinkedList<>();
		q.add(new Ball(rx, ry, bx, by, 0));
		visited[rx][ry][bx][by] = true;
		while (!q.isEmpty()) {
			Ball cur = q.poll();
			if (cur.count > 10) {
				return -1;
			}
			if (cur.rx == endX && cur.ry == endY) {
				return cur.count;
			}


			for (int d = 0; d < 4; d++) {
				int nrx = cur.rx;
				int nry = cur.ry;
				int nbx = cur.bx;
				int nby = cur.by;
				boolean red = false;
				boolean blue = false;
				while (true) {
					if (map[nrx + dx[d]][nry + dy[d]] == '#')
						break;
					nrx += dx[d];
					nry += dy[d];
					if (nrx == endX && nry == endY) {
						red = true;
						break;
					}
				}
				while (true) {
					if (map[nbx + dx[d]][nby + dy[d]] == '#')
						break;
					nbx += dx[d];
					nby += dy[d];
					if (nbx == endX && nby == endY) {
						blue = true;
						break;
					}
				}
				if (blue) {
					continue;
				}
				if (red && !blue) {
					return cur.count + 1;
				}

				if (nrx == nbx && nry == nby) {
					int redDis = Math.abs(nrx - cur.rx) + Math.abs(nry - cur.ry);
					int blueDis = Math.abs(nbx - cur.bx) + Math.abs(nby - cur.by);
					if (redDis > blueDis) {
						nrx -= dx[d];
						nry -= dy[d];
					} else {
						nbx -= dx[d];
						nby -= dy[d];
					}
				}
				if (!visited[nrx][nry][nbx][nby]) {
					visited[nrx][nry][nbx][nby] = true;
					q.add(new Ball(nrx, nry, nbx, nby, cur.count + 1));
				}
			}

		}
		return -1;
	}

}
