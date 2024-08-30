import java.io.*;
import java.util.*;
public class Main {
	static int N,M;
	static int[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		int result = bfs(0,0);
		System.out.println(result);
	}
	public static int bfs(int x,int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		boolean[][] visited = new boolean[N][M];
		visited[x][y] = true;
		int count  = 1;
		
		while(!q.isEmpty()) {
			int size = q.size();
			count++;
			for(int i=0;i<size;i++) {
				int[] temp = q.poll();
				for(int d = 0;d<4;d++) {
					int cx = temp[0] + dx[d];
					int cy = temp[1] + dy[d];
					if(cx == N-1 && cy == M-1) return count;
					if(isMap(cx,cy) && !visited[cx][cy]) {
						if(map[cx][cy] == 1) {
							visited[cx][cy] = true;
							q.add(new int[] {cx,cy});
						}
					}
				}
			}
		}
		return -1;
	}
	public static boolean isMap(int x, int y) {
		return x>=0 && x<N && y>= 0 && y<M;
	}
}
