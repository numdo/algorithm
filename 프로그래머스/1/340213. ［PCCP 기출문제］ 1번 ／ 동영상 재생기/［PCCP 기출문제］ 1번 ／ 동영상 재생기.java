import java.io.*;
import java.util.*;
class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoL = MinuteToSecond(video_len);
        int cur = MinuteToSecond(pos);
        int opS = MinuteToSecond(op_start);
        int opE = MinuteToSecond(op_end);
        
        for(String it:commands){
            if(cur >= opS && cur <= opE){
                cur = opE;
            }
            if(it.equals("next")){
                cur+=10;
                if(cur>videoL) cur = videoL;
            } else if(it.equals("prev")){
                cur-=10;
                if(cur<0) cur = 0;
            }
        }
        if(cur >= opS && cur <= opE){
            cur = opE;
        }
        return SecondToTime(cur);
    }
    static int MinuteToSecond(String time){
        String[] temp = time.split(":");
        return Integer.parseInt(temp[0])*60 + Integer.parseInt(temp[1]);
    }
    static String SecondToTime(int cur){
        return String.format("%02d",cur/60) + ":" + String.format("%02d",cur%60);
    }
}