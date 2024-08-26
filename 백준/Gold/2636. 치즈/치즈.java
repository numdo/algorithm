
import java.io.*;
import java.util.*;
public class Main {
	static int N,M,grid[][],xy[];
	static boolean[][] visited;
	static char[][] melt;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		grid = new int[N][M];
		melt = new char[N][M];
		xy = new int[2];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 테두리에서 사방탐색으로 부식되는 곳을 찾는다.
		
		int cnt = 0;
		int cheeze = 0;
		while(true) {
			visited = new boolean[N][M];
			
			int temp = fuzeCheeze(0,0);
			multCheeze();
//			for(int i=0;i<N;i++) {
//				for(int j=0;j<M;j++) {
//					System.out.print(grid[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("====================");
			if(temp > 0) {
				cheeze = temp;
			}
			else{
				break;
			}
			cnt++;

		}
		System.out.println(cnt);
		System.out.println(cheeze);
	}
	public static int fuzeCheeze(int x,int y) {
		Queue<int[]> queue = new LinkedList<>();
		visited[x][y] = true;
		queue.offer(new int[] {x,y});
		int cnt = 0;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			for(int i=0;i<4;i++) {
				int cx = cur[0] + dx[i];
				int cy = cur[1] + dy[i];
				if(isMap(cx,cy) && !visited[cx][cy]) {
					if(grid[cx][cy] == 0) {
						visited[cx][cy] = true;
						queue.offer(new int[] {cx,cy});
					}
					else if(grid[cx][cy] == 1){
						cnt++;
						grid[cx][cy] = 9;
						melt[cx][cy] = 'c';
						
					}
//					else if(grid[cx][cy] == 2) {
//						cnt++;
//						visited[cx][cy] = true;
//						grid[cx][cy] = 0;
//						queue.offer(new int[] {cx,cy});
//					}
				}
			}
		}
		return cnt;
	}
	public static void multCheeze() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(!visited[i][j] && melt[i][j] == 'c') {
					grid[i][j] = 0;
					melt[i][j] = ' ';
				}
			}
		}
	}
	public static boolean isMap(int x,int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
}
