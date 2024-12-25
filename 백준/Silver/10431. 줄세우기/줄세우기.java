import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int P = Integer.parseInt(br.readLine());
		int[] arr = new int[20];
		StringBuilder sb = new StringBuilder();
		for(int t=0;t<P;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for(int i=0;i<20;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int answer = 0;
			for(int i=1;i<20;i++) {
				int prev = arr[i];
				for(int j=0;j<i;j++) {
					if(prev < arr[j]) {
						answer++;
//						arr[i] = arr[j];
//						arr[j] = prev;
					}
				}
			}
			sb.append(num).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}
}
