import java.io.*;
import java.util.*;

public class Main {
	static int N,M;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] map;
	static int[][] answer;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		answer = new int[N][M];
		arr = new int[N*M+1];
		int color = 1;
		for (int i = 0; i < N; i++) {
			String line=br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 0 && answer[i][j] == 0) {
					bfs(i,j,color);
					color++;
				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 1) {
					Set<Integer> set = new HashSet<>();
					int sum = 1;
					for(int d=0;d<4;d++) {
						int nx = dx[d] + i;
						int ny = dy[d] + j;
						
						if(isIn(nx,ny) && map[nx][ny] == 0) {
							int cid = answer[nx][ny];
							if(!set.contains(cid)) {
								set.add(cid);
								sum += arr[cid];
							}
						}
					}
					sb.append(sum % 10);
				} else {
					sb.append(0);
				}
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	public static void bfs(int x,int y,int color) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		answer[x][y] = color;
		int cnt = 1;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int i=0;i<4;i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(isIn(nx,ny) && answer[nx][ny] == 0 && map[nx][ny] == 0) {
					answer[nx][ny] = color;
					q.add(new int[] {nx,ny});
					cnt++;
				}
			}
		}
		arr[color] = cnt;
	}
	public static boolean isIn(int x,int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
}
