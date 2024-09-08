import java.io.*;
import java.util.*;


public class Main {

	static int N,M,K;
	static int[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		System.out.println(bfs(0,0));
		
	}

	public static int bfs(int x,int y) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] visited = new boolean[N][M][K+1];
		
		q.offer(new int[] {x,y,K});
		visited[x][y][K] = true;
		int count = 1;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] cur = q.poll();
				int cx = cur[0];
				int cy = cur[1];
				int wall = cur[2];
				if(cx == N-1 && cy == M-1) {
					return count;
				}
				for(int dir = 0;dir<4;dir++) {
					int nx = cx + dx[dir];
					int ny = cy + dy[dir];
					
					if(isMap(nx,ny)) {
						if(map[nx][ny] == 0 && !visited[nx][ny][wall]) {
							visited[nx][ny][wall] = true;
							q.offer(new int[] {nx,ny,wall});
						}
						else if(map[nx][ny] == 1 && wall >0  && !visited[nx][ny][wall-1]) {
							visited[nx][ny][wall-1] = true;
							q.offer(new int[] {nx,ny,wall-1});
						}
					}
					
				}
			}
			count++;
		}
		return -1;
		
	}
	public static boolean isMap(int x,int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
	
}
