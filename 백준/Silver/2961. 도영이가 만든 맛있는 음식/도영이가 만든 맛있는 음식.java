
import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] arr;
	static List<Integer> list;
	static int minVal = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		list = new ArrayList<>();
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		subset(0,1,0);
		System.out.println(minVal);
	}
	private static void subset(int depth,int mult,int sum) {
		if(depth == N) {
			if(list.isEmpty()) return;
			minVal = Math.min(minVal,Math.abs(mult - sum));
			return;
		}
		
		subset(depth+1,mult,sum);
		int temp = Math.abs(mult*arr[depth][0]-(sum+arr[depth][1]));  
		list.add(temp);
		subset(depth+1,mult*arr[depth][0],sum+arr[depth][1]);
		list.remove(list.size()-1);
	}

}
