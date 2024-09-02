import java.io.*;
import java.util.*;
public class Solution {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] arr1 = new int[N];
			int[] arr2 = new int[M];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				arr1[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<M;i++) {
				arr2[i] = Integer.parseInt(st.nextToken());
			}
			int[] small;	// 작은거 택
			int[] big;	// 작은거 택
			if(N<M) {
				small = arr1;
				big = arr2;
			}
			else {
				small = arr2;
				big = arr1;
			}
			int max = Integer.MIN_VALUE;
			for(int i=0;i<=big.length - small.length;i++) {
				int sum = 0;
				for(int j=0;j<small.length;j++) {
					sum += small[j] * big[i+j];
				}
				max = Math.max(max, sum);
			}
			System.out.println("#" + tc + " " + max);
			
		}
	}

}
