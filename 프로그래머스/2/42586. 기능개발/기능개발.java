import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> temp = new ArrayList<>();
        List<Integer> stack = new ArrayList<>();
        for(int i=0;i<progresses.length;i++){
            int day = (100-progresses[i])/speeds[i];
            if((100-progresses[i])%speeds[i] == 0){
                stack.add(day);
            }
            else{
                stack.add(day + 1);                
            }
        }
        // 7 0 9
        int cur = stack.get(0);
        int count = 1;
        for(int i=1;i<stack.size();i++){
            if(stack.get(i) > cur) {
                cur = stack.get(i);
                temp.add(count);
                count = 1;
            }
            else{
                count++;                
            }
        }
        temp.add(count);
        int[] answer = new int[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            answer[i] = temp.get(i);
        }
        return answer;
    }
}