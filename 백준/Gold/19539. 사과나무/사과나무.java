
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int total = 0;
		int one = 0;
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			total += arr[i];
			one += arr[i]%2; //높이를 2로 나눴을 때 나머지가 1이면 oneCount 증가
		}
		if(total %3 ==0 && one <=total/3) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
		
	}

}
