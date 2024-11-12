import java.io.*;
import java.util.*;

public class Solution {
	static int N, M;
	static char[][] pool;
	static Queue<int[]> q;
	static int[][] result;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			pool = new char[N][M];
			q = new LinkedList<>();
			result = new int[N][M];
			for (int i = 0; i < N; i++) {
				Arrays.fill(result[i], -1);
			}
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					pool[i][j] = line.charAt(j);
					if (pool[i][j] == 'W') {
						q.add(new int[] { i, j });
						result[i][j] = 0;
					}
				}
			}
			int answer = bfs();
			System.out.println("#" + tc +" " + answer);
		}
	}

	private static int bfs() {
		int count = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = cur[0] + dx[d];
					int ny = cur[1] + dy[d];
					if (!isIn(nx, ny) || result[nx][ny] != -1)
						continue;
					result[nx][ny] = count;
					q.add(new int[] { nx, ny });
				}
			}
			count++;
		}
		int sum = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				sum+= result[i][j];
			}
		}
		return sum;
	}

	public static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
