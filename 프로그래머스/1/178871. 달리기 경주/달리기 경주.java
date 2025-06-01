import java.io.*;
import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String,Integer> playerSeq = new HashMap<>();
        for(int i=0;i<players.length;i++){
            playerSeq.put(players[i],i);
        }
        for(String it:callings){
            int idx = playerSeq.get(it);
            String prev = players[idx-1];
            players[idx-1] = it;
            players[idx] = prev;
            playerSeq.put(it,idx-1);
            playerSeq.put(prev,idx);
        }
        
        return players;
    }
}