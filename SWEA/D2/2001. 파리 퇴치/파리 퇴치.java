
import java.io.*;
import java.util.*;
public class Solution {
	static int N;
	static int M;
	public static void main(String[] args) throws IOException,FileNotFoundException {
		// TODO Auto-generated method stub
		//BufferedReader br = new BufferedReader(new FileReader("./res/SWEA2001_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int tc =1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int[][] arr = new int[N][N];
			int[][] sumArr = new int[N][N];
			int maxSum = 0;
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine()," ");
				int sum = 0;
				for(int j=0;j<N;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					// 누적합
					sum += arr[i][j];
					sumArr[i][j] = sum;
				}
			}
			for(int i=0;i<=N-M;i++) {
				for(int j=0;j<=N-M;j++) {
					int temp = 0;
					for(int k=i;k<M+i;k++) {
						if(j == 0) {
							temp +=sumArr[k][j+M-1];
						}
						else {
							temp += sumArr[k][j+M-1];
							temp -= sumArr[k][j-1];
						}
					}
					maxSum = Math.max(maxSum, temp);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(maxSum).append("\n");
		}
		System.out.print(sb);
		br.close();
	}

}
