import java.util.HashMap;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        // 일단 해시 갈기고 key 옷 , val 옷 종류 하고 
        // 일단 clothes의 길이만큼은 더하고 플러스 조합인데
        // 조합 할때 val이 같으면 패스 하면되네
        // 지금 map에 종류,옷 으로 들어가있음
        // 종류별로 더하고 같은종류가 있으면 하나당 곱하ㄱ
        HashMap<String,Integer> map = new HashMap<>();
        for(String[] it : clothes){
            map.put(it[1] ,map.getOrDefault(it[1],0)+1);
            // System.out.println(it[0] + " " + it[1]);
            
        }
        // 2 2 1 1 = 6 + 
        // 
        // map에 다 넣었고 한번 돌때마다 카운트 하고 map을 한번 더돌리고 키가 
        for(String key : map.keySet()){
            answer *= (map.get(key)+1);
        }
        
        return answer-1;
    }
}