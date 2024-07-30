import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// deque로 구현해보자
// deque는 양방향 삽입 가능
// 1번 연산하면 get(0)인거고
// 2번 연산하면 get(0)하고 add()
// 3번 연산하면 pop? 하고
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().trim().split(" ");

        int size = Integer.parseInt(line[0]);
        int N = Integer.parseInt(line[1]);
        Deque<Integer> deque = new ArrayDeque<>();
        // deque에 삽입
        for(int i = 1; i <= size; i++){
            deque.add(i);
        }
        String[] str = br.readLine().trim().split(" ");
        // 반복문 조건 생각해야함
        // 앞에서 밖에 못빼니까 poll값이 그 값이면 될듯?
        // 2,3 번연산을 하면 나눌때가 좀 달라질듯
        int count = 0;
        // 문제점
        for(int i = 0; i < str.length; i++){
            int target = Integer.parseInt(str[i]);
            // 그냥 위치를 찾아주면 된다?
            int index = 0;
            for(Integer it : deque){
                if(target == it){
                    break;
                }
                index++;
            }
            // 3번 연산
            if(index > deque.size()/2 ){
                for(int j = 0; j < deque.size()-index; j++){
                    deque.addFirst(deque.removeLast());
                    count++;

                }
            }
            // 2번 연산
            else{
                for(int j = 0; j < index; j++){
                    deque.addLast(deque.removeFirst());
                    count++;

                }
            }
            deque.removeFirst();
        }
        System.out.println(count);


    }
}