
import java.io.*;
import java.util.*;
public class Main {
	static int V,E,result = 0;
	static ArrayList<Integer>[] graph;
	static boolean visited[];
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		graph = new ArrayList[V+1];
		visited = new boolean[V+1];
		for(int i=1;i<=V;i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i=0;i<E;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		visited[1] = true;
		dfs(1);
		System.out.println(result);
	}
	private static void dfs(int val) {
		
		for(int it : graph[val]) {
			if(visited[it]) continue;
			visited[it] = true;
			result++;
			dfs(it);
		}
		
	}
	
}
