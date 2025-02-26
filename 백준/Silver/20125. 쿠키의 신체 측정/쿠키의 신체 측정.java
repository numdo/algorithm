import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static char[][] grid;
    static int[] dx = { 0, 0, 1};
    static int[] dy = { -1, 1, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int sx = 1;
        int sy = 1;
        boolean flag = false;
        grid = new char[N][N];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < N; j++) {
                grid[i][j] = line.charAt(j);
                if(line.charAt(j) == '*' && !flag) {
                    flag = true;
                    sx = i + 1;
                    sy = j;
                }
            }
        }
        // 왼팔, 오른팔, 허리, 왼다리, 오른다리
        int[] body = new int[5];
        // 허리 왼팔 오른팔
        body[0] = go(sx,sy,0,0);
        body[1] = go(sx,sy,0,1);
        body[2] = go(sx,sy,0,2);
        body[3] = go(sx+body[2] + 1,sy-1,0,2) + 1;
        body[4] = go(sx+body[2] + 1,sy+1,0,2) + 1;

        System.out.println((sx + 1) + " " + (sy + 1));
        for(int i = 0; i < 5; i++) {
            System.out.print(body[i] + " ");
        }
    }
    public static int go(int i, int j, int depth,int dir) {
        if(!isIn(i,j)){
            return depth - 1;
        }
        if(grid[i][j] == '_') {
            return depth - 1;
        }
        return go(i + dx[dir], j+dy[dir],depth + 1, dir);

    }
    public static boolean isIn(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}