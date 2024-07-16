

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;


public class Main {
    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try{
            String[] input = bf.readLine().trim().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            // m이 n의 절반 이상일 경우를 바꿔줌
            BigInteger top = BigInteger.ONE;;
            BigInteger bottom = BigInteger.ONE;;
            BigInteger result;
            if(m > n/2){
                m = n-m;
            }
            for(int i=1;i<=m;i++){
                top = top.multiply(BigInteger.valueOf(n - i + 1));
                bottom = bottom.multiply(BigInteger.valueOf(i));
            }
            result = top.divide(bottom);
            System.out.println(result);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
