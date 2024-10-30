import java.io.*;
import java.util.*;

public class Main {
	static int N,K,L;
	static int[][] map;
	static Queue<Bam> q;
	static int[] dx = {0,1,0,-1};	// 우 하 상 좌
	static int[] dy = {1,0,-1,0};
	static int time = 0;
	static int bamX=0,bamY=0,bamDir = 0;
	static List<int[]> bamLen;
	static class Bam implements Comparable<Bam>{
		int time;
		String dir;
		public Bam(int time, String dir) {
			this.time = time;
			this.dir = dir;
		}
		@Override
		public int compareTo(Bam o) {
			// TODO Auto-generated method stub
			return this.time - o.time;
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		L = Integer.parseInt(br.readLine());
		map = new int[N][N];
		q = new LinkedList<>();
		bamLen = new ArrayList<>();
		for(int i=0;i<L;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			map[x][y] = -1;
		}
		K = Integer.parseInt(br.readLine());
		for(int i=0;i<K;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			String d = st.nextToken();
			q.add(new Bam(time,d));
		}
		map[0][0] = 1;
		bamLen.add(new int[] {0,0});
		dummy();
		System.out.println(time);
	}
	public static void dummy() {
		while(true) {
			time++;
			int nx = bamX + dx[bamDir];
			int ny = bamY + dy[bamDir];
			
			if(!isIn(nx,ny) || map[nx][ny] == 1) {
				return;
			}
			bamLen.add(new int[] {nx,ny});
			if(map[nx][ny] == -1) {
				map[nx][ny] = 1;
			} else {
				map[nx][ny] = 1;
				int[] tail = bamLen.remove(0);
				map[tail[0]][tail[1]] = 0;
			}
			bamX = nx;
			bamY = ny;
			if(!q.isEmpty() && q.peek().time == time) {
				Bam temp = q.poll();
				if(temp.dir.equals("D")) {
					bamDir = (bamDir+1)%4;
				} else {
					bamDir = (bamDir-1) < 0 ? 3 : (bamDir-1);
				}
			}
		}
	}
	public static boolean isIn(int x,int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}

}
