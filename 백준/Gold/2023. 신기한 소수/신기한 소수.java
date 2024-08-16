
import java.io.*;
import java.util.*;
public class Main {
	static int N;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		// 첫번째 인자가 소수인 수만 구함
		findPrime(2,1);
		findPrime(3,1);
		findPrime(5,1);
		findPrime(7,1);
		System.out.print(sb);
		
	}
	// 첫번째 소수와 깊이 값을 인지값으로 받음
	public static void findPrime(int num,int depth) {
		// N자리 수가 되면 재귀 탎출
		if(depth ==N) {
			sb.append(num).append("\n");
			return;
		}
		// 다음 자리수에는 짝수가 올수 없기때문에 홀수만 더해줌
		for(int i=1;i<=9;i+=2) {
			int nextNum = 10*num + i;
			if(checkPrime(nextNum)) {
				findPrime(nextNum,depth+1);
			}
		}
	}
	// 제곱근까지의 수까지만 소수를 구해도 소수를 판별 할 수 있음
	public static boolean checkPrime(int num) {
		for(int i=2;i<=Math.sqrt(num);i++) {
			if(num%i == 0) return false;
		}
		return true;
	}
	

}
