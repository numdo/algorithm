import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // N 입력 및 배열 생성
        int N = Integer.parseInt(br.readLine());
        int[] standard = new int[N];
        
        // N개의 숫자 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            standard[i] = Integer.parseInt(st.nextToken());
        }
        
        // 배열 정렬
        Arrays.sort(standard);
        
        // M 입력 및 탐색 수행
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(st.nextToken());
            // Arrays.binarySearch를 사용하여 탐색
            if (Arrays.binarySearch(standard, x) >= 0) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }
        
        // 결과 출력
        System.out.print(sb);
    }
}
