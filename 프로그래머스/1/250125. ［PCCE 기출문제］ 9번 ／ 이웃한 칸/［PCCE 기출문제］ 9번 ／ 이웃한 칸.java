class Solution {

    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        int[] dx = {0,0,-1,1};
        int[] dy = {1,-1,0,0};
        for(int i=0;i<4;i++){
            int nx = h + dx[i];
            int ny = w + dy[i];
            if(isIn(nx,ny,board.length,board[0].length) && 
               board[h][w].equals(board[nx][ny])){
                answer++;
            }
        }
        return answer;
    }
    public boolean isIn(int x,int y, int maxX, int maxY){
        return x>=0 && x<maxX && y>=0 && y<maxY;
    }
}