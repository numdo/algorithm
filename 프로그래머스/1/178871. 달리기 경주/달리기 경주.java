import java.io.*;
import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        Map<String,Integer> playerSeq = new HashMap<>();
        Map<Integer,String> seq = new HashMap<>();
        for(int i=0;i<players.length;i++){
            playerSeq.put(players[i],i);
            seq.put(i,players[i]);
        }
        for(String it:callings){
            int temp = playerSeq.get(it)-1;
            playerSeq.put(it,temp);
            String prev = seq.get(temp);
            playerSeq.put(prev,temp+1);
            seq.put(temp+1,prev);
            seq.put(temp,it);
        }
        seq.forEach((key,value) -> {
            answer[key] = value; 
        });
        return answer;
    }
}