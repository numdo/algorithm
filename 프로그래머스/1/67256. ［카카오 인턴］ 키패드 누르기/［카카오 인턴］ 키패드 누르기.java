import java.io.*;
import java.util.*;
import java.math.*;
class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        // 1 4 7 * L
        // 3 6 9 # R
        Map<String,Integer> map = new HashMap<>();
        map.put("L",9);
        map.put("R",11);
        for(int it : numbers){
            it = it == 0 ? 10 : it-1;
            int div = it/3;
            int mod = it%3;
            int l = map.get("L");
            int r = map.get("R");
            // System.out.println("cur : " + div + " " + mod);
            // System.out.println("left : " + l/3 + " " + l%3);
            // System.out.println("right: " + r/3 + " " + r%3);
            // System.out.println("=============");
            if(mod == 0) {
                answer+="L";
                map.put("L",it);
            } else if(mod == 2) {
                answer+="R";
                map.put("R",it);
            } else {
                if(Distance(l/3,l%3,div,mod) > Distance(r/3,r%3,div,mod)){
                    answer+="R";
                    map.put("R",it);
                } else if(Distance(l/3,l%3,div,mod) < Distance(r/3,r%3,div,mod)){
                    answer+="L";
                    map.put("L",it);
                } else{
                    String temp = hand.equals("left") ? "L" : "R";
                    answer+=temp;
                    map.put(temp,it);
                }
            }
        }
        return answer;
    }
    private int Distance(int cx, int cy, int nx, int ny){
        return Math.abs(cx - nx) + Math.abs(cy - ny);
    }
}