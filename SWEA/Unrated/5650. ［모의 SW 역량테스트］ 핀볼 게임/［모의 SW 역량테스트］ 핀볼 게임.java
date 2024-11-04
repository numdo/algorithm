import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int[][] map;
	static int[] dx = {-1,1,0,0};	// 상 하 좌 우
	static int[] dy = {0,0,-1,1};
	static int ballX,ballY,ballDir;
	static int answer;
    static Map<Integer, List<int[]>> holeMap;
    static boolean[][] visited;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			holeMap = new HashMap<>();
			visited = new boolean[N][N];
			answer = 0;
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] >=6 && map[i][j] <=10) {
                        holeMap.putIfAbsent(map[i][j], new ArrayList<>());
                        holeMap.get(map[i][j]).add(new int[]{i, j});
                    }
				}
			}
			simulate();
			System.out.println("#" + tc + " " + answer);
		}
	}
	public static void simulate() {
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] == 0) {
					for(int d=0;d<4;d++) {
						int temp = goBall(i,j,d);
						answer = Math.max(answer, temp);
					}
				}
			}
		}
		
	}
	public static int goBall(int x,int y,int d) {
        ballX = x;
        ballY = y;
        ballDir = d;
		int cnt = 0;
		while(true) {
			int nx = ballX + dx[ballDir];
			int ny = ballY + dy[ballDir];
			
			if(!isIn(nx,ny)) {
				ballDir = ballDir % 2 ==0 ? ballDir + 1 : ballDir - 1;
				cnt++;
                ballX = nx;
                ballY = ny;
				continue;
			}
			if(map[nx][ny] == -1 || (nx == x && ny == y)) {
				return cnt;
			}
			ballX = nx;
			ballY = ny;
			
			if(map[nx][ny] >=1 && map[nx][ny]<=5) {
				changeDir(map[nx][ny]);
				cnt++;
			}
			else if(map[nx][ny] >=6 && map[nx][ny] <=10) {
				visited[nx][ny] = true;
				goHole(map[nx][ny]);
				visited[nx][ny] = false;
			}
		}
	}
	public static boolean isIn(int x,int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	public static void changeDir(int block) {
		if(block == 1) {
			if(ballDir == 0) {
				ballDir = 1;
			} else if(ballDir == 1) {
				ballDir = 3;
			} else if(ballDir == 2) {
				ballDir = 0;
			} else if(ballDir == 3) {
				ballDir = 2;
			}
		} else if(block==2) {
			if(ballDir == 0) {
				ballDir = 3;
			} else if(ballDir == 1) {
				ballDir = 0;
			} else if(ballDir == 2) {
				ballDir = 1;
			} else if(ballDir == 3) {
				ballDir = 2;
			}
		} else if(block == 3) {
			if(ballDir == 0) {
				ballDir = 2;
			} else if(ballDir == 1) {
				ballDir = 0;
			} else if(ballDir == 2) {
				ballDir = 3;
			} else if(ballDir == 3) {
				ballDir = 1;
			}
		} else if(block==4) {
			if(ballDir == 0) {
				ballDir = 1;
			} else if(ballDir == 1) {
				ballDir = 2;
			} else if(ballDir == 2) {
				ballDir = 3;
			} else if(ballDir == 3) {
				ballDir = 0;
			}
		} else{
			if(ballDir == 0) {
				ballDir = 1;
			} else if(ballDir == 1) {
				ballDir = 0;
			} else if(ballDir == 2) {
				ballDir = 3;
			} else if(ballDir == 3) {
				ballDir = 2;
			}
		}
	}
//    public static void goHole(int block) {
//        List<int[]> holes = holeMap.get(block);
//        for(int[] hole : holes) {
//            if(hole[0] != ballX || hole[1] != ballY) { // 현재 위치가 아닌 다른 홀로 이동
//                ballX = hole[0];
//                ballY = hole[1];
//                break;
//            }
//        }
//    }
	public static void goHole(int block) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] == block && !visited[i][j]) {
					ballX = i;
					ballY = j;
					return;
				}
			}
		}
	}
}
