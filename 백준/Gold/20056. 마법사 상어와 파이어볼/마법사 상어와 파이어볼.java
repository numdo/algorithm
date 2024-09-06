import java.io.*;
import java.util.*;

public class Main {
	static int N,M,K;
	static Fire[][] map;
	static List<Fire>[][] unionMap;
	static Queue<Fire> fireQ = new LinkedList<>();
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,1,1,1,0,-1,-1,-1};
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new Fire[N][N];
		unionMap = new ArrayList[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				unionMap[i][j] = new ArrayList<>();
			}
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			unionMap[x][y].add(new Fire(x,y,m,s,dir));
			fireQ.add(new Fire(x,y,m,s,dir));
		}
		simulate();
		
		int answer = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				for(Fire fire : unionMap[i][j]) {
					answer+=fire.m;
				}
			}
		}
		System.out.println(answer);
	}
	
	static void simulate() {
		
		for(int k=0;k<K;k++) {
			while(!fireQ.isEmpty()) {
				Fire fire = fireQ.poll();
				moveBall(fire);
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(unionMap[i][j].size()>1) {
						unionBall(i,j,unionMap[i][j]);
					}
				}
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!unionMap[i][j].isEmpty()) {
						for(Fire fire:unionMap[i][j]) {
							fireQ.add(fire);
						}
					}
				}
			}
		}
	}
	
	static void moveBall(Fire fire) {
		unionMap[fire.x][fire.y].remove(0);
		int nx = fire.x + fire.s * dx[fire.dir];
		int ny = fire.y + fire.s * dy[fire.dir];
		fire.x = isArea(nx);
		fire.y = isArea(ny);
		unionMap[fire.x][fire.y].add(fire);	// 나중에 합쳐질 맵에 넣어줌
	}
	
	static int isArea(int x) {
        if (x < 0) {
            x = (x % N + N) % N;
        } else {
            x = x % N;
        }
        return x;
    }
	
	static void unionBall(int x,int y,List<Fire> fireList) {
		int mess = 0;
		int speed = 0;
		int odd = 0;		// 
		int even = 0;		// 
		int size = fireList.size();
		for(Fire fire :fireList) {
			mess+=fire.m;
			speed+=fire.s;
			if(fire.dir %2==0) {
				even++;
			}
			else {
				odd++;
			}
		}
		mess/=5;
		speed/=size;
		fireList.clear();
		if(mess ==0) {
			return;
		}
		for(int i=0;i<4;i++) {
			if(odd == size || even == size) {
				fireList.add(new Fire(x,y,mess,speed,i*2));
			}
			else {
				fireList.add(new Fire(x,y,mess,speed,i*2+1));
			}
		}
		
	}
	
	
	static class Fire{
		int x,y,m,s,dir;

		public Fire(int x, int y, int m, int s, int dir) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Fire [x=" + x + ", y=" + y + ", m=" + m + ", s=" + s + ", dir=" + dir + "]";
		}
		
		
	}
}
