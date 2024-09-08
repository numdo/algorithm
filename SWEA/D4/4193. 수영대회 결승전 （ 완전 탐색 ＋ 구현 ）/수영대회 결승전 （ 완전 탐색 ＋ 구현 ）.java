import java.io.*;
import java.util.*;

public class Solution {
	static int N, answer;
	static int map[][];
	static int startX, startY, endX, endY;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			answer = 0;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			StringTokenizer st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
			System.out.println("#" + tc + " " + bfs(startX,startY));
		}
	}

	// 토네이도는 %3 < 2 일때 이동을 못한다.
	// %3 == 2일때만 가능
	public static int bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		visited[x][y] = true;

		if (map[x][y] == 2) {
			q.offer(new int[] { x, y, 2 });
		} else {
			q.offer(new int[] { x, y, 0 });
		}

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			if (cur[0] == endX && cur[1] == endY) {
				return cur[2];
			}
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[0] + dx[dir];
				int ny = cur[1] + dy[dir];

				if (isMap(nx, ny) && !visited[nx][ny] && map[nx][ny] !=1) {
					if (map[nx][ny] == 2) {
						if (cur[2] % 3 == 2) {
							map[nx][ny] = 0;
							visited[nx][ny] = true;
							q.add(new int[] { nx, ny, cur[2] + 1 });
						} else if (cur[2] % 3 == 1) {
							q.add(new int[] { cur[0], cur[1], cur[2] + 1 });
						} else {
							q.add(new int[] { cur[0], cur[1], cur[2] + 1 });
						}
					} else if (map[nx][ny] == 0) {
						visited[nx][ny] = true;
						q.add(new int[] { nx, ny, cur[2] + 1 });
					}
				}

			}

		}
		return -1;
	}

	public static boolean isMap(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
