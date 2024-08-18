import java.io.*;
import java.util.*;
public class Main {
	static int N;
	static int M;
	static StringBuilder sb = new StringBuilder();
	static int[] arr;
	static int[] output;
	static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		output = new int[M];
		for(int i=0;i<N;i++) {
			arr[i] = i+1;
		} 
		perm(0);
		System.out.print(sb);
	}
	public static void perm(int depth) {
		if(depth == M) {
			for(int it : output) {
				sb.append(it).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=0;i<N;i++) {
//			if(selected[i]) continue;
			if(depth > 0 && output[depth-1] > arr[i]) {
				continue;
			}
			output[depth] = arr[i];
			perm(depth+1);
//			selected[i] = true;
		}
	}

}

