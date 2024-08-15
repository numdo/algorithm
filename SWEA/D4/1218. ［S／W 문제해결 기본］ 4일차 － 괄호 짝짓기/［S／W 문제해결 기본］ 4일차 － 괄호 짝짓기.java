import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        //String filePath = "SWEA\\res\\SWEA_1218_input.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= 10; tc++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            Stack<Character> stack = new Stack<>();
            char[] arr = st.nextToken().toCharArray();
            for (int i = 0; i < n; i++) {
                char temp = arr[i];
                if (temp == '{' || temp == '('
                        || temp == '[' || temp == '<') {
                    stack.push(temp);
                } else {
                    char open = stack.peek();
                    if (temp == '}' && open == '{') {
                        stack.pop();
                    } else if (temp == ']' && open == '[') {
                        stack.pop();
                    } else if (temp == ')' && open == '(') {
                        stack.pop();
                    } else if (temp == '>' && open == '<') {
                        stack.pop();
                    } else {
                        break;
                    }
                }
            }
            if (stack.isEmpty()) {
                sb.append("#").append(tc).append(" ").append("1").append("\n");
            } else {
                sb.append("#").append(tc).append(" ").append("0").append("\n");
            }
        }
        System.out.print(sb.toString());
    }
}
