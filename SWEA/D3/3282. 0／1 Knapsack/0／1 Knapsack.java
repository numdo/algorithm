import java.io.*;
import java.util.*;

public class Solution {
	static int N, K;
	static int[][] dp;
	static int[] weight, cost;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			dp = new int[N + 1][K + 1];
			weight = new int[N + 1];
			cost = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				weight[i] = Integer.parseInt(st.nextToken());
				cost[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= K; j++) {
					if (weight[i] > j) {
						dp[i][j] = dp[i - 1][j];
					} else {
						dp[i][j] = Math.max(dp[i - 1][j - weight[i]] + cost[i], dp[i - 1][j]);
					}
				}
			}
			System.out.println("#" + tc + " " + dp[N][K]);
		}
	}

}
