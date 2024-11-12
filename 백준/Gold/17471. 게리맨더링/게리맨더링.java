import java.io.*;
import java.util.*;

public class Main {
	static int N,answer = Integer.MAX_VALUE;
	static int[] score,area;
	static boolean[] visited;
	static ArrayList<Integer>[] graph;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		score = new int[N+1];
		for(int i=1;i<=N;i++) {
			score[i] = Integer.parseInt(st.nextToken());
		}
		
		graph = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for(int j=0;j<size;j++) {
				int to = Integer.parseInt(st.nextToken());
				graph[i].add(to);
//				graph[to].add(i);
			}
		}
		
		area = new int[N+1];
		subset(1);
		if(answer == Integer.MAX_VALUE) System.out.println("-1");
        else System.out.println(answer);
		
	}

	public static void subset(int depth) {
		
		if(depth == N+1) {
			int area1 = 0;
			int area2 = 0;
			for(int i=1;i<=N;i++) {
				if(area[i] == 1) {
					area1+=score[i];
				}
				else {
					area2+=score[i];
				}
			}
			// 연결 되어있는지 확인
			int link = 0;
			visited = new boolean[N+1];
			for(int i=1;i<=N;i++) {
				if(visited[i]) continue;
				dfs(i,area[i]);
				link++;
			}
			if(link == 2) {
				answer = Math.min(Math.abs(area2 - area1),answer);
			}
			
			return;
		}
		area[depth] = 1;
		subset(depth+1);
		
		area[depth] = 2;
		subset(depth+1);
	}
	
	public static void dfs(int depth,int color) {
		
		for(int i:graph[depth]) {
			if(!visited[i] && area[i] == color) {
				visited[i] = true;
				dfs(i,area[i]);
			}
			
		}
	}
}
