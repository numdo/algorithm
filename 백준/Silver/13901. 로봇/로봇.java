import java.io.*;
import java.util.*;

public class Main {
	static int N,M,K;
	static int[][] map;
	static int startX,startY;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[] dir;
	static int answerX,answerY;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		dir = new int[4];
		map = new int[N][M];
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = -1;
		}
		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());
		map[startX][startY] = 1;

		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			dir[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		answerX = startX;
		answerY = startY;
		bfs();
		
		System.out.println(answerX + " " + answerY);
	}
	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {startX,startY,0});

		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int i=0;i<4;i++) {
				int d = (cur[2] +i)%4;
				int nx = cur[0] + dx[dir[d]];
				int ny = cur[1] + dy[dir[d]];
				if(isIn(nx,ny) && map[nx][ny] == 0) {
					map[nx][ny]++;
					q.add(new int[] {nx,ny,d});
					answerX = nx;
					answerY = ny;
					break;
				}
			}
//			for(int i=0;i<N;i++) {
//				for(int j=0;j<M;j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("===================");
		}
	}
	public static boolean isIn(int x,int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
}
