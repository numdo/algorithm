import java.io.*;
import java.util.*;

public class Solution {
	static int K,arr[];
	static boolean[] visited;
	static int max;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char[] line = st.nextToken().toCharArray();
			arr = new int[line.length];
			int N = line.length;
			visited = new boolean[N];
			max = 0;
			for(int i=0;i<N;i++) {
				arr[i] = line[i] - '0';
			}
			K = Integer.parseInt(st.nextToken());
			dfs(0,0,N);
			System.out.println("#" + tc + " " + max);
		}
	}
	
	private static void dfs(int start, int depth, int N) {
    if (depth == K) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum = sum * 10 + arr[i];
        }
        max = Math.max(max, sum);
        return;
    }

    recursiveSwap(start, start + 1, depth, N);
}

private static void recursiveSwap(int i, int j, int depth, int N) {
    if (i >= N - 1) {
        return; // base case: 종료 조건, i가 마지막 인덱스에 도달하면 종료
    }

    if (j >= N) {
        // j가 N에 도달하면 다음 i로 이동
        recursiveSwap(i + 1, i + 2, depth, N);
        return;
    }

    // 스왑하고 재귀 호출
    swap(i, j);
    dfs(i, depth + 1, N);
    swap(i, j); // 원상 복구

    // 다음 j로 재귀적으로 호출
    recursiveSwap(i, j + 1, depth, N);
}

	
	private static void swap(int a,int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
