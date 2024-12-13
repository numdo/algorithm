import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int[][] map;
	static boolean[][][][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[N][M][K + 1][2];
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		int answer = bfs();
		System.out.println(answer);
	}

    public static int bfs() {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0,0,1});
        visited[0][0][0][1] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x=cur[0], y=cur[1], broken=cur[2], count=cur[3];
            if(x==N-1 && y==M-1) return count;

            int time = (count%2); // 1=낮, 0=밤
            int nextCount = count+1;
            int nt = nextCount%2; // 다음 단계 낮/밤
            
            for(int d=0; d<4; d++) {
                int nx = x+dx[d], ny = y+dy[d];
                if(nx<0||nx>=N||ny<0||ny>=M) continue;

                if(map[nx][ny]==0 && !visited[nx][ny][broken][nt]) {
                    visited[nx][ny][broken][nt] = true;
                    q.offer(new int[]{nx,ny,broken,nextCount});
                } else if(map[nx][ny]==1 && time==1 && broken<K && !visited[nx][ny][broken+1][nt]) {
                    visited[nx][ny][broken+1][nt] = true;
                    q.offer(new int[]{nx,ny,broken+1,nextCount});
                }
            }

            if(time==0 && !visited[x][y][broken][nt]) { 
                // 밤에 대기
                visited[x][y][broken][nt] = true;
                q.offer(new int[]{x,y,broken,nextCount});
            }
        }

        return -1;
    }

	public static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
