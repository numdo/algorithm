import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			int[] dp = new int[N];
			int result = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			for(int i=0;i<N;i++) {
				dp[i] = 1;
				for(int j=0;j<i;j++) {
					if(arr[j] < arr[i] ) {
						dp[i] = Integer.max(dp[i],dp[j]+1);
					}
				}
				result = Integer.max(result,dp[i]);
			}
			System.out.println("#" + tc + " " + result);
		}
	}

}
