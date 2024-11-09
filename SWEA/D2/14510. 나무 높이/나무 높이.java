import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int maxHeight = Integer.MIN_VALUE;
            int[] treeHeights = new int[N];

            // 최대 높이 찾기 및 초기 나무 높이 저장
            for (int i = 0; i < N; i++) {
                int height = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, height);
                treeHeights[i] = height;
            }
            int odd = 0;
            int even = 0;
            for(int i=0;i<N;i++) {
            	int diff = maxHeight - treeHeights[i];
            	odd+= diff%2;
            	even+=diff/2;
            }

            int answer = 0;
            boolean flag = true;
            while(even>odd) {
            	even--;
            	odd+=2;
            	flag = false;
            }
            if(flag && odd-even>1) {
            	answer+= odd-even-1;
            }
            answer += odd+even;
            System.out.println("#" + tc + " " + answer);
        }
    }
}
