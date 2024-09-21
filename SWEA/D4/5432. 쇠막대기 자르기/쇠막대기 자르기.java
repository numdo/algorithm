import java.io.*;
import java.util.*;

public class Solution {
	static char[] arr;
	static List<Integer> open;
	static int result;
	static int count;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1;tc<=T;tc++) {
			arr = br.readLine().toCharArray();
			open = new ArrayList<>();
			result = 0;
			count = 0;
			for(int i=0;i<arr.length;i++) {
				if(arr[i] == '(') {
					open.add(i);
					count++;
				}
				else {
					int start = open.remove(open.size()-1);
					if(i-start == 1) {
						count--;
						result +=count;
					}
					else {
						result++;
						count--;
					}
				}
//				System.out.print("i :" + i);
//				System.out.println(" result : " + result);
			}
			System.out.println("#" + tc + " " + result);
		}
		
	}

}
