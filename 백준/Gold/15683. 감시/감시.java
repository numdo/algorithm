import java.io.*;
import java.util.*;

/**
 * 1-5까지 감시카메라를 전부 리스트에 담는다
 * cctv의 갯수가 최대 8개 많지 않음
 * 전부 다 같은 방향으로 탐색을 한번 한다
 * cctv 클래스를 만들어서 번호 방향
 * 
 */
public class Main {
	static int N,M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static List<int[]> cctv;
	static int minVal = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctv = new ArrayList<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > 0 && map[i][j] <6) {
					cctv.add(new int[] {i,j});
				}
			}
		}
		dfs(0,map);
		System.out.println(minVal);
	}
	private static void dfs(int depth,int[][] office) {
		if(depth == cctv.size()) {
			minVal = Math.min(minVal,calculateMap(office));
			return;
		}
		int[] temp = cctv.get(depth);
		int x = temp[0];
		int y = temp[1];
		int index = office[x][y];
		
		for(int[] it : dirCCTV(index)) {
			int[][] cpyMap = copyMap(office);
			for(int idx : it) {
				simulate(x,y,idx,cpyMap);
			}
			dfs(depth+1,cpyMap);
		}
		
	}
	private static void simulate(int x,int y,int dir,int[][] office) {
		int cx = x;
		int cy = y;
		while(true) {
			cx += dx[dir];
			cy += dy[dir];
			
			if(!isMap(cx,cy) || office[cx][cy] == 6) {
				break;
			}
			if(office[cx][cy] == 0) {
				office[cx][cy] = 7;
			}
		}
	}
	private static int[][] copyMap(int[][] map){
		int[][] temp = new int[N][M];
		for(int i=0;i<N;i++) {
			temp[i] = Arrays.copyOf(map[i], M);
		}
		return temp;
	}
	private static int calculateMap(int[][] office) {
		int cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(office[i][j] == 0)cnt++;
			}
		}
		return cnt;
	}
	private static int[][] dirCCTV(int num) {
		if(num == 1) {
			return new int[][] {{0},{1},{2},{3}};
		}
		else if(num ==2) {
			return new int[][] {{0,2},{1,3}};
		}
		else if(num == 3) {
			return new int[][] {{0,1},{1,2},{2,3},{3,0}};
		}
		else if(num == 4) {
			return new int[][] {{0,1,2},{0,1,3},{0,2,3},{1,2,3}};
		}
		else{
			return new int[][] {{0,1,2,3}};
		}
	}
	private static boolean isMap(int x,int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
}
