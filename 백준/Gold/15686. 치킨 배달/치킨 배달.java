
import java.io.*;
import java.util.*;

public class Main {
	static int N,M;
	static boolean[] visited;
	static List<Streets> home, chicken;
	static int result = Integer.MAX_VALUE;
	static Streets[] select;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 치킨집들을 조합하고 그 조합에 대한 가까운 치킨집에 대한 거리의 최소값을 구한다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		home = new ArrayList<>();
		chicken = new ArrayList<>();
		select = new Streets[M];
		// 입력 받기
		for(int i = 1;i<=N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j =1;j<=N;j++) {
				int temp =  Integer.parseInt(st.nextToken());
				if(temp == 1) home.add(new Streets(i,j,temp));
				else if (temp == 2) chicken.add(new Streets(i,j,temp));
			}
		}
		
		visited = new boolean[chicken.size()];
		comb(0,0);
		System.out.println(result);
		
	}
	public static void comb(int start,int depth) {
		if(depth == M) {
			int tempDis = 0;
			for(Streets h: home) {
				int minDis = Integer.MAX_VALUE;
				for(Streets c : select) {
					minDis = Math.min(minDis, h.distance(c.x, c.y));
				}
				tempDis += minDis;
				if(tempDis > result) return;
			}
			result = Math.min(result,tempDis);
			return;
		}
		for(int i=start;i<chicken.size();i++) {
//			if(visited[i]) continue;
//			visited[i] = true;
			
//			chicken.get(i).select = true;
			select[depth] = chicken.get(i);
			comb(i+1,depth+1);
		}
	}
	static class Streets{
		int x,y,kind;
		boolean select;
		Streets(int x ,int y,int kind){
			this.x = x;
			this.y = y;
			this.kind = kind;
		}
		int distance(int x,int y) {
			return Math.abs(this.x - x) + Math.abs(this.y - y);
		}
	}
}
