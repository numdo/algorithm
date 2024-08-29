
import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		long result = 0;
		for(int i=0;i<N;i++) {
			int temp = arr[i];
			int cnt = 1;
			temp -= b;
			if(temp >0) {
				if(temp%c != 0)
					cnt+= (temp/c) +1;
				else
					cnt+= temp/c;
			}
			result += cnt;
		}
		System.out.println(result);
	}

}
