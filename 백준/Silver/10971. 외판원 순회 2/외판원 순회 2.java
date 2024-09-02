
import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] matrix;
	static int[] result;
	static int minVal = Integer.MAX_VALUE;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		matrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N];
		visited[0] = true;
		dfs(0, 1, 0);
		System.out.println(minVal);
	}

	private static void dfs(int cur, int count, int cost) {
		if (count == N) {
			if (matrix[cur][0] != 0) {
				minVal = Math.min(minVal, cost + matrix[cur][0]);
			}
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!visited[i] && matrix[cur][i] != 0) {
				visited[i] = true;
				dfs(i, count + 1, cost + matrix[cur][i]);
				visited[i] = false;
			}
		}
	}

}
