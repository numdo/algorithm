import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	private static class Person implements Comparable<Person> {
		int idx, arriveTime, waitTime, infoNum, fixNum;

		Person(int idx, int arriveTime) {
			this.idx = idx;
			this.arriveTime = arriveTime;
			this.waitTime = 0;
			this.infoNum = 0;
			this.fixNum = 0;
		}
		@Override
		public int compareTo(Person o) {
			int num = this.waitTime - o.waitTime;
			if (num == 0)
				num = this.infoNum - o.infoNum;
			return num;
		}
	}

	private static int N, M, K, A, B, infoTime[], fixTime[];
	private static Person[] infoList, fixList;
	private static Queue<Person> infoWait, tempWait, endPerson;
	private static PriorityQueue<Person> fixWait;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			infoWait = new LinkedList<>();
			tempWait = new LinkedList<>();
			endPerson = new LinkedList<>();
			fixWait = new PriorityQueue<>();
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			infoTime = new int[N];
			infoList = new Person[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				infoTime[i] = Integer.parseInt(st.nextToken());
			
			fixTime = new int[M];
			fixList = new Person[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++)
				fixTime[i] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++)	//고객번호는 1부터 시작 
				infoWait.add(new Person(i, Integer.parseInt(st.nextToken())));
			
			int time = 0;
			while (true) {
				//접수, 정비 창구(배열) 모두 비고, 대기줄까지 비면 계속 true로 유지됨 
				boolean endCondition = true;
				//접수창구 
				for (int i = 0; i < N; i++) {
					if (infoList[i] == null) {
						if (infoWait.isEmpty()) continue;
						Person temp = infoWait.peek();
						if (time >= temp.arriveTime) {
							temp = infoWait.poll();
							temp.waitTime = 1;
							temp.infoNum = i;
							infoList[i] = temp;
						}
						endCondition = false;
					} else {
						infoList[i].waitTime++;
						endCondition = false;
					}
					if (infoList[i] != null && infoList[i].waitTime == infoTime[i]) {	//시간이 다 됐다
						infoList[i].waitTime = time;	//현재 시간을 넣어줘서 먼저기다린 애들부터 정비하게 하기 
						tempWait.add(infoList[i]);
						infoList[i] = null;
					}
				}
				
				//정비창구
				for (int j = 0; j < M; j++) {
					if (fixList[j] == null) {
						if (fixWait.isEmpty()) continue;
						Person temp = fixWait.poll();
						temp.waitTime = 1;
						temp.fixNum = j;
						fixList[j] = temp;
						endCondition = false;
					} else {
						fixList[j].waitTime++;
						endCondition = false;
					}
					if (fixList[j] != null && fixList[j].waitTime == fixTime[j]) {
						endPerson.add(fixList[j]);
						fixList[j] = null;
					}
				}
				
				//종료조건 
				if (infoWait.isEmpty() && fixWait.isEmpty() && endCondition) break;
				
				//접수창구에서 나온 애들 정비창구 대기줄에 넣어주기 
				while (!tempWait.isEmpty())
					fixWait.add(tempWait.poll());

				time++;
			}
			
			int personNumSum = 0;
			while (!endPerson.isEmpty()) {
				Person temp = endPerson.poll();
				if (temp.infoNum == A - 1 && temp.fixNum == B - 1)
					personNumSum += temp.idx;
			}
			if (personNumSum == 0) personNumSum = -1;
			sb.append("#" + t + " " + personNumSum + "\n");
		}
		
		System.out.print(sb);
	}
}