
import java.io.*;
import java.util.*;

public class Solution {
	static int[][] arr;
	static List<Integer> list;
	static int N,M;
	static int result;
	static int[] temp;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("./res/SWEA3421_input.txt"));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			arr = new int[M][2];
			result = (int)Math.pow(2, N);
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine()," ");
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			temp = new int[N];
			for(int i=0;i<N;i++) {
				temp[i] = i+1;
			}
			subset(0);
			System.out.println("#" + tc+" " +result);
		}
	}
	// 0 1 2랑
	// 1 2 랑 같다고 판단해서 두개를 뺀다...
	private static void subset(int index) {
		if(index == N) {
			for(int i=0;i<arr.length;i++) {
				if(list.contains(arr[i][0]) && list.contains(arr[i][1])) {					
					result--;
					break;
				}
			}
			return;
		}
		subset(index+1);
		// 공
		list.add(temp[index]);
		subset(index+1);
		list.remove(list.size()-1);

	}

}
