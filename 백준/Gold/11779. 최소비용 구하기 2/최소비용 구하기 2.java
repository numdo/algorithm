import java.io.*;
import java.util.*;

public class Main {
	static int V,E;
	static List<int[]>[] graph;
	static int[] result;
	static int[] path;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		graph = new ArrayList[V+1];
		result = new int[V+1];
		path = new int[V+1];
		for(int i=1;i<=V;i++) {
			graph[i] = new ArrayList<>();
			result[i] = Integer.MAX_VALUE;
			path[i] = -1;
		}
		
		for(int i=0;i<E;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			graph[start].add(new int[] {end,val});
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		dijkstra(start);
		System.out.println(result[end]);
		List<Integer> list = new ArrayList<>();
		int index = end;
		while(index != start) {
			list.add(index);
			index = path[index];
		}
		list.add(start);
		Collections.reverse(list);
		System.out.println(list.size());
		for(int it: list) {
			System.out.print(it + " ");
		}
	}
	public static void dijkstra(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> (o1[1] - o2[1]));
		pq.add(new int[] {start,0});
		result[start] = 0;
		path[start] = start;
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if(cur[1] > result[cur[0]]) {
				continue;
			}
			for(int[] next : graph[cur[0]]) {
				int cost = cur[1] + next[1];
				if(cost < result[next[0]]) {
					result[next[0]] = cost;
					path[next[0]] = cur[0];
					pq.add(new int[] { next[0],cost});
				}
			}
		}
		
	}

}
