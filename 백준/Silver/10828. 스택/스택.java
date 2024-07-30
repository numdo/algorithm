import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        BOJStack s = new BOJStack();
        for(int i = 1; i <= n; i++) {
            String[] line = br.readLine().split(" ");
            if(line[0].equals("push")) {
                s.push(Integer.parseInt(line[1]));
            }
            else if(line[0].equals("pop")) {
                System.out.println(s.pop());
            }
            else if(line[0].equals("empty")) {
                System.out.println(s.empty());
            }
            else if(line[0].equals("size")) {
                System.out.println(s.size());
            }
            else if(line[0].equals("top")) {
                System.out.println(s.top());
            }
        }
    }
    public static class BOJStack{
        private int index;
        private List<Integer> stack;
        public BOJStack(){
            this.index = -1;
            stack = new ArrayList<>();
        }
        public int empty(){
            return index==-1 ? 1:0;
        }
        public void push(int x){
            stack.add(x);
            index++;
        }
        public int pop(){
            if(empty()>0) return -1;
            return stack.remove(index--);
        }
        public int size(){
            return index+1;
        }
        public int top(){
            if(empty()==1) return -1;
            return stack.get(index);
        }
    }
}