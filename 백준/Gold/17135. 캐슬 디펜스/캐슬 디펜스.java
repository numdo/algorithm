import java.io.*;
import java.util.*;

public class Main {
	static int N,M,D;
	static int map[][];
	static boolean visited[];
	static int[] dx = {0,-1,0};
	static int[] dy = {-1,0,1}; 
	static int answer = 0;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N+1][M];
		visited = new boolean[M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(0,0,new int[3]);
		System.out.println(answer);
	}
	public static void comb(int depth,int value,int[] loc) {
		if(depth==3) {
			answer = Math.max(answer,simulate(loc)); 
			return;
		}
		
		for(int i=value;i<M;i++) {
			if(visited[i]) continue;
			visited[i] = true;
			loc[depth] = i;
			comb(depth+1,i,loc);
			visited[i] = false;
		}
	}
	public static int simulate(int[] loc) {
		int cnt = 0;
		int[][] temp = cpyMap();
		for(int t=0;t<N;t++) {
			List<int[]> list = new ArrayList<>();
			for(int i=0;i<3;i++) {
				int[] remove = calDis(N,loc[i],temp);
				if(!Objects.isNull(remove)) {
					list.add(remove);
				}
			}
			for(int[] it : list) {
				if(temp[it[0]][it[1]] == 1) {
					cnt++;
				}
				temp[it[0]][it[1]] = 0;
			}
//			for(int i=0;i<N;i++) {
//				for(int j=0;j<M;j++) {
//					System.out.print(temp[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("====================");
			move(temp);
			
		}
		return cnt;
	}
	public static int[] calDis(int x,int y,int[][] temp) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y,0});
		boolean[][] visited = new boolean[N+1][M];
		visited[x][y] = true;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				int[] cur = q.poll();
				int cx = cur[0];
				int cy = cur[1];
				if(cur[2] >= D) {
					return null;
				}
				for(int d=0;d<3;d++) {
					int nx = cx + dx[d];
					int ny = cy + dy[d];
					if(isIn(nx,ny) && !visited[nx][ny] && temp[nx][ny] !=2) {
						if(temp[nx][ny] == 1) {
							return new int[] {nx,ny};
						}
						q.add(new int[] {nx,ny,cur[2]+1});
						visited[nx][ny] = true;
					}
				}
			}
			
		}
		return null;
		
	}
	public static boolean isIn(int x,int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
	public static void move(int[][] temp) {
		for(int i=N-1;i>0;i--) {
			temp[i] = Arrays.copyOf(temp[i-1], M);
		}
		Arrays.fill(temp[0], 0);
	}
	public static int[][] cpyMap(){
		int[][] temp = new int[N+1][M];
		for(int i=0;i<N;i++) {
			temp[i] = Arrays.copyOf(map[i], M);
		}
		Arrays.fill(temp[N], 2);
		return temp;
	}
}
