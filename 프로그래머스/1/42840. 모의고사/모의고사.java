import java.io.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer;

        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        
        int[] math1 = {1,2,3,4,5};
        int[] math2 = {2,1,2,3,2,4,2,5};
        int[] math3 = {3,3,1,1,2,2,4,4,5,5};
        
        for(int i=0;i<answers.length;i++){
            if(answers[i] == math1[i%math1.length]){
                count1++;
            }
            if(answers[i] == math2[i%math2.length]){
                count2++;
            }
            if(answers[i] == math3[i%math3.length]){
                count3++;
            }
        }
        System.out.println(count1);
        System.out.println(count2);
        System.out.println(count3);
        if(count1 > count2 && count1 > count3){
            answer = new int[1];
            answer[0] = 1;
        } else if(count2 > count1 && count2 > count3){
            answer = new int[1];
            answer[0] = 2;
        } else if(count3 > count1 && count3 > count2){
            answer = new int[1];
            answer[0] = 3;
        } else if(count1 == count2 && count2 > count3){
            answer = new int[2];
            answer[0] = 1;
            answer[1] = 2;
        } else if(count1 == count3 && count1 > count2){
            answer = new int[2];
            answer[0] = 1;
            answer[1] = 3;
        } else if(count2 == count3 && count2 > count1){
            answer = new int[2];
            answer[0] = 2;
            answer[1] = 3;
        } else{
            answer = new int[3];
            answer[0] = 1;
            answer[1] = 2;
            answer[2] = 3;
        }
        
        return answer;
    }
}