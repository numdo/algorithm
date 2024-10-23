import java.io.*;
import java.util.*;

public class Main {
	static int N,M;
	static int X,Y;
	static int[] dir;
	static int[] dx = {1,1,1,0,0,0,-1,-1,-1};
	static int[] dy = {-1,0,1,-1,0,1,-1,0,1};
	static Queue<int[]> mad; 
	static char[][] map;
	static int[][] robots;
	static boolean flag = true;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		mad = new LinkedList<>();
		robots = new int[N][M];
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'I') {
					X = i;
					Y = j;
				}
				else if(map[i][j] == 'R') {
					mad.add(new int[] {i,j});
					robots[i][j] = 1;
				}
			}
		}
		
		String line = br.readLine();
		char[] temp = line.toCharArray();
		dir = new int[temp.length];
		for(int i=0;i<temp.length;i++) {
			dir[i] = (int)(temp[i]-'0')-1;
		}
		simulate();
		if(flag) {
			for(int j=0;j<N;j++) {
				for(int k=0;k<M;k++) {
					System.out.print(map[j][k]);
				}
				System.out.println();
			}
		}
	}
	public static void simulate() {
		for(int i=0;i<dir.length;i++) {
			// 종수의 이동
			map[X][Y] = '.';
			X += dx[dir[i]];
			Y += dy[dir[i]];
			if (map[X][Y] == 'R') {
                System.out.println("kraj " + (i + 1));
                flag = false;
                return;
            }
			map[X][Y] = 'I';
			move();
			if(!flag) {
				System.out.println("kraj "+(i+1));
				return;
			}
			combine();
		}
	}
	public static void move() {
        int[][] tempRobots = new int[N][M];  // 임시로 로봇 이동 결과 저장
		if(!mad.isEmpty()) {
			int size = mad.size();
			for(int i=0;i<size;i++) {
				int[] cur = mad.poll();
				int min = Integer.MAX_VALUE;
				int minDir = 0;
				for(int d=0;d<9;d++) {
					int nx = cur[0] + dx[d];
					int ny = cur[1] + dy[d];
					if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                        int temp = calDist(nx, ny);
                        if (min > temp) {
                            min = temp;
                            minDir = d;
                        }
                    }
				} 
				map[cur[0]][cur[1]] = '.';
				int nx = cur[0] + dx[minDir];
				int ny = cur[1] + dy[minDir];
				if(map[nx][ny] == 'I') {
					flag = false;
					return;
				}
				tempRobots[nx][ny]++;
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					robots[i][j] = tempRobots[i][j];
				}
			}
		}
	}
	public static int calDist(int nx,int ny) {
		return Math.abs(X-nx) + Math.abs(Y-ny);
	}
	public static void combine() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(robots[i][j] >1) {
					robots[i][j] = 0;
					map[i][j] = '.';
				}
				else if(robots[i][j] == 1) {
					mad.add(new int[] {i,j});
					map[i][j] = 'R';
				}
			}
		}
	}
}
