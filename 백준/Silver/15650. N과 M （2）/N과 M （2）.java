import java.util.*;
import java.io.*;


public class Main  {
    static int N;
    static int M;
    static int[] arr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        visited = new boolean[N];
        arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = i+1;
        }

        dfs(0,new ArrayList<>());
    }
    public static void dfs(int depth, List<Integer> list){
        if(list.size() == M){
            for(int i=0;i<M;i++){
                System.out.print(list.get(i)+" ");
            }
            System.out.println();
            return;
        }
        for(int i=depth;i<N;i++){
            if(!visited[i]){
                visited[i] = true;
                list.add(arr[i]);
                dfs(i+1,list);
                list.remove(list.size()-1);
                visited[i] = false;
            }
        }
    }
}
