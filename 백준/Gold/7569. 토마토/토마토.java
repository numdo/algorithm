import java.io.*;
import java.util.*;

public class Main {
	static int M, N, H;
	static int dx[] = { -1, 1, 0, 0, 0, 0 };
	static int dy[] = { 0, 0, -1, 1, 0, 0 };
	static int dz[] = { 0, 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		int box[][][] = new int[N][M][H];
		int visited[][][] = new int[N][M][H];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					visited[j][k][i] = Integer.MAX_VALUE;
					
				}
			}
		}
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					box[j][k][i] = Integer.parseInt(st.nextToken());
					if(box[j][k][i] == -1) {
						visited[j][k][i] = -1;
					}
				}
			}
		}
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if(box[j][k][i] == 1) {
						bfs(j,k,i,box,visited);
					}
					
				}
			}
		}
		int result = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if(visited[j][k][i] >0) {
						if(visited[j][k][i] == Integer.MAX_VALUE) {
							System.out.println(-1);
							return;
						}
						result = Integer.max(result,visited[j][k][i]);	
					}
				}
			}
		}
		System.out.println(result);
	}

	public static void bfs(int x, int y, int z, int[][][] box, int[][][] visited) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { x, y, z, 0 });
		
		visited[x][y][z] = 0;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			int cz = cur[2];
			int cval = cur[3];
			for (int i = 0; i < 6; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				int nz = cz + dz[i];
				if (isBox(nx, ny, nz) && visited[nx][ny][nz] > (cval+1) && box[nx][ny][nz] == 0) {
					visited[nx][ny][nz] = cval+1;
					q.add(new int[] { nx, ny, nz, cval + 1 });
				}
			}
		}
	}

	public static boolean isBox(int x, int y, int z) {
		return x >= 0 && x < N && y >= 0 && y < M && z >= 0 && z < H;
	}
}
