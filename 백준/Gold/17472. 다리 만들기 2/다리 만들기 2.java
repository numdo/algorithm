import java.io.*;
import java.util.*;

public class Main {
	static int N,M,map[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static List<Edge> bridges;
	static boolean[][] visited;
	static int[] parents;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int color = 1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					makeIsland(i,j,color++);
				}
			}
		}
		bridges = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] >0) {
					for(int dir = 0;dir<4;dir++) {
						makeBridge(i, j, map[i][j],dir);
					}
				}
			}
		}
		Collections.sort(bridges);
//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		make(color);
		int cost = 0;
		int count = 0;
		for(Edge edge:bridges) {
			if(union(edge.start,edge.end)) {
				cost += edge.weight;
				if(++count == color-2) break;
			}
		}
		if(count != color - 2) {
			System.out.println(-1);
		}
		else {
			System.out.println(cost);
		}
	}
	public static void makeIsland(int x,int y,int color) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		map[x][y] = color;
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int i=0;i<4;i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(isMap(nx,ny) && !visited[nx][ny]) {
					visited[nx][ny] = true;
					if(map[nx][ny] == 1) {
						map[nx][ny] = color;
						q.add(new int[] {nx,ny});
					}
				}
			}
		}
	}
	
	public static void makeBridge(int x,int y,int color,int dir) {
		int distance = 0;
		while(true) {
			x+= dx[dir];
			y+= dy[dir];
			if(!isMap(x,y)) {
				return;
			}
			if(map[x][y] == color) {
				return;
			}
			else if(map[x][y] > 0) {
				if(distance>1) {
					bridges.add(new Edge(color,map[x][y],distance));	
				}				
				return;
			}
			distance++;
		}

	}
	

	
	public static boolean isMap(int x,int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
	
	public static void make(int color) {
		parents = new int[color];
		for(int i=1;i<color;i++) {
			parents[i] = -1; 
		}
	}
	public static int find(int a) {
		if(parents[a] <0) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	public static boolean union(int a,int b) {
		int ar = find(a);
		int br = find(b);
		
		if(ar == br) {
			return false;
		}
		parents[ar] += parents[br];
		parents[br] = ar;
		return true;
	}
	
	static class Edge implements Comparable<Edge>{
		int start,end,weight;

		public Edge(int start,int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}

		@Override
		public String toString() {
			return "Edge [end=" + end + ", weight=" + weight + "]";
		}
		
	}
}
