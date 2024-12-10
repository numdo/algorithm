import java.io.*;
import java.util.*;

public class Main {
	static int N,K;
	static int answer = 0;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		bfs();
		System.out.println(answer);
	}
	public static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		int[] visited = new int[100001];
		Arrays.fill(visited, Integer.MAX_VALUE);
		visited[N] = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == K) {
				answer = visited[cur];
				return;
			}
			int[] arr = {cur-1,cur+1,cur*2};
			for(int i=0;i<3;i++) {
				int next = arr[i];
				if(next < 0 || next >100000) continue;
				if(i<2) {
					if(visited[cur] + 1 < visited[next]) {
						q.add(next);
						visited[next] = visited[cur] + 1;
					}
				} else {
					if(visited[cur] < visited[next]) {
						q.add(next);
						visited[next] = visited[cur];
					}
				}
			}
		}
	}
}
