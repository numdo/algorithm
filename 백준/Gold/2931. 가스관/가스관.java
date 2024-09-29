import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static Gas start, end, answer;
	static int dx[] = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int dy[] = { 0, 0, -1, 1 };
	static boolean[][] visited;
	static char[][] map;
	static int[][] pipe = { 
			{ 0, 0, 0, 0 }, 
			{ 1, 1, 0, 0 }, // |
			{ 0, 0, 1, 1 }, // -
			{ 1, 1, 1, 1 }, // +
			{ 0, 1, 0, 1 }, // 1
			{ 1, 0, 0, 1 }, // 2
			{ 1, 0, 1, 0 }, // 3
			{ 0, 1, 1, 0 } // 4
	};

	static class Gas {
		int x, y;
		int[] dir;

		public Gas(int x, int y, int[] dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'M') {
					start = new Gas(i, j, pipe[3]);
				} else if (map[i][j] == 'Z') {
					end = new Gas(i, j, pipe[3]);
				}
			}
		}
		bfs();
		
		for(int i=0;i<4;i++) {
			int nx = answer.x + dx[i];
			int ny = answer.y + dy[i];
			int d = i%2 == 0 ? i+1 : i-1;
			if(isMap(nx,ny)) {
				int[] nd = gasPipe(map[nx][ny]);
				if(nd[d] == 1) {
					answer.dir[i] = 1;
				}
			}
		}
		char dir = ' ';

		for(int i=1;i<8;i++) {
			int cnt = 0;
			for(int j =0;j<4;j++) {
				if(answer.dir[j] == pipe[i][j]) {
					cnt++;
				}
			}
			if(cnt >=4) {
				switch(i) {
				case 1:
					dir = '|';
					break;
				case 2:
					dir = '-';
					break;
				case 3:
					dir = '+';
					break;
				case 4:
					dir = '1';
					break;
				case 5:
					dir = '2';
					break;
				case 6:
					dir = '3';
					break;
				case 7:
					dir = '4';
					break;
				}
			}
		}
		System.out.printf("%d %d %c\n",answer.x+1,answer.y+1,dir);
//		System.out.println((answer.x + 1) + " " + (answer.y + 1) + " " + dir);
	}

	public static void bfs() {
		Queue<Gas> q = new LinkedList<>();
		q.add(start);
		visited[start.x][start.y] = true;

		while (!q.isEmpty()) {
			Gas cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (cur.dir[i] == 1 && isMap(nx, ny) && !visited[nx][ny]) {
					if (map[nx][ny] != '.') {
						visited[nx][ny] = true;
						int d = i%2 == 0 ? i+1 : i-1;
						int[] nd = gasPipe(map[nx][ny]);
						if (cur.dir[i] == nd[d]) {
						    q.add(new Gas(nx, ny, nd));
						}

					} else {
						if(!cur.equals(start)) {
							answer = new Gas(nx,ny,new int[] {0,0,0,0});
							return;
						}
					}
				}
			}
		}
	}

	public static boolean isMap(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
	public static int[] gasPipe(char c) {
		if(c=='.') {
			return pipe[0];
		} 
		else if(c == '|') {
			return pipe[1];
		}
		else if(c == '-') {
			return pipe[2];
		}
		else if(c == '+') {
			return pipe[3];
		}
		else if(c == '1') {
			return pipe[4];
		}
		else if(c == '2') {
			return pipe[5];
		}
		else if(c == '3') {
			return pipe[6];
		}
		else if(c == '4') {
			return pipe[7];
		}
		else {
			return pipe[0];
		}
	}
}
