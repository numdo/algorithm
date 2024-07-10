// class Solution {
//     public String solution(String s, String skip, int index) {
//         String answer = "";
//         // s 입력 값만큼 for문을 돌림 
//         // skip의 크기만큼 for문을 돌리면서 s의 문자열에 있는 값과 
//         // index만큼 2번째 for문을 돌린다. 그러면서 s 문자열의 값을 증가
//         // skip에 포함되어 있으면 하나 더 증가
        
//         char[] sArray = s.toCharArray();
//         for(int i=0;i<sArray.length;i++){
//             int count = 0;
//             int sIdx = sArray[i];
//             for(int j=0;j<index;j++){
//                 sIdx++;
//                 if(sIdx >= 'a' && sIdx <= 'z'){
//                     if(skip.indexOf((char)sIdx) != -1) {
//                         sIdx++;
//                     }
//                 }
//                 else{
//                     sIdx = 'a' - 1 + sIdx-'z';
//                     if(skip.indexOf((char)sIdx) != -1) {
//                         sIdx++;
//                     }
//                 }
//             }                        
//             answer+=(char)sIdx;
            
//         }
        
//         return answer;
//     }
// }
class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder answer = new StringBuilder();
        for (char c : s.toCharArray()) {
            int count = 0;
            while (count < index) {
                c++;
                if (c > 'z') {
                    c = 'a';
                }
                if (skip.indexOf(c) == -1) {
                    count++;
                }
            }
            answer.append(c);
        }
        return answer.toString();
    }
}
