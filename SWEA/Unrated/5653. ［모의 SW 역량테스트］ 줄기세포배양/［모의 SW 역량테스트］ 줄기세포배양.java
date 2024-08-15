import java.io.*;
import java.util.*;

public class Solution {
	static final int[] di = { -1, 1, 0, 0 };// 상하좌우
	static final int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		//String filePath = "SWEA\\res\\SWEA5653_input.txt";
		//BufferedReader br = new BufferedReader(new FileReader(filePath));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			boolean[][] v = new boolean[K + N + K][K + M + K];
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1[2], o2[2]));
			for (int i = K + 0; i < K + N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = K + 0; j < K + M; j++) {
					int L = Integer.parseInt(st.nextToken());
					if (L != 0) {
						v[i][j] = true; // 0,1,2,3
						pq.offer(new int[] { i, j, L, L + L });
					}
				}
			}
			for (int k = 1; k <= K; k++) {
				ArrayDeque<int[]> q = new ArrayDeque<>();
				while (!pq.isEmpty()) {
					int[] ij = pq.poll();
					ij[3]--;
					if (ij[2] > ij[3]) {// 번식
						for (int d = 0; d < 4; d++) {
							int ni = ij[0] + di[d];
							int nj = ij[1] + dj[d];
							if (!v[ni][nj]) {
								v[ni][nj] = true; // 0 ,1 ,2 ,3
								q.offer(new int[] { ni, nj, ij[2], ij[2] + ij[2] });
							}
						}
					}
					if (ij[3] != 0)
						q.offer(ij);
				}
				while (!q.isEmpty())
					pq.offer(q.poll());
			}
			sb.append("#").append(tc).append(" ").append(pq.size()).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}
}