import java.io.*;
import java.util.*;

public class Main {
	static int N,d,k,c;
	static int[] belt;
	static boolean[] checked;
	static int answer;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		belt = new int[N];
		checked = new boolean[d+1];

		for(int i=0;i<N;i++) {
			belt[i] = Integer.parseInt(br.readLine());
		}
		for(int i=0;i<N;i++) {
			dfs(i,0,0);
		}
		System.out.println(answer);
	}
	public static void dfs(int index,int depth,int count) {
		if(depth == k) {
			if(!checked[c]) {
				count++;
			}
			answer = Math.max(answer, count);
			return;
		}
		if(index >= N) {
			index = 0;
		}
		if (!checked[belt[index]]) {
            checked[belt[index]] = true;
            dfs(index + 1, depth + 1, count + 1);
            checked[belt[index]] = false; // 백트래킹을 위해 복구
        } else {
            dfs(index + 1, depth + 1, count); // 이미 먹은 초밥이면 카운트는 증가하지 않음
        }
	}
}
