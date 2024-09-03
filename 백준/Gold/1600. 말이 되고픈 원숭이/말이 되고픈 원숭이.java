import java.io.*;
import java.util.*;

/**
 * 
 */


public class Main {
	static int N,M,K,map[][];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[] horseX = {-1,-2,-2,-1,1,2,2,1};
	static int[] horseY = {-2,-1,1,2,2,1,-1,-2};
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfs(0,0,0));
	}
	public static int bfs(int x,int y,int k) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y,k});
		boolean visited[][][] = new boolean[N][M][K+1];
		visited[x][y][k] = true;
		int cost = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] cur = q.poll();
				if(cur[0] == N-1 && cur[1] == M-1) {
					return cost;
				}
				
				for(int dir=0;dir<4;dir++) {
					int nx = cur[0] + dx[dir];
					int ny = cur[1] + dy[dir];
					int horse = cur[2];
					if(isMap(nx,ny) && !visited[nx][ny][horse] && map[nx][ny] == 0) {
						visited[nx][ny][horse] = true;
						q.add(new int[] {nx,ny,horse});
					}
				}
				if(cur[2] < K) {
					for(int dir=0;dir<8;dir++) {
						int nx = cur[0] + horseX[dir];
						int ny = cur[1] + horseY[dir];
						int horse = cur[2] + 1;
						if(isMap(nx,ny) && !visited[nx][ny][horse] && map[nx][ny] == 0) {
							visited[nx][ny][horse] = true;
							q.add(new int[] {nx,ny,horse});
						}
					}
				}
			}
			cost++;
		}
		return -1;
	}
	public static boolean isMap(int x,int y) {
		return x>=0 && x<N && y >=0 && y<M;
	}
}
