
import java.io.*;
import java.util.*;

public class Solution {
	static int N, M;
	static char[][] map;
	static Queue<int[]> water;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visited;
	static int[][] calMap;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			calMap = new int[N][M];
			water = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
					if (map[i][j] == 'W') {
						water.add(new int[] { i, j });
						calMap[i][j] = 0;
					} else {
						calMap[i][j] = Integer.MAX_VALUE;
					}
				}
			}

			int sum = 0;
			bfs(water);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (calMap[i][j] != Integer.MAX_VALUE) {
						sum += calMap[i][j];
					}
				}
			}
			System.out.println("#" + tc + " " + sum);
		}
	}

	// 다시 하자 W인것것들을 리스트에 넣어뒀잖아 그러면 그 리스트만큼 돌려
	// int 맵을 하나 만들어서 거기에는 거리 값들을 넣어놔
	// 그래서 bfs를 하면서 최소값이면 갱신 아니면 탈출로 가자
	// bfs를 하면서
	private static void bfs(Queue<int[]> q) {
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			for (int d = 0; d < 4; d++) {
				int cx = x + dx[d];
				int cy = y + dy[d];
				if (isMap(cx, cy) && calMap[x][y] +1 < calMap[cx][cy]) {
					calMap[cx][cy] = calMap[x][y] +1;
					q.add(new int[] {cx,cy});
				}
			}
		}
	}

	private static boolean isMap(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}