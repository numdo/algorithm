import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<char[]> abc;
	static Set<Character> set;
	static List<ABC> abcList;
	static int answer = 0;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		abc = new ArrayList<>();
		set = new HashSet<>();
		abcList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			abc.add(line.toCharArray());
			for (char it : abc.get(i)) {
				set.add(it);
			}
		}
		for (char it : set) {
			abcList.add(new ABC(it, 0));
		}
		perm(0,new boolean[10]);
		System.out.println(answer);
	}

	private static void perm(int depth,boolean[] selected) {
		if (depth == abcList.size()) {
			answer = Math.max(answer, calABC());
			return;
		}

		for (int i = 0; i < 10; i++) {
			if(selected[i]) continue;
			selected[i] = true;
			abcList.get(depth).val = i;
			perm(depth + 1,selected);
			selected[i] = false;
		}
	}
	static int calABC() {
		int sum = 0;
		for (char[] temp : abc) {
			int num = 0;
			for (int i = 0; i < temp.length; i++) {
				for (ABC it : abcList) {
					if (it.idx == temp[i]) {
						num = 10*num + it.val;
					}
				}
			}
			sum+=num;
		}
		
		return sum;
	}

	static class ABC {
		char idx;
		int val;

		public ABC(char idx, int val) {
			this.idx = idx;
			this.val = val;
		}

	}
}