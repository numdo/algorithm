import java.io.*;
import java.util.*;

public class Main {
	static int L, R, C;
	static char building[][][];
	static int dl[] = { 0, 0, 0, 0, -1, 1 };
	static int dr[] = { -1, 1, 0, 0, 0, 0 };
	static int dc[] = { 0, 0, -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if (L == 0 || R == 0 || C == 0) {
				break;
			}
			building = new char[L][R][C];
			int l = 0, r = 0, c = 0;
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					String line = br.readLine();
					for (int k = 0; k < C; k++) {
						building[i][j][k] = line.charAt(k);
						if (building[i][j][k] == 'S') {
							l = i;
							r = j;
							c = k;
						}
					}

				}
				br.readLine();
			}
			int answer = bfs(l,r,c);
			if(answer == -1) {
				System.out.println("Trapped!");
			}
			else {
				System.out.println("Escaped in " + answer + " minute(s).");
			}
		}

	}

	public static int bfs(int l, int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { l, r, c, 0 });
		boolean visited[][][] = new boolean[L][R][C];
		visited[l][r][c] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if(building[cur[0]][cur[1]][cur[2]] == 'E') {
				return cur[3];
			}
			for (int i = 0; i < 6; i++) {
				int nl = cur[0] + dl[i];
				int nr = cur[1] + dr[i];
				int nc = cur[2] + dc[i];
				
				if(isIn(nl,nr,nc) && !visited[nl][nr][nc] && building[nl][nr][nc] != '#') {
					visited[nl][nr][nc] = true;
					q.add(new int[] {nl,nr,nc,cur[3]+1});
				}
			}
		}
		return -1;
	}

	public static boolean isIn(int l, int r, int c) {
		return l >= 0 && l < L && r >= 0 && r < R && c >= 0 && c < C;
	}
}
