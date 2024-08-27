import java.io.*;
import java.util.*;
public class Main {
	static int N;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static boolean[][] visited;
	static char[][] rgb;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		rgb = new char[N][N];
		visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			rgb[i] = line.toCharArray();
		}
		int cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(visited[i][j]) continue;
				bfs(i,j);
				cnt++;
			}
		}		
		int rgbCnt = 0;
		visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(visited[i][j]) continue;
				bfs(i,j);
				rgbCnt++;
			}
		}
		System.out.println(cnt + " " + rgbCnt);
	}
	public static void bfs(int x,int y) {
		Queue<int[]> que = new LinkedList<>();
		visited[x][y] = true;
		que.add(new int[] {x,y});
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			char temp = rgb[cur[0]][cur[1]];
			if(rgb[cur[0]][cur[1]] == 'G') {
				rgb[cur[0]][cur[1]] = 'R';
			}
			for(int i=0;i<4;i++) {
				int cx = dx[i] + cur[0];
				int cy = dy[i] + cur[1];
				
				if(isMap(cx,cy) && rgb[cx][cy] == temp) {
					visited[cx][cy] = true;
					que.add(new int[] {cx,cy});
				}
			}
		}
	}
	
	public static boolean isMap(int x,int y) {
		return x>=0 && x<N && y>=0 && y<N && !visited[x][y]; 
	}
}
