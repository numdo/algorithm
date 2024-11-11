import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, X;
    static List<int[]> conditions;
    static int[] cage;
    static int[] bestAssignment;
    static int maxSum;
    static boolean found;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            conditions = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken()) - 1; 
                int r = Integer.parseInt(st.nextToken()) - 1; 
                int s = Integer.parseInt(st.nextToken());
                conditions.add(new int[]{l, r, s});
            }

            cage = new int[N];
            bestAssignment = new int[N];
            maxSum = -1;
            found = false;

            backtrack(0, 0);

            sb.append("#").append(tc).append(" ");
            if (found) {
                for (int i = 0; i < N; i++) {
                    sb.append(bestAssignment[i]).append(" ");
                }
            } else {
                sb.append(-1);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void backtrack(int idx, int currentSum) {
        if (idx == N) {
            if (validate()) {
                if (!found || currentSum > maxSum ) {
                    found = true;
                    maxSum = currentSum;
                    System.arraycopy(cage, 0, bestAssignment, 0, N);
                }
            }
            return;
        }

        for (int i = 0; i <= X; i++) {
            cage[idx] = i;
            if (isValidPartial(idx)) {
                backtrack(idx + 1, currentSum + i);
            }
        }
    }

    static boolean isValidPartial(int currentIdx) {
        for (int[] cond : conditions) {
            int l = cond[0];
            int r = cond[1];
            int s = cond[2];

            if (currentIdx < l) {
                continue;
            }

            if (currentIdx > r) {
                // 조건 범위가 이미 완료됨, 합이 맞는지 확인
                int sum = 0;
                for (int i = l; i <= r; i++) {
                    sum += cage[i];
                }
                if (sum != s) {
                    return false;
                }
            } else {
                // 조건 범위가 진행 중임, 현재까지의 합과 남은 케이지로 가능한 최대 합을 확인
                int sum = 0;
                int assigned = 0;
                for (int i = l; i <= currentIdx; i++) {
                    sum += cage[i];
                    assigned++;
                }
                int remaining = (r - l + 1) - assigned;
                if (sum > s) {
                    return false;
                }
                if (sum + remaining * X < s) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean validate() {
        for (int[] cond : conditions) {
            int l = cond[0];
            int r = cond[1];
            int s = cond[2];
            int sum = 0;
            for (int i = l; i <= r; i++) {
                sum += cage[i];
                if (cage[i] > X) {
                    return false;
                }
            }
            if (sum != s) {
                return false;
            }
        }
        return true;
    }

//    static boolean isLexSmaller(int[] current, int[] best) {
//        for (int i = 0; i < N; i++) {
//            if (current[i] < best[i]) {
//                return true;
//            } else if (current[i] > best[i]) {
//                return false;
//            }
//        }
//        return false;
//    }
}
