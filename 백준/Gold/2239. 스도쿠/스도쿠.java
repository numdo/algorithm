import java.io.*;
import java.util.*;

public class Main {
	static final int N = 9;
	static int[][] map = new int[N][N];
	static boolean[][][] used = new boolean[N+1][N+1][N+1];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = (line.charAt(j) - '0');
			}
		}
		sudokoo(0,0);
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();

		}
		
	}
	public static boolean sudokoo(int x,int y) {
		if(y== N) {
			y=0;
			x++;
		}
		if(x== N) {
			return true;
		}
		if(map[x][y] != 0 ) {
			return sudokoo(x,y+1);
		}
		for(int i=1;i<=N;i++) {
			if(isValid(x,y,i)) {
				map[x][y] = i;
				if(sudokoo(x,y+1)) {
					return true;
				}
				map[x][y] = 0;
			}
		}
		return false;
	}
	public static boolean isValid(int x,int y,int num) {
		for(int i=0;i<N;i++) {
			if(map[x][i] ==num || map[i][y] == num) {
				return false;
			}
		}
		
		int xVal = (x/3)*3;
		int yVal = (y/3)*3;
		for(int i=xVal;i<xVal+3;i++) {
			for(int j=yVal;j<yVal+3;j++) {
				if(map[i][j] == num) {
					return false;
				}
			}
		}
		return true;
	}
}
