import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] standard = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            int x = Integer.parseInt(st.nextToken());
            standard[i] = x;
        }
        Arrays.sort(standard);
        int M = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            int x = Integer.parseInt(st.nextToken());
            boolean target = binarySearch(x,0,N-1,standard);
            if(target){
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }
        System.out.print(sb.toString());

    }
    public static boolean binarySearch(int key, int low, int high, int[] array) {
        int mid = 0;

        if (low <= high) {
            mid = (low + high) / 2;

            if(key == array[mid]){
                return true;
            }
            else if(key < array[mid]){
                return binarySearch(key, low, mid-1 ,array);
            }
            else {
                return binarySearch(key, mid+1, high, array);
            }
        }
        return false;
    }
}
