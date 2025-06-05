class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;

        int wW = wallet[0];
        int wH = wallet[1];

        int bW = bill[0];
        int bH = bill[1];

        while (true) {

            if ((bW <= wW && bH <= wH) || (bH <= wW && bW <= wH)) {
                break;
            }

            if (bW >= bH) {
                bW /= 2;
            } else {
                bH /= 2;
            }
            answer++;
        }

        return answer;
    }
}