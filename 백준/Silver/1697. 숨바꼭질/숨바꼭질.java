import java.io.*;
import java.util.*;

public class Main {
	static int N,K;
	static int[] time;
	static final int max = 100000;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		time = new int[max+1];
		System.out.println(find());
	}
	public static int find() {
		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		time[N] = 1;
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == K) {
				return time[cur] - 1;
			}
			int[] canGo = {cur-1,cur+1,cur*2};
			
			for(int i=0;i<3;i++) {
				int next = canGo[i];
				if(next >=0 && next<=max && time[next] == 0) {
					time[next] = time[cur] +1;
					q.add(next);
				}
			}
		}
		return -1;
	}
}
