
import java.io.*;
import java.util.*;

public class Main {
	static int R, C, maxVal = 0;
	static char[][] board;
	static boolean[][] visited;
	static Map<Character, Boolean> alphabet = new HashMap<>();

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < 26; i++) {
			alphabet.put((char) ('A' + i), false);
		}
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			board[i] = line.toCharArray();
		}
		System.out.println(startBoard(0, 0, 1));
	}

//	public static int startBoard(int x,int y) {
//		Queue<int[]> que = new LinkedList<>();
//		que.offer(new int[] {x,y});
//		visited[x][y] = true;
//		alphabet.put(board[x][y], true);
//		int cnt = 1;
//		while(!que.isEmpty()) {
//			int size = que.size();
//			Map<Character,Boolean> temp = new HashMap<>(alphabet);
//			for(int q=0;q<size;q++) {
//				int[] cur = que.poll();
//				for(int i=0;i<4;i++) {
//					int cx = cur[0] + dx[i];
//					int cy = cur[1] + dy[i];
//					if(isBoard(cx,cy) && !visited[cx][cy]) {
//						visited[cx][cy] = true;
//						if(!temp.get(board[cx][cy])) {
//							System.out.println("cx, cy : " + cx + " " + cy);
//							cnt++;
//							temp.put(board[cx][cy], true);
//							que.offer(new int[] {cx,cy});
//						}
//					}
//				}	
//			}
//			alphabet = new HashMap<>(temp);
//		}
//		return cnt;
//	}
	public static int startBoard(int x, int y, int cnt) {
        visited[x][y] = true;
        maxVal = Math.max(maxVal, cnt);
        alphabet.put(board[x][y], true);
        
        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];
            if (isBoard(cx, cy) && !visited[cx][cy] && !alphabet.get(board[cx][cy])) {
//				System.out.println("cx, cy : " + cx + " " + cy);
                startBoard(cx, cy, cnt + 1);
            }
        }
        
        visited[x][y] = false;
        alphabet.put(board[x][y], false);
        
        return maxVal;
    }

	public static boolean isBoard(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}
}
