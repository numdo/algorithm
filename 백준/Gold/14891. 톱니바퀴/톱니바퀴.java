import java.io.*;
import java.util.*;

public class Main {
	static final int N = 8;
	static LinkedList<Integer>[] qList;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// Queue 배열을 만들고 K번 만큼 돌리고
		// 번호에 해당하는 톱니 바퀴 돌리고 각 번호별로 돌려줘야하는 톱니 바퀴 돌리기
		qList = new LinkedList[5];
		for (int i = 1; i <= 4; i++) {
			qList[i] = new LinkedList<>();
		}
		for (int i = 1; i <= 4; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				qList[i].add(line.charAt(j) - '0');
			}
		}
		int K = Integer.parseInt(br.readLine());
		Queue<int[]> order = new LinkedList<>();
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			order.add(new int[] { x, y });
		}
		while (!order.isEmpty()) {
			int[] temp = order.poll();
			int num = temp[0];
			int dir = temp[1]; // 1은 시계방향, -1은 반시계 방향
			// 1번은 2번과

			int[] change = move(num, dir);
			for (int i = 1; i <= 4; i++) {
				if (change[i] < 0) {
					int size = change[i] * -1;
					for (int j = 0; j < size; j++) {
						int first = qList[i].removeFirst();
						qList[i].addLast(first);
					}
				} else {
					int size = change[i];
					for (int j = 0; j < size; j++) {
						int last = qList[i].removeLast();
						qList[i].addFirst(last);
					}
				}
			}
		}
		int sum = 0;
		for (int i = 1; i <= 4; i++) {
//			for(int j=0;j<8;j++) {
//				System.out.print(qList[i].get(j));
//			}
//			System.out.println();
			if (qList[i].getFirst() == 1) {
				sum += Math.pow(2, i - 1);
			}
		}
		System.out.println(sum);
	}

	public static int[] move(int num, int dir) {
	    int[] change = new int[5];
	    int tempDir = dir;
	    change[num] = dir;  // 현재 톱니바퀴 회전 설정

	    // 오른쪽 톱니바퀴들과의 연쇄작용 확인
	    for (int i = num; i < 4; i++) {
	        if (qList[i].get(2) != qList[i + 1].get(6)) {
	            tempDir *= -1;
	            change[i + 1] = tempDir;
	        } else {
	            break;  // 연쇄작용이 없으면 종료
	        }
	    }

	    // 왼쪽 톱니바퀴들과의 연쇄작용 확인
	    tempDir = dir;
	    for (int i = num; i > 1; i--) {
	        if (qList[i].get(6) != qList[i - 1].get(2)) {
	            tempDir *= -1;
	            change[i - 1] = tempDir;
	        } else {
	            break;  // 연쇄작용이 없으면 종료
	        }
	    }

	    return change;
	}

}
