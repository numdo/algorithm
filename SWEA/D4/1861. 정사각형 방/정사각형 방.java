
import java.io.*;
import java.util.*;

public class Solution {

	static int N,arr[][],max,cnt;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("./res/SWEA1861_input.txt"));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			max = -1;
			int start = 0;
			arr = new int[N][N];
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					cnt = 1;
					findRoom(i,j,arr[i][j]);
					if(cnt > max) {
						max = cnt;
						start = arr[i][j];
					}
					else if (cnt == max) {
						start = Math.min(start, arr[i][j]);
					}
				}
			}
				
			
			System.out.println("#" + tc + " " + start + " " + max);
		}
	}
	// 무조건 하나씩 있으니까 넘버가 있고 시작 넘버 부터 사방탐색을 하는데
	// 방향이 고정이고 ex 5까지 탐색을 했으면 5보다 작은 숫자들은 탐색을 시작 안해도 됨
	// 1부터 N*N까지 2차원 배열에 저장하고 1부터 시작해서 가는데 4방 탐색을 하고 다음수가 일치할때만 들어감
	// 
	public static void findRoom(int x,int y,int val) {			
		for(int d=0;d<4;d++) {
			int cx = x + dx[d];
			int cy = y + dy[d];
			if(cx >=0 && cx <N && cy >=0 && cy < N && arr[cx][cy] -1 == val) {
				cnt++;
				findRoom(cx,cy,arr[cx][cy]);
			}
		}
	}
	

}
