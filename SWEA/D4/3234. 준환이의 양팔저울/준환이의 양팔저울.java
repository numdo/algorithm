
import java.io.*;
import java.util.*;

public class Solution {

	static int N;
	static int[] arr;
	static int[] temp;
	static int result;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("./res/SWEA3234_input.txt"));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			temp = new int[N];
			visited = new boolean[N];
			result = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				temp[i] = Integer.parseInt(st.nextToken());
			}
			
			perm(0);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	public static void perm(int depth) {
		if(depth == N) {
			subset(0,0,0);
			return;
		}
		for(int i=0;i<N;i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[depth] = temp[i];
				perm(depth+1);
				visited[i] = false;
			}
		}
	}
	
	public static void subset(int depth,int left,int right) {
		if(left < right) {
			return;
		}
		if(depth == N) {
			result++;	
			return;
		}
		subset(depth+1,left+arr[depth],right);
		subset(depth+1,left,right+arr[depth]);
		
		
	}

}
