
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			int n = Integer.parseInt(br.readLine().trim());
			String[] nLine = br.readLine().split(" ");
			int m = Integer.parseInt(br.readLine().trim());
			String[] mLine = br.readLine().split(" ");
			HashMap<Integer,Integer> a = new HashMap<>();
			HashMap<Integer,Integer> b = new HashMap<>();
			for(int i=0;i<n;i++){
				int num = Integer.parseInt(nLine[i]);
				for(int j=2;j*j<=num;j++){
					while(num % j == 0){
						a.put(j,a.getOrDefault(j, 0)+1);
						num /= j;
					}
				}
				if(num > 1){
					a.put(num, a.getOrDefault(num, 0)+1);
				}
			}
			for(int i=0;i<m;i++){
				int num = Integer.parseInt(mLine[i]);
				for(int j=2;j*j<=num;j++){
					// while문을 쓰는게 j번째 즉 j인자일떄 나눠질수 있는 수까지를 다 카운트
					while(num % j == 0){
						b.put(j,b.getOrDefault(j, 0)+1);
						num /= j;
					}
				}
				// num이 소
				if(num > 1){
					b.put(num, b.getOrDefault(num, 0)+1);
				}
			}
			BigInteger result = BigInteger.ONE;
			for(int key : a.keySet()){
				if (b.containsKey(key)) {
					int powA = a.get(key);
					int powB = b.get(key);
					int minVal = Integer.min(powA ,powB);
					result = result.multiply(BigInteger.valueOf(key).pow(minVal));
				}
			}
			String answer = result.toString();
			if(answer.length() >= 9){
				System.out.println(answer.substring(answer.length()-9));
			}
			else{
				System.out.println(answer);
			}
			// 결국 소인수 분해로 풀어야 하는군


		}catch(IOException e){

		}
	}

}
