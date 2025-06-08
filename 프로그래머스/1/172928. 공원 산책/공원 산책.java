import java.io.*;
import java.util.*;

class Solution {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] map;
    public int[] solution(String[] park, String[] routes) {
        int[] answer = new int[2];
        int N = park.length;
        int M = park[0].length();
        map = new char[N][M];
        Map<String,Integer> dirMap = new HashMap<>();
        dirMap.put("N",0);
        dirMap.put("S",1);
        dirMap.put("W",2);
        dirMap.put("E",3);
        for (int i = 0; i < N; i++) {
            map[i] = park[i].toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'S') {
                    answer[0] = i;
                    answer[1] = j;
                }
            }
        }
        for(int i=0;i<routes.length;i++){
            String[] temp = routes[i].split(" ");
            int dir = dirMap.get(temp[0]);
            int dist = Integer.parseInt(temp[1]);
            answer = move(answer[0], answer[1], dist, dir, N, M);

        }
        
        return answer;
    }
    public int[] move(int x, int y, int dist, int d, int N, int M) {
        int nx = x;
        int ny = y;

        // 먼저 dist만큼 이동 경로를 확인
        for (int i = 0; i < dist; i++) {
            nx += dx[d];
            ny += dy[d];

            if (!isIn(nx, ny, N, M) || map[nx][ny] == 'X') {
                return new int[] {x, y}; // 이동 불가능, 원위치 유지
            }
        }

        // 모든 칸이 유효했을 경우만 이동 적용
        return new int[] {nx, ny};
    }
    public boolean isIn(int x,int y,int n,int m){
        return x>=0 && x<n && y>=0 && y<m;
    }
}