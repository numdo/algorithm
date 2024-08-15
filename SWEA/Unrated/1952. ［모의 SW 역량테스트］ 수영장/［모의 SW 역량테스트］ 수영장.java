import java.io.*;

public class Solution {
    static int minVal;
    static int maxVal;
    static final int MONTH = 12;
    static int[] tickets;
    static int[] planList;
    public static void main(String args[]) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 각 이용권을 몇번 사용해야 다음 이용권의 가격이 될수 있는지를 체크
        // 가장 작은 이용권이 제일 싸면 그걸로
        // 1. 완전탐색으로 모든 경우의수를 다구한다?

        int T = Integer.parseInt(br.readLine().trim());
        for(int test = 1;test<=T;test++){
            String[] line = br.readLine().trim().split(" ");
            tickets = new int[4];
            for(int i=0;i<tickets.length;i++){
                tickets[i] = Integer.parseInt(line[i]);
            }
            String[] plan = br.readLine().trim().split(" ");
            planList = new int[MONTH];
            for(int i=0;i<MONTH;i++){
                planList[i] = Integer.parseInt(plan[i]);
            }
            minVal = tickets[3];
            maxVal = tickets[3]+1;
            dfs(0,0);
            System.out.println("#" + test + " " + minVal);
        }
    }
    // 티켓들의 값이 적힌 배열 하나
    // 계획이 담긴 배열 하나(index는 달 value는 일수)
    // month+1 == 달수
    // value는 최소값
    // chk 배열
    // 인덱스값이 안넘도록 해주고
    // 0이면 다음걸로 패스
    // 일권 * 한달의 횟수 < 한달권 한달권 더해주고
    // 아니면 일권것 더해주고
    // 3번 반복해도 안되면 3달치꺼 더해주고
    //
    public static void dfs(int month, int value){
        if(month >= 12){
            minVal = value < minVal ? value : minVal;
            maxVal = value > maxVal ? value : maxVal;
            return;
        }
        if(maxVal < value) return;
        if(planList[month] == 0){
            dfs(month +1,value);
        }
        else{
            // 처음에 일권으로 검사를 해 그리고 다음 시나리오로 가 근데 min 값보다 작아졌어 그러면 루프나가
            dfs(month +1,value + (planList[month] * tickets[0]));
            // 그러면 월권으로 오지? 거기서
            dfs(month +1,value + tickets[1]);

            dfs(month +3,value + tickets[2]);

        }

    }

}
