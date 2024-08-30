import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int sum = 0; // 높이의 총합
        int twos = 0; // 필요한 2의 개수
        
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(st.nextToken());
            sum += h;
            twos += h / 2; // 각 나무의 높이 h를 2로 나누어 나올 수 있는 2의 개수
        }

        // 조건 1: 높이의 총합이 3의 배수인가?
        // 조건 2: twos가 sum / 3 보다 크거나 같은가?
        if (sum % 3 == 0 && twos >= sum / 3) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
