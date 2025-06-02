import java.io.*;
import java.util.*;
import java.math.*;
class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health;
        // 시전하면 x만큼 회복
        // t초까지 성공하면 y 추가 회복
        int t = bandage[0];
        int x = bandage[1];
        int y = bandage[2];
        int start = 0;
        for(int i=0;i<attacks.length;i++){
            int diff = Math.abs(start - attacks[i][0]);
            start = attacks[i][0] + 1;
            if(diff/t > 0){
                answer += (diff / t) * y;
            }
            answer+=(diff*x);
            System.out.println(answer);
            if(answer >= health){
                answer = health;
            }
            answer -= attacks[i][1];
            if(answer <= 0){
                answer = -1;
                break;
            }
        }
        return answer;
    }
}