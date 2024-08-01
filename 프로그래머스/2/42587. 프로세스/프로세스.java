import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<int[]> queue = new LinkedList<>();
        
        
        // 큐에 모든 프로세스를 인덱스와 함께 추가합니다.
        for (int i = 0; i < priorities.length; i++) {
            queue.add(new int[]{priorities[i], i});
        }
        
        int answer = 0;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            boolean hasHigherPriority = false;
            
            // 현재 프로세스보다 높은 우선순위가 있는지 확인합니다.
            for (int[] process : queue) {
                if (process[0] > current[0]) {
                    hasHigherPriority = true;
                    break;
                }
            }
            
            if (hasHigherPriority) {
                // 더 높은 우선순위가 있다면 현재 프로세스를 다시 큐에 넣습니다.
                queue.add(current);
            } else {
                // 그렇지 않다면 현재 프로세스를 실행합니다.
                answer++;
                if (current[1] == location) {
                    // 실행된 프로세스가 우리가 찾는 프로세스라면 결과를 반환합니다.
                    return answer;
                }
            }
        }
        
        return answer;
    }
    

}
