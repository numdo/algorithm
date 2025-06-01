import java.io.*;
import java.util.*;
class Solution {
    /**
     * DP 한 번으로 공원 전체를 훑어
     *  -1(빈칸)만으로 이뤄진 가장 큰 정사각형 변 길이를 구한다.
     */
    public int solution(int[] mats, String[][] park) {

        int n = park.length;          // 행 길이
        int m = park[0].length;       // 열 길이

        // dp[i][j] = (i,j)가 우하단인 최대 정사각형 한 변 길이
        int[][] dp = new int[n][m];
        int maxLen = 0;               // park 전역에서 얻은 최대 변 길이

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // 사람이 없는 칸("-1")일 때만 값 계산
                if (park[i][j].equals("-1")) {

                    if (i == 0 || j == 0) {      // 첫 행·첫 열
                        dp[i][j] = 1;
                    } else {
                        // 위·왼쪽·좌상 값 중 최소 + 1
                        dp[i][j] = Math.min(
                                        Math.min(dp[i-1][j], dp[i][j-1]),
                                        dp[i-1][j-1]) + 1;
                    }
                    maxLen = Math.max(maxLen, dp[i][j]); // 전역 최대 갱신
                }
                // 사람이 앉아 있다면 dp[i][j]는 기본값 0
            }
        }

        // 지민이가 가진 돗자리 중 maxLen 이하에서 가장 큰 값 선택
        int answer = -1;
        for (int len : mats) {
            if (len <= maxLen) answer = Math.max(answer, len);
        }
        return answer;
    }
}