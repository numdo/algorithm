import java.io.*;
import java.util.*;

public class Main {
	static int N, map[][], dist[][];
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			dist = new int[N][N];
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			dijkstra();
			sb.append("Problem ").append(tc++).append(": ").append(dist[N-1][N-1]).append("\n");
		}
		System.out.print(sb);
	}

	public static void dijkstra() {
		PriorityQueue<Loopy> pq = new PriorityQueue<Loopy>();
		pq.offer(new Loopy(0,0,map[0][0]));
		dist[0][0] = map[0][0];
		
		while(!pq.isEmpty()) {
			Loopy cur = pq.poll();

			for(int i=0;i<4;i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(isMap(nx,ny)) {
					int nextValue = cur.value + map[nx][ny];
					if(nextValue < dist[nx][ny]) {
						dist[nx][ny] = nextValue;
						pq.offer(new Loopy(nx,ny,nextValue));
					}
				}
			}
		}
	}

	public static boolean isMap(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	static class Loopy implements Comparable<Loopy> {
		int x, y, value;

		public Loopy(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}

		@Override
		public int compareTo(Loopy o) {
			// TODO Auto-generated method stub
			return this.value - o.value;
		}

	}
}
