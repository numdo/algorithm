import java.io.*;
import java.util.*;

public class Main {
	static int N, L, R;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] country;
	static int count = 0;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		country = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				country[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean isMove = true;
		while (isMove) {
			isMove = false;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						if(move(i,j)) {
							isMove = true;
						}
					}
				}
			}
			if(isMove) {
				count++;
			}
		}

		System.out.println(count);
	}

	public static boolean move(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> backQ = new LinkedList<>();
		q.add(new int[] { x, y, country[x][y] });
		backQ.add(new int[] { x, y });
		visited[x][y] = true;
		int sum = country[x][y];
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];

				if (isIn(nx, ny) && !visited[nx][ny] && canGo(country[nx][ny], cur[2])) {
					sum += country[nx][ny];
					visited[nx][ny] = true;
					q.add(new int[] { nx, ny, country[nx][ny] });
					backQ.add(new int[] { nx, ny });
				}
			}
		}

		int size = backQ.size();
		
		if (size > 1) {
			while (!backQ.isEmpty()) {
				int[] cur = backQ.poll();
				country[cur[0]][cur[1]] = sum / size;
			}
//			System.out.println("count : " + count);
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(country[i][j] + " ");
//				}
//				System.out.println();
//			}
			return true;
		}
		return false;
		

	}

	public static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	public static boolean canGo(int cur, int next) {
		return (Math.abs(next - cur) >= L && Math.abs(next - cur) <= R);
	}
}
