import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[] arr;
    static int result;
    static int[][] dp; // dp[mask][left] : 특정 mask에서 왼쪽 무게가 left일 때 가능한 경우의 수
    static int totalWeight; // 전체 무게의 합

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            result = 0;
            totalWeight = 0;
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                totalWeight += arr[i];
            }

            // DP 배열 초기화, mask는 2^N 크기, left는 최대 총 무게까지
            dp = new int[1 << N][totalWeight + 1];
            for (int i = 0; i < (1 << N); i++) {
                Arrays.fill(dp[i], -1); // 아직 계산되지 않은 상태는 -1로 설정
            }

            // 부분 집합 탐색 시작 (초기 상태는 mask=0, left=0, right=0)
            result = subset(0, 0, 0);
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }

    // DP와 비트마스킹을 이용한 부분집합 탐색
    public static int subset(int mask, int left, int right) {
        // 오른쪽 무게가 왼쪽 무게보다 커지면 탐색 종료
        if (right > left) {
            return 0;
        }

        // 모든 무게추를 선택했을 경우 (모든 비트가 1인 경우)
        if (mask == (1 << N) - 1) {
            return 1;
        }

        // 이미 계산된 적이 있는 경우 그 값을 반환
        if (dp[mask][left] != -1) {
            return dp[mask][left];
        }

        int res = 0;

        // 아직 사용되지 않은 무게추에 대해 탐색
        for (int i = 0; i < N; i++) {
            if ((mask & (1 << i)) == 0) {  // i번째 무게추가 아직 선택되지 않았다면
                // 1. 현재 무게추를 왼쪽에 추가하는 경우
                res += subset(mask | (1 << i), left + arr[i], right);
                
                // 2. 현재 무게추를 오른쪽에 추가하는 경우 (단, 오른쪽 무게가 왼쪽 무게보다 크지 않아야 함)
                if (left >= right + arr[i]) {
                    res += subset(mask | (1 << i), left, right + arr[i]);
                }
            }
        }

        // 현재 상태의 결과를 DP 테이블에 저장
        dp[mask][left] = res;
        return res;
    }
}
