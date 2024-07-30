import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        minHeap heap = new minHeap(n);
        for(int i=0;i<n;i++){
            int input = Integer.parseInt(br.readLine().trim());
            if(input == 0){
                if(heap.isEmpty()){
                    System.out.println(0);
                }
                else{
                    System.out.println(heap.pop());
                }
            }else{
                heap.add(input);
            }
        }
        

    }
    public static class minHeap{
        private int[] arr;
        private int index;
        public minHeap(int n){
            arr = new int[n+1];
            index=0;
        }
        public boolean isEmpty(){
            return index==0?true:false;
        }
        public void add(int x){
            arr[++index] = x;
            // 새로 들어온 값이 트리에 있는 값보다 작으면
            int current = index;

            // Bubble up
            while(current > 1 && arr[current] < arr[current / 2]){
                swap(current, current / 2);
                current = current / 2;
            }
            
        }
        public int peek(){
            return arr[index];
        }
        public int pop(){
            if(isEmpty()) return 0;
            int min = arr[1];
            arr[1] = arr[index--];
            
            // Bubble down
            int current = 1;
            while(current * 2 <= index){
                int child = current * 2;
                if(child + 1 <= index && arr[child + 1] < arr[child]){
                    child++;
                }
                if(arr[current] <= arr[child]){
                    break;
                }
                swap(current, child);
                current = child;
            }
            return min;
        }
        private void swap(int a, int b){
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }
}