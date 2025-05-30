import java.io.*;
import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        // 준 선물
        // 받은 선물
        // 선물 지수(내가 보낸 선물 - 받은 선물)
        // friends에 있는 사람들중 가장 많이 받은 사람이 다음달에 받는 선물의 수
        int fLen = friends.length;
        int gLen = gifts.length;
        int[] giftGap = new int[fLen];
        int[] giftCount = new int[fLen];
        int[][] forGifts = new int[fLen][fLen];
        Map<String,Integer> seq = new HashMap<>();
        for(int i=0;i<fLen;i++){
            seq.put(friends[i],i);
        }
        
        for(int i=0;i<gLen;i++){
            String[] temp = gifts[i].split(" ");
            
            // 누가 누구에게
            int from = seq.get(temp[0]);
            int to = seq.get(temp[1]); 
            forGifts[from][to]++;
            giftGap[from]++;
            giftGap[to]--;
        }
        
        for(int i=0;i<fLen;i++){
            for(int j=i+1;j<fLen;j++){
                if(forGifts[i][j] > forGifts[j][i]){
                    giftCount[i]++;
                } else if(forGifts[i][j] < forGifts[j][i]){
                    giftCount[j]++;
                } else{
                    if(giftGap[i] > giftGap[j]){
                        giftCount[i]++;
                    } else if(giftGap[i] < giftGap[j]){
                        giftCount[j]++;
                    }
                }
            }
        }
        for(int i=0;i<fLen;i++){
            if(answer < giftCount[i]){
                answer = giftCount[i];
            }
        }
        
        return answer;
    }
}