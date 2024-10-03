import java.io.*;
import java.util.*;

public class Main {
	static int N,M;
	static char[][] map;
	static boolean[][][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static class People{
		int x,y,dist,key;

		public People(int x, int y, int dist, int key) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.key = key;
		}
		
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][64];
		People people = null;
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == '0') {
					people = new People(i,j,0,0);
					map[i][j] = '.';
				}
			}
		}
		System.out.println(bfs(people));
	}
	public static int bfs(People p) {
		Queue<People> q = new LinkedList<>();
		q.add(p);
		visited[p.x][p.y][0] = true;
		
		while(!q.isEmpty()) {
			People cur = q.poll();
			
			for(int i=0;i<4;i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(isMap(nx,ny) && !visited[nx][ny][cur.key] && map[nx][ny] != '#') {
//					System.out.println("x : " + nx + " y : " + ny);
//					System.out.println("key : " + cur.key);
					if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
						int newKey = cur.key | (1<<(map[nx][ny] - 'a'));
						if(!visited[nx][ny][newKey]) {
							visited[nx][ny][newKey] = true;
							q.add(new People(nx,ny,cur.dist+1,newKey));
						}
					}
					else if(map[nx][ny] == '.') {
						visited[nx][ny][cur.key] = true;
						q.add(new People(nx,ny,cur.dist+1,cur.key));
					}
					else if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
						if((cur.key & (1 << (map[nx][ny] - 'A'))) != 0) {
							visited[nx][ny][cur.key] = true;
							q.add(new People(nx,ny,cur.dist+1,cur.key));
						}
					}
					else if(map[nx][ny] == '1') {
						return cur.dist+1;
					}
				}
			}
		}
		return -1;
	}
	public static boolean isMap(int x,int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
}
