import java.io.*;
import java.util.*;


public class Solution {
	static int N,map[][];
	static String dir;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static Queue<int[]> q;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			dir = st.nextToken();
			map = new int[N][N];
			q = new LinkedList<>();
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j =0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			make2048();
			System.out.println("#" + tc);
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			
		}
	}
	public static void make2048() {
		int direction = 0;
		boolean[][] visited = new boolean[N][N];
		
		if(dir.equals("up")) {
			// 업이면 맨윗줄 에서부터 아래로 내려가면서
			// 자기 위에 무언가를 만날때까지 반복 -> 0이면 반복
			// 자기랑 다르면 그 위치에서 스탑
			// 자기랑 같으면 값 바꿔주고 스탑
			for(int i=1;i<N;i++) {
				for(int j=0;j<N;j++) {
					int nx = i + dx[0];
					int ny = j + dy[0];
					int val = map[i][j];
					while(true) {
						if(isMap(nx,ny)) {
							if(map[nx][ny] == 0){
								map[nx][ny] = val;
								map[nx+dx[1]][ny+dy[1]] = 0;
								nx += dx[0];
								ny += dy[0];
							}
							else if (map[nx][ny] == val && !visited[nx][ny]) {
								visited[nx][ny] = true;
								map[nx][ny] *=2; 
								map[nx+dx[1]][ny+dy[1]] = 0;
								break;
							}
							else {
								break;
							}
						}
						else {
							break;
						}
					}
					
				}
			}
			
		}
		
		
		else if(dir.equals("down")) {
			for(int i=N-2;i>=0;i--) {
				for(int j=0;j<N;j++) {
					int nx = i + dx[1];
					int ny = j + dy[1];
					int val = map[i][j];
					while(true) {
						if(isMap(nx,ny)) {
							if(map[nx][ny] == 0){
								map[nx][ny] = val;
								map[nx+dx[0]][ny+dy[0]] = 0;
								nx += dx[1];
								ny += dy[1];
							}
							else if (map[nx][ny] == val && !visited[nx][ny]) {
								visited[nx][ny] = true;
								map[nx][ny] *=2; 
								map[nx+dx[0]][ny+dy[0]] = 0;
								break;
							}
							else {
								break;
							}
						}
						else {
							break;
						}
					}
				}	
			}
		}
		
		
		else if(dir.equals("left")) {
			for(int i=0;i<N;i++) {
				for(int j=1;j<N;j++) {
					int nx = i + dx[2];
					int ny = j + dy[2];
					int val = map[i][j];
					while(true) {
						if(isMap(nx,ny)) {
							if(map[nx][ny] == 0){
								map[nx][ny] = val;
								map[nx+dx[3]][ny+dy[3]] = 0;
								nx += dx[2];
								ny += dy[2];
							}
							else if (map[nx][ny] == val && !visited[nx][ny]) {
								visited[nx][ny] = true;
								map[nx][ny] *=2; 
								map[nx+dx[3]][ny+dy[3]] = 0;
								break;
							}
							else {
								break;
							}
						}
						else {
							break;
						}
					}
					
				}		
			}
		}
		
		else if(dir.equals("right")) {
			for(int i=0;i<N;i++) {
				for(int j=N-2;j>=0;j--) {
					int nx = i + dx[3];
					int ny = j + dy[3];
					int val = map[i][j];
					while(true) {
						if(isMap(nx,ny)) {
							if(map[nx][ny] == 0){
								map[nx][ny] = val;
								map[nx+dx[2]][ny+dy[2]] = 0;
								nx += dx[3];
								ny += dy[3];
							}
							else if (map[nx][ny] == val && !visited[nx][ny]) {
								visited[nx][ny] = true;
								map[nx][ny] *=2; 
								map[nx+dx[2]][ny+dy[2]] = 0;
								break;
							}
							else {
								break;
							}
						}
						else {
							break;
						}
					}
					
				}		
			}
		}
		
		
	}
	public static boolean isMap(int x,int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
}
