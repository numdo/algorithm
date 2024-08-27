
import java.io.*;
import java.util.*;

public class Solution {
	static int N,maxSize,startX,startY;
	static boolean[][] visited;
	static int[][] map;
	static boolean[] used;
	static int[] dx = {1,1,-1,-1};
	static int[] dy = {1,-1,-1,1};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("./res/SWEA2105_input.txt"));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			maxSize = -1;
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(visited[i][j]) continue;
					used = new boolean[101];
					startX = i;
					startY = j;
					dfs(0,i,j,0,0);
				}
			}
			System.out.println("#" + tc + " " + maxSize);
		}
	}
	public static void dfs(int depth,int x,int y,int dir,int turnCnt) {
		if(depth >0 && x==startX && y == startY && turnCnt >=3) {
			maxSize = Math.max(maxSize, depth);
			return;
		}
		
		for(int i=dir;i<4;i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			if(isMap(cx,cy) && !used[map[cx][cy]]) {
				visited[cx][cy] = true;
				used[map[cx][cy]] = true;
				dfs(depth+1,cx,cy,i,turnCnt + (dir != i ? 1 : 0));
				visited[cx][cy] = false;
				used[map[cx][cy]] = false;
			}
			if(i == 1 && dir == 0) break;
		}
		
	}
	public static boolean isMap(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N && !visited[x][y];
	}
}
