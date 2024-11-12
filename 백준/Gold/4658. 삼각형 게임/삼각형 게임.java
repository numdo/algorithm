import java.io.*;
import java.util.*;

public class Main {
	static List<int[]> list;
	static int answer;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			list = new ArrayList<>();
			answer = Integer.MIN_VALUE;
			for (int i = 0; i < 6; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int[] temp = new int[3];
				temp[0] = Integer.parseInt(st.nextToken());
				temp[1] = Integer.parseInt(st.nextToken());
				temp[2] = Integer.parseInt(st.nextToken());
				list.add(temp);
			}
			make(0,new int[6],new boolean[6]);
			if(answer == Integer.MIN_VALUE) {
				System.out.println("none");
			}
			else {
				System.out.println(answer);
			}
			String line = br.readLine();
			if (line.charAt(0) == '$') {
				break;
			}
		}
	}
	public static void make(int depth,int[] temp,boolean[] visited) {
		if(depth == 6) {
			int[] rotation = new int[6];
			turn(0,rotation,temp);
			return;
		}
		for(int i=0;i<6;i++) {
			if(visited[i]) continue;
			visited[i] = true;
			temp[depth] = i;
			make(depth+1,temp,visited);
			visited[i] = false;
		}
	}
	// 위치는 어디든 상관없다
	// 그러니까 0 2 4의 1번이랑 1 3 5의 0번이 일치하면 2번을 출력한다
	// 아니면 0번째 돌리고
	public static void turn(int depth,int[] info,int[] perm) {
		if(depth == 6) {
			int temp = isHex(perm,info);
			if(temp != -1) {
				answer = Math.max(temp,answer);
			}
			return;
		}
		for(int i=0;i<3;i++) {
			info[depth] = i;
			turn(depth+1,info,perm);
		}
	}

	private static int[] cycle(int[] perm,int info) {
		int[] clone = perm.clone();
		for(int i=0;i<info;i++) {
			int temp = clone[2];
			clone[2] = clone[1];
			clone[1] = clone[0];
			clone[0] = temp;
		}
		return clone;
	}
	public static int isHex(int[] perm,int[] info) {
		int[] cur = cycle(list.get(perm[0]),info[0]);
		int sum = cur[2];
		for (int i = 1; i < 6; i++) {
			int[] next = cycle(list.get(perm[i]),info[i]);
			if (cur[1] != next[0]) {
				return -1;
			}
			sum+=next[2];
			cur = next;
		}
		if(cur[1] != cycle(list.get(perm[0]), info[0])[0]) {
			return -1;
		}
		
		return sum;
	}
}
