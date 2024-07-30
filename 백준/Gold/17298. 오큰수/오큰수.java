import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());
        String[] temp = br.readLine().split(" ");
        int[] input = new int[n];
        int[] answer = new int[n];
        for(int i=0;i<n;i++){
            input[i] = Integer.parseInt(temp[i]);
        }
        Stack<Integer> indexList = new Stack<Integer>();
        indexList.push(0);
        for(int i=1;i<n;i++){
            while(!indexList.isEmpty() && input[indexList.peek()] < input[i]){
                answer[indexList.pop()] = input[i];
            }
            indexList.push(i);
        }
        while(!indexList.isEmpty()){
            answer[indexList.pop()] = -1;
        }
        for(int i=0;i<answer.length;i++){
            bw.write(answer[i] + " ");
        }
        bw.flush();
    }

}
