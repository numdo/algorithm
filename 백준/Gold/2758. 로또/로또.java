import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수
        
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());  // 고를 숫자의 개수
            int M = Integer.parseInt(st.nextToken());  // 숫자의 범위
            
            long[][] dp = new long[M+1][N+1];
            
            // 초기 상태: dp[i][1] = 1 (i 숫자 하나를 고르는 경우)
            for (int i = 1; i <= M; i++) {
                dp[i][1] = 1;
            }
            
            // DP 테이블 채우기
            for (int j = 2; j <= N; j++) {  // j는 고른 숫자의 개수
                for (int i = 2; i <= M; i++) {  // i는 마지막으로 고른 숫자
                    for (int k = i / 2; k >= 1; k--) {  // k는 i의 절반 이하 숫자들
                        dp[i][j] += dp[k][j-1];
                    }
                }
            }
            
            // 결과 계산
            long result = 0;
            for (int i = 1; i <= M; i++) {
                result += dp[i][N];  // N개의 숫자를 고르는 모든 경우의 수
            }
            
            System.out.println(result);  // 결과 출력
        }
    }
}
