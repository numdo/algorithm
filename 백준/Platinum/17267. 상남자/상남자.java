import java.io.*;
import java.util.*;

public class Main {
	static int N,M,L,R,map[][];
	static int startX,startY;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] visited;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = line.charAt(j) - '0';
				if(map[i][j] == 2) {
					startX = i;
					startY = j;
				}
			}
		}
		bfs(startX,startY);
		int answer = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
//				System.out.print(visited[i][j] + " ");
				if(visited[i][j]) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}

	public static void bfs(int x,int y) {
		PriorityQueue<Point> q = new PriorityQueue<>();
		q.offer(new Point (x,y,L,R));
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			int cx = cur.x;
			int cy = cur.y;
			int left = cur.left;
			int right = cur.right;
			for(int dir = 0;dir<4;dir++) {
				int nx = cx + dx[dir];
				int ny = cy + dy[dir];
				
				if(isMap(nx,ny) && !visited[nx][ny] && map[nx][ny] == 0) {
					if(dir == 0 || dir == 1) {
						visited[nx][ny] = true;
//						int[] temp = goUpDown(nx,ny,dir);
						q.offer(new Point (nx,ny,left,right));
					}
					else if(dir == 2 && left >0) {
						visited[nx][ny] = true;
						q.offer(new Point (nx,ny,left-1,right));
					}
					else if(dir == 3 && right > 0) {
						visited[nx][ny] = true;
						q.offer(new Point (nx,ny,left,right-1));
					}

				}
			}
		}
	}
	public static int[] goUpDown(int x,int y,int dir) {
		while(true) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			// 범위를 벗어나거나 방문한 적 있거나 벽인 경우 종료
			if(!isMap(nx, ny) || visited[nx][ny] || map[nx][ny] != 0) {
				return new int[] {x, y};  // 이전 위치로 돌아감
			}
			
			// 새로운 위치 방문 처리
			x = nx;
			y = ny;
			visited[x][y] = true;
		}
	}
	public static boolean isMap(int x,int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
	static class Point implements Comparable<Point>{
		int x,y,left,right;

		public Point(int x, int y, int left, int right) {
			super();
			this.x = x;
			this.y = y;
			this.left = left;
			this.right = right;
		}

		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
            return (left+right) <= (o.left + o.right) ? 1 : -1;
		}
		
	}
}
