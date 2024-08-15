
import java.io.*;
import java.util.*;
public class Main {
	static int N;
	static int M;
	static List<Integer> list = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dfs(0);
		System.out.print(sb);
	}
	public static void dfs(int depth) {
		if(depth == M) {
			for(int it : list) {
				sb.append(it).append(" ");
//				System.out.println(it + " ");
			}
			sb.append("\n");
			return;
		}
		for(int i=0;i<N;i++) {
			list.add(i+1);
			dfs(depth+1);
			list.remove(depth);
		}
	}

}
