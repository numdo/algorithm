
import java.util.*;
import java.io.*;
public class Main {
	static int N,M;
	static int[][] map;
	static boolean visited[][][];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][2];
		for(int i=0;i<N;i++) {
			String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
		}
		System.out.println(bfs());
	}
	private static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0,0,0});
		visited[0][0][0] = true;
		int count = 1;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i = 0;i<size;i++) {
				int[] temp = q.poll();
				if(temp[0] == N-1 && temp[1] == M-1) {
					return count;
				}
				for(int j=0;j<4;j++) {
					int cx = temp[0] + dx[j];
					int cy = temp[1] + dy[j];
					int wall = temp[2];	// 0이면 벽을 부술수 있는 상태 1이면 벽을 못부수는 상태

					if(isMap(cx,cy)) {
						if(map[cx][cy] == 0 && !visited[cx][cy][wall]) {
							visited[cx][cy][wall] = true;
							q.add(new int[] {cx,cy,wall});
						}
						// 벽을 만났는데 벽을 부술 수 있는 경우
						else if(map[cx][cy] == 1 && wall == 0 && !visited[cx][cy][1]){
							visited[cx][cy][1] = true;
                            q.add(new int[] {cx, cy, 1});						}
					}
				}
			}
			count++;
		}
		return -1;
	}
	private static boolean isMap(int x,int y) {
		return x>=0 && x<N && y>=0 && y<M ;
	}
}
