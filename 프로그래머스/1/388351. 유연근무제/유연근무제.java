import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startDay) {
        int answer = 0;
        boolean[] isSchedules = new boolean[schedules.length];
        for(int i=0;i<timelogs.length;i++){
            int temp = startDay;
            for(int j=0;j<timelogs[i].length;j++){
                if(temp > 5) {
                    temp++;
                    temp = temp> 7 ? 1 : temp;
                    continue;
                }
                temp++;
                int sch = TimeToMinutes(schedules[i]);
                int log = TimeToMinutes(timelogs[i][j]);
                if((sch + 10) < log) {
                    isSchedules[i] = true;
                }
                System.out.println("isSchedules : " + isSchedules[i]);
            }

        }
        for(int i=0;i<isSchedules.length;i++) {
            if(!isSchedules[i]) answer++;
        }
        return answer;
    }
    public int TimeToMinutes(int time){
        return (time/100)*60 + time%100;
    }
}