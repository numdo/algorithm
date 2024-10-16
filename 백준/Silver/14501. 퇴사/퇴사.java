import java.io.*;
import java.util.*;

public class Main {
	static int N,answer;
	static int[][] arr;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		answer = 0;
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		subset(0,0);
		System.out.println(answer);
	}

	public static void subset(int depth,int val) {
		
		if(depth>=N) {
			answer = Integer.max(answer,val);
			return;
		}
		subset(depth+1,val);
		
		if(depth+arr[depth][0] <=N) {
			subset(depth+arr[depth][0],val+arr[depth][1]);
		}
	}
}

