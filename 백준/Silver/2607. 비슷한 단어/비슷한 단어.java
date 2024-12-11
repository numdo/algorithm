import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String standard = br.readLine();
		int[] arr = new int[26];
		int answer = 0;
		for(int i=0;i<standard.length();i++) {
			arr[standard.charAt(i)-'A']++;
		}
		for(int i=1;i<N;i++) {
			String line = br.readLine();
			int[] cpyArr = Arrays.copyOf(arr, 26);
			for(int j=0;j<line.length();j++) {
				cpyArr[line.charAt(j) - 'A']--;
			}
			int plus = 0;
			int minus = 0;
			for(int j=0;j<26;j++) {
				if(cpyArr[j] > 0) {
					plus+=cpyArr[j];
				} else if(cpyArr[j] <0) {
					minus-=cpyArr[j];
				}
			}
//			System.out.println(line + " plus : " + plus);
//			System.out.println(line + " minus : " + minus);
			if(plus < 2 && minus < 2) {
				answer++;
			}
		}
		System.out.println(answer);
	}

}
