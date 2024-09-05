import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] map;
	static List<int[]> water;
	static int[] start;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		water = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == '*') {
					water.add(new int[] { i, j });
				} else if (map[i][j] == 'S') {
					start = new int[] { i, j };
				}
			}
		}

		int answer = bfs(start[0], start[1]);

		if (answer == -1) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(answer);
		}
	}

	public static int bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> wq = new LinkedList<>();
		boolean waterVisited[][] = new boolean[N][M];
		boolean visited[][] = new boolean[N][M];
		
		q.offer(new int[] { x, y });
		visited[x][y] = true;
		for(int[] it: water) {
			wq.offer(new int[] { it[0], it[1] });
			waterVisited[it[0]][it[1]] = true;
		}

		int depth = 1;
		while (!q.isEmpty()) {
			int waterSize = wq.size();
			for (int i = 0; i < waterSize; i++) {
				int[] cur = wq.poll();
				for (int dir = 0; dir < 4; dir++) {
					int nx = cur[0] + dx[dir];
					int ny = cur[1] + dy[dir];
					if (isMap(nx, ny) && !waterVisited[nx][ny] && map[nx][ny] == '.') {
						waterVisited[nx][ny] = true;
						wq.offer(new int[] { nx, ny });
					}
				}
			}
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				for (int dir = 0; dir < 4; dir++) {
					int nx = cur[0] + dx[dir];
					int ny = cur[1] + dy[dir];
					if (isMap(nx, ny) && !visited[nx][ny] && !waterVisited[nx][ny] && map[nx][ny] != 'X') {
						if (map[nx][ny] == 'D') {
							return depth;
						}
						visited[nx][ny] = true;
						q.offer(new int[] { nx, ny });
					}
				}
			}

			depth++;
		}
		return -1;
	}

	public static boolean isMap(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
