import java.io.*;
import java.util.*;

public class Main {
	static int N,M;
	static int[] arr;
	static boolean[] checked;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		checked = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			
		}
		Arrays.sort(arr);
		comb(0,new int[M]);
		
		System.out.print(sb);
	}

	public static void comb(int depth,int[] temp) {
		if(depth == M) {
			for(int i=0;i<M;i++) {
				sb.append(temp[i]).append(" ");
			}
			sb.append("\n");			
			return;
		}
		int prev = -1;
		for(int i=0;i<N;i++) {
			if(checked[i] || arr[i] == prev) continue;
			checked[i] = true;
			temp[depth] = arr[i];
			comb(depth+1,temp);
			checked[i] = false;
			prev = arr[i];
		}
	}
}
