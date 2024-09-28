import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] arr;
	static boolean[] visited;
	static int result = 0;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][9];
		visited = new boolean[9];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<9;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		makeOrder(0,new int[9]);
		System.out.println(result);
	}
	public static void makeOrder(int depth, int[] batter) {
		if(depth==9) {
//			for(int it:batter) {
//				System.out.print(it + " ");
//			}
//			System.out.println();
			result = Integer.max(result,simulate(batter));
		}
		if(depth == 3) {
			batter[3] = 0;
			makeOrder(depth+1,batter);
		}
		for(int i=1;i<9;i++) {
			if(visited[i]) continue;
			visited[i] = true;
			batter[depth] = i;
			makeOrder(depth+1,batter);
			visited[i] = false;
		}
		
	}
	public static int simulate(int[] batter) {
		int score = 0;
		int order = 0;
		for(int j=0;j<N;j++) {
			int out = 0;
			int[] base = new int[4];  // 1, 2, 3루를 나타냄 (베이스 상태)
            Arrays.fill(base, 0);
			while(out <3) {
				if(order==9)order=0;
				int tempScore = arr[j][batter[order]];
				if(tempScore == 0) {
					out++;
				}else {
					score += advanceRunners(base, tempScore);
				}
				order++;
			}
		}
		return score;
	}
	public static int advanceRunners(int[] base, int hit) {
        int score = 0;

        // 주자들 진루 처리
        for (int i = 3; i >= 0; i--) {
            if (i == 3) {
                // 홈에 있는 주자 처리 (득점 여부)
                score += base[i];
            } else {
                // 주자들을 hit만큼 진루시킴
                if (i + hit >= 4) {
                    score += base[i];  // 주자가 홈으로 들어오면 점수 추가
                } else {
                    base[i + hit] = base[i];  // 주자를 진루시킴
                }
            }
            base[i] = 0;  // 기존 위치 초기화
        }

        // 타자도 주자로 진루
        if (hit >= 4) {
            score++;  // 홈런일 경우 타자가 바로 점수
        } else {
            base[hit] = 1;  // 타자가 hit만큼 진루
        }

        return score;
    }
}
