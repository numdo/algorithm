
import java.io.*;
import java.util.*;
public class Main {
	static int N,M;
	static int[] indegrees;
	static ArrayList<Integer>[] graph;
	static LinkedList<Integer> ans;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		ans = new LinkedList<>();
		for(int i=1;i<=N;i++) {
			graph[i] = new ArrayList<>();
		}
		indegrees = new int[N+1];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			indegrees[to]++;
		}
		topoloySort();
		for(int i = 0;i<N;i++) {
			sb.append(ans.poll()).append(" ");
		}
		System.out.println(sb);
	}
	private static void topoloySort() {
		Queue<Integer> q = new LinkedList<>();
		for(int i=1;i<=N;i++) {
			if(indegrees[i] == 0) {
				q.add(i);
			}
		}
		while(!q.isEmpty()) {
			int temp = q.poll();
			ans.add(temp);
			for(int i : graph[temp]) {
				indegrees[i]--;
				if(indegrees[i] == 0) {
					q.add(i);
				}
			}
		}
	}
}
