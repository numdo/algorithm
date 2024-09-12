import java.io.*;
import java.util.*;
class Solution {
    static int answer;
    static int dx[] = {-1,1,0,0};
    static int dy[] = {0,0,-1,1};
    static List<Integer> list;
    public int solution(int[][] land) {
        answer = 0;
        int size = land[0].length;
        boolean[][] visited = new boolean[land.length][size];
        list = new ArrayList<>();
        list.add(0);
        int color = 1;
        for(int i=0;i<land.length;i++){
            for(int j=0;j<land[0].length;j++){
                if(visited[i][j] || land[i][j] == 0) continue;
                int count = bfs(i,j,land,visited,color);
                list.add(color++,count);
            }
        }
        visited = new boolean[land.length][size];
        for(int i=0;i<size;i++){
            Set<Integer> set = new HashSet<>();
            dfs(0,i,set,visited,land);
        }
        return answer;
    }
    public static int bfs(int x,int y,int[][] land,boolean[][] visited,int color){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x,y});
        land[x][y] = color;
        visited[x][y] = true;
        int count = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int i=0;i<4;i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(isMap(nx,ny,land) && !visited[nx][ny] && land[nx][ny] == 1){
                    visited[nx][ny] = true;
                    land[nx][ny] = color;
                    q.offer(new int[]{nx,ny});
                }
            }
            count++;
        }
        return count;
    }
    public static void dfs(int x,int y,Set<Integer> set,boolean[][] visited,int[][] land){
        if(!isMap(x,y,land) || visited[x][y]){
            return;
        }
        set.add(land[x][y]);
        visited[x][y] = true;

        if(x == land.length-1){
            int sum = 0;
            for(int it : set){
                sum += list.get(it);
            }
            answer = Math.max(answer,sum);
            return;
        }
        
        dfs(x+1,y,set,visited,land);      
    }
    public static boolean isMap(int x,int y,int[][] land){
        return x>=0 && x<land.length && y>=0 && y<land[0].length;
    }
}