import java.io.*;
import java.util.*;

public class Main {
	static int N,M,X,Y,K;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int[][] map;
	static int[] command;
	static class Dice{
		int x,y,bottom,north,east,west,south,top;

		

		public Dice(int x, int y, int bottom, int north, int east, int west, int south, int top) {
			super();
			this.x = x;
			this.y = y;
			this.bottom = bottom;
			this.north = north;
			this.east = east;
			this.west = west;
			this.south = south;
			this.top = top;
		}

		public void move(int dir,int val) {
			int temp = 0;
			x+=dx[dir];
			y+=dy[dir];
			
			if(dir == 0) {
				temp = east;
				east = top;
				top = west;
				west = bottom;
				bottom = temp;
			}
			 else if(dir == 1) {
				temp = west;
				west = top;
				top = east;
				east = bottom;
				bottom = temp;
			}
			else if(dir==2) {
				temp = north;
				north=top;
				top = south;
				south=bottom;
				bottom = temp;
			}
			else if(dir == 3) {
				temp = south;
				south = top;
				top = north;
				north = bottom;
				bottom = temp;
			}
			if(val == 0) {
				map[x][y] = bottom;
			}
			else {
				bottom = map[x][y];
				map[x][y] = 0;
			}
		}

		@Override
		public String toString() {
			return "Dice [x=" + x + ", y=" + y + ", bottom=" + bottom + ", north=" + north + ", east=" + east
					+ ", west=" + west + ", south=" + south + ", top=" + top + "]";
		} 
		
		
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Dice dice = new Dice(X,Y,0,0,0,0,0,0);
		map = new int[N][M];
		command = new int[K];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<K;i++) {
			command[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		System.out.print(simulate(dice));
	}
	public static String simulate(Dice dice) {
		StringBuilder sb = new StringBuilder();
		 for(int i=0;i<K;i++) {
			 int d = command[i];
			 int nx = dice.x + dx[d];
			 int ny = dice.y + dy[d];
			 if(!isIn(nx,ny)) {
				continue; 
			 }
			 dice.move(d, map[nx][ny]);
			 sb.append(dice.top);
			 sb.append("\n");
//			 System.out.println(dice);

		 }
		 return sb.toString();
	}
	public static boolean isIn(int x,int y) {
		return x>=0 && x<N && y >= 0 && y<M;
	}

}
