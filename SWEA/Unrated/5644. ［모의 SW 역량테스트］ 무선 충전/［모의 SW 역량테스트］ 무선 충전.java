
import java.io.*;
import java.util.*;

public class Solution {

	static int N, M;
	static int[] A, B;
	static List<BC> list;
				// 안움직임 | 상 | 우 | 하 | 좌
	static int[] dx = { 0, 0, 1, 0, -1 };	
	static int[] dy = { 0, -1, 0, 1, 0 };	

	static class BC {
		int x, y, area, power;
		
		BC(int x, int y, int area, int power) {
			this.x = x;	// 행의 좌표
			this.y = y;	// 열의 좌표
			this.area = area;
			this.power = power;
		}
        boolean isInRange(int cx, int cy) {
            return Math.abs(this.x - cx) + Math.abs(this.y - cy) <= this.area;
        }
	}

	static class User{
		int x,y;
		User(int x,int y){
			this.x = x;
			this.y = y;
		}
		void set(int x,int y) {
			this.x += x;
			this.y += y;
		}
		
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("./res/SWEA5644_input.txt"));
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int sum = 0;
			A = new int[N];
			B = new int[N];
			list = new ArrayList<>();
			int[] scoreA = new int[N];
			int[] scoreB = new int[N];
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			// 리스트에 배터리 충전소에 대한 정보 저장
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int area = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				list.add(new BC(x, y, area, power));
			}
			// d = |xa-xb| + |ya-yb|
								// 행 열
			User userA = new User(1,1);
			User userB = new User(10,10);
			for(int i = 0;i<=N;i++) {
				// 유저 이동
				if(i>0) {
					userA.set(dx[A[i-1]], dy[A[i-1]]);
					userB.set(dx[B[i-1]], dy[B[i-1]]);	
				}
				 int maxCharge = 0;

                // 가능한 최대 충전량 계산
                for (int j = 0; j < M; j++) {
                    for (int k = 0; k < M; k++) {
                        int chargeA = 0, chargeB = 0;

                        if (list.get(j).isInRange(userA.x, userA.y)) {
                            chargeA = list.get(j).power;
                        }

                        if (list.get(k).isInRange(userB.x, userB.y)) {
                            chargeB = list.get(k).power;
                        }

                        // 같은 BC를 사용하는 경우
                        if (j == k && chargeA > 0 && chargeB > 0) {
                            maxCharge = Math.max(maxCharge, chargeA / 2 + chargeB / 2);
                        } else {
                            maxCharge = Math.max(maxCharge, chargeA + chargeB);
                        }
                    }
                }
                sum += maxCharge;
			}
			
			System.out.println("#" + tc + " " + sum);
		}
	}
}
