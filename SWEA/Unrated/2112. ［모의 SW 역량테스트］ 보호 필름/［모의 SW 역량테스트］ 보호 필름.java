import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int minCount;
    static int D;
    static int W;
    static int K;
    static int[][] film;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        for(int test = 1; test<=T;test++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            minCount = Integer.MAX_VALUE;
            film = new int[D][W];
            for(int i=0;i<D;i++){
                st = new StringTokenizer(br.readLine().trim());
                for(int j=0;j<W;j++){
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            if(!checking()){
                dfs(0,0);
            }else {
                minCount = 0;
            }


            System.out.println("#" + test + " " + minCount);
        }
    }
    public static boolean checking(){
        for(int i=0;i<W;i++){
            boolean row = false;
            int cnt = 1;
            for(int j=0;j<D-1;j++){
                if(film[j][i] == film[j+1][i]) {
                    cnt++;
                }
                else {
                    cnt = 1;
                }
                if(cnt >=K){
                    row = true;
                    break;
                }
            }
            if(!row){
                return false;
            }
        }
        return true;
    }
    public static void dfs(int depth,int count){
        if(checking()) {
            minCount = Math.min(count,minCount);
            return;
        }
        if(depth == D) return;
        if(count >= minCount) return;
        int[] temp = new int[W];
        for(int i=0;i<W;i++){
            temp[i] = film[depth][i];
        }
        dfs(depth+1,count);
        // A로 염색
        for(int i=0;i<W;i++){
            film[depth][i] = 0;
        }
        dfs(depth+1,count+1);
        // B로 염색
        for(int i=0;i<W;i++){
            film[depth][i] = 1;
        }
        dfs(depth+1,count+1);
        for(int i=0;i<W;i++){
            film[depth][i] = temp[i];
        }
    }
}
