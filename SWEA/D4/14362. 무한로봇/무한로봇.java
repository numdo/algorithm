import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int x, y, dir;
	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static Set<String> set;
	static String answer;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String line = br.readLine();
			set = new HashSet<>();
			x=0;
			y=0;
			dir=1;
			set.add(x+","+y+","+dir);
			answer = move(line);
			System.out.println("#" + tc + " " + answer);
		}
	}

	public static String move(String line) {
		int maxVal = 0;
		for(int d=0;d<4;d++) {
			for (int i = 0; i < line.length(); i++) {
				char input = line.charAt(i);
				if (input == 'S') {
					x += dx[dir];
					y += dy[dir];
					maxVal = Math.max(maxVal, x*x + y*y);
				} else if (input == 'L') {
					dir = (dir + 3) % 4;
				} else if (input == 'R') {
					dir = (dir + 1) % 4;
				}
//				System.out.println("x : " + x);
//				System.out.println("y : " + y);
//				System.out.println("dir : " + dir);
			}
			String temp = x+"," + y + "," + dir;
			if(set.contains(temp)) {
				return String.valueOf(maxVal);
			}
			set.add(temp);
		}
		return "oo";
	}

}
