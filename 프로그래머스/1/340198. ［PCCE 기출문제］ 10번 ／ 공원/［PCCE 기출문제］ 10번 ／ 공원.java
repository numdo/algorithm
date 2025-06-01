import java.io.*;
import java.util.*;
class Solution {
    static int[] di = {0,1,1};
    static int[] dj = {1,1,0};
    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        int temp = 0;
        for(int i=0;i<park.length;i++){
            for(int j=0;j<park[0].length;j++){
                if(park[i][j].equals("-1")){
                    int p = bfs(i,j,park);
                    System.out.println(i+" ,"+j);
                    System.out.println(p);
                    System.out.println();
                    temp = Integer.max(p,temp);
                }
            }
        }
        for(int i=0;i<mats.length;i++){
            if(mats[i] <= temp){
                answer = Integer.max(mats[i],answer);
            }
        }
        return answer;
    }
    static int bfs(int i, int j, String[][] park){
        Queue<int[]> q = new LinkedList<>();
        int[][] visited = new int[park.length][park[0].length];
        q.add(new int[]{i,j});
        visited[i][j] = 1;
        int size = 1;
        while(!q.isEmpty()){
            int depth = q.size();
            for(int k = 0;k<depth;k++){
                int[] cur = q.poll();
                for(int d=0;d<3;d++){
                    int ni = cur[0] + di[d];
                    int nj = cur[1] + dj[d];
                    if(isIn(ni,nj,park.length,park[0].length) && park[ni][nj].equals("-1")){
                        if(visited[ni][nj] == 0) {
                            visited[ni][nj] = 1;
                            q.add(new int[]{ni,nj});
                        }
                    } else{
                        return size;
                    }
                }
            }
            size++;
        }
        return size;
    }
    static boolean isIn(int i,int j,int iLen,int jLen){
        return i<iLen && j<jLen;
    }
}