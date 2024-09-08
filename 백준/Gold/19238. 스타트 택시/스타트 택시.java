import java.io.*;
import java.util.*;

public class Main {

	static class Customer implements Comparable<Customer> {
		int sx, sy, ex, ey, distance;

		public Customer(int sx, int sy, int ex, int ey) {
			this.sx = sx;
			this.sy = sy;
			this.ex = ex;
			this.ey = ey;
			this.distance = 0;
		}

		@Override
		public int compareTo(Customer o) {
			// 거리 비교 -> 행 우선 -> 열 우선 정렬
			if (this.distance == o.distance) {
				if (this.sx == o.sx) {
					return this.sy - o.sy;
				}
				return this.sx - o.sx;
			}
			return this.distance - o.distance;
		}
	}

	static int N, M, fuel, map[][];
	static int startX, startY;
	static List<Customer> startCustomers;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); // 승객 수
		fuel = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken()) - 1;
		startY = Integer.parseInt(st.nextToken()) - 1;

		startCustomers = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken()) - 1;
			int sy = Integer.parseInt(st.nextToken()) - 1;
			int ex = Integer.parseInt(st.nextToken()) - 1;
			int ey = Integer.parseInt(st.nextToken()) - 1;

			startCustomers.add(new Customer(sx, sy, ex, ey));
		}

		// 승객 수 만큼 반복
		for (int i = 0; i < M; i++) {
			// 택시에서 가장 가까운 승객을 찾음 (BFS로 모든 승객 거리 계산)
			if (!findClosestCustomer()) {
				System.out.println(-1);
				return;
			}
		}

		// 모든 승객을 이동시켰으면 연료 출력
		System.out.println(fuel);
	}

	// 가장 가까운 승객을 찾고, 그 승객을 목적지까지 데려다 줌
	public static boolean findClosestCustomer() {
		int[][] distToTaxi = bfs(startX, startY);
		Customer closest = null;

		// 승객 거리 갱신
		for (Customer it : startCustomers) {
			it.distance = distToTaxi[it.sx][it.sy];
			if (it.distance == -1) {
				return false; // 승객까지 갈 수 없는 경우
			}
		}

		// 승객들 중에서 가장 가까운 승객 선택
		Collections.sort(startCustomers);
		closest = startCustomers.get(0);

		// 승객에게 도착 후, 연료 계산
		fuel -= closest.distance;
		if (fuel < 0)
			return false;

		// 승객을 목적지로 데려다 주기
		int[][] distToDest = bfs(closest.sx, closest.sy);
		int distToGo = distToDest[closest.ex][closest.ey];
		if (distToGo == -1 || fuel < distToGo)
			return false;

		// 목적지로 도착 후 연료 계산
		fuel -= distToGo;
		fuel += distToGo * 2;

		// 택시의 새로운 위치를 승객의 목적지로 업데이트
		startX = closest.ex;
		startY = closest.ey;

		// 승객 제거
		startCustomers.remove(0);
		return true;
	}

	// BFS로 현재 위치에서 모든 좌표까지의 최단 거리를 계산함
	public static int[][] bfs(int sx, int sy) {
		Queue<int[]> q = new LinkedList<>();
		int[][] dist = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], -1);
		}
		q.offer(new int[] { sx, sy });
		dist[sx][sy] = 0;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			for (int dir = 0; dir < 4; dir++) {
				int nx = cx + dx[dir];
				int ny = cy + dy[dir];
				if (isMap(nx, ny) && dist[nx][ny] == -1 && map[nx][ny] == 0) {
					dist[nx][ny] = dist[cx][cy] + 1;
					q.offer(new int[] { nx, ny });
				}
			}
		}
		return dist;
	}

	// 유효한 좌표인지 확인
	public static boolean isMap(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
