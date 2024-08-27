import java.io.*;
import java.util.*;

public class Main {
	
	static int N,arr[][],maxVal = 0,maxCnt = 0;
	static List<int[]> color;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("./res/SWEA7733_input.txt"));
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			maxVal = 0;
			maxCnt = 0;
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					maxCnt = Math.max(maxCnt, arr[i][j]);
				}
			}
			for(int time = 0;time<=100;time++) {
				visited = new boolean[N][N];
				
				int cnt = 0;
				for (int i=0;i<N;i++){
					for(int j=0;j<N;j++) {
						// 반대로 하면 된다 시간대보다 큰 것들만 bfs를 해서 덩어리를 판별함
						if(arr[i][j] > time && !visited[i][j]) {
							cnt++;
							bfs(i,j,time);
						}	
					}
				}
				maxVal = Math.max(cnt, maxVal);
			}
			System.out.println(maxVal);
		
	}
	public static void bfs(int x,int y,int time) {
		Queue<int[]> que = new LinkedList<>();
		visited[x][y] = true;
		que.offer(new int[] {x,y});
		
		while(!que.isEmpty()) {
			int[] temp = que.poll();
			for(int i=0;i<4;i++) {
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];
				if(isMap(nx,ny) && arr[nx][ny] > time && !visited[nx][ny]) {
					visited[nx][ny] = true;
					que.offer(new int[] {nx,ny});
				}
			}
		}
	}
	public static boolean isMap(int x,int y) {
		return x>=0 && x<N && y>=0 && y<N; 
	}

}
