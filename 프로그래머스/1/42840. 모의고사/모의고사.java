import java.io.*;
import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        int[] answer;

        int[] scores = new int[3];
        
        int[] math1 = {1,2,3,4,5};
        int[] math2 = {2,1,2,3,2,4,2,5};
        int[] math3 = {3,3,1,1,2,2,4,4,5,5};
        
        for(int i=0;i<answers.length;i++){
            if(answers[i] == math1[i%math1.length]){
                scores[0]++;
            }
            if(answers[i] == math2[i%math2.length]){
                scores[1]++;
            }
            if(answers[i] == math3[i%math3.length]){
                scores[2]++;
            }
        }
        int maxScore = Math.max(scores[0],Math.max(scores[1], scores[2]));
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<3;i++){
            if(scores[i] == maxScore){
                list.add(i+1);
            }
        }
        answer = new int[list.size()];
        for(int i=0;i<list.size();i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}