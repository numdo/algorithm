
import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int L;
	static int[] food;
	static int[] cal;
	static int result;
	static boolean[] visited;
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		//BufferedReader br = new BufferedReader(new FileReader("./res/SWEA5215_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			food = new int[N];
			cal = new int[N];
			result = 0;
			visited = new boolean[N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine()," ");
				food[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}
			comb(0,0,0);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	public static void comb(int depth,int taste,int score) {
		if(score >= L) {
			return;
		}
		else {
			result = Math.max(result,taste);
		}
		for(int i=depth;i<N;i++) {
			if(!visited[i]) {
				visited[i] = true;
				comb(i+1,taste + food[i],score+cal[i]);
				visited[i] = false;
			}
			
		}
	}

}
