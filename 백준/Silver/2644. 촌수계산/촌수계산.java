import java.io.*;
import java.util.*;

public class Main {
	static int V,E,start,end,result=-1;
	static boolean[] visited;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(br.readLine());
		List<Integer>[] graph = new ArrayList[V+1];
		
		for(int i=1;i<=V;i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			graph[s].add(e);
			graph[e].add(s);
		}
		visited = new boolean[V+1];
		dfs(start,graph,0);
		System.out.println(result);
	}
	public static void dfs(int index,List<Integer>[] graph,int count) {
		if(index==end) {
			result = count;
			return;
		}
		for(int it:graph[index]) {
			if(visited[it])continue;
			visited[it] = true;
			dfs(it,graph,count+1);
			visited[it] = false;
		}
	}
}
