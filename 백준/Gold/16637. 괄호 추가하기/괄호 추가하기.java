import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static char arr[];
	static boolean[] visited;
	static int answer = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String line = br.readLine();
		arr = new char[N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			arr[i] = line.charAt(i);
		}
			findBracket(1);
		
		System.out.println(answer);
	}

	public static void findBracket(int depth) {
		if (depth >= N) {
			answer = Integer.max(calculate(),answer);
			return;
		}
		visited[depth] = true;
		findBracket(depth + 4);
		visited[depth] = false;
		findBracket(depth + 2);
	}

	public static int calculate() {
		List<Integer> cal = new ArrayList<>();
		for(int i=0;i<N;i++) {
			if(visited[i]) {
				if(arr[i] == '+') {
					int temp = cal.remove(cal.size()-1);
					cal.add(temp+(arr[i+1]-'0'));
				}
				else if(arr[i] == '-') {
					int temp = cal.remove(cal.size()-1);
					cal.add(temp-(arr[i+1]-'0'));
				}
				else if(arr[i] == '*') {
					int temp = cal.remove(cal.size()-1);
					cal.add(temp*(arr[i+1]-'0'));
				}
				i++;
			}
			else {
				if(arr[i] == '+' || arr[i] == '-' || arr[i]=='*') {
					cal.add((int)arr[i]);
				}
				else {
					cal.add(arr[i]-'0');
				}
			}
		}
		int temp = cal.get(0);
		for(int i=1;i<cal.size();i+=2) {
			if(cal.get(i) == '+') {
				temp += cal.get(i+1);
			} else if(cal.get(i) == '-' ) {
				temp -= cal.get(i+1);
			} else if(cal.get(i) == '*') {
				temp *= cal.get(i+1);
			}
		}
		return temp;
	}
}