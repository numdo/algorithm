import java.util.*;

class Solution {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    public int solution(String[] storage, String[] requests) {
        char[][] container = toCharGrid(storage);
        int remain = totalCount(container);

        for (String req : requests) {
            char target = req.charAt(0);
            if (req.length() == 2) { // 크레인: 해당 알파벳 모두 제거
                remain = removeAll(container, target, remain);
            } else { // 지게차: 접근 가능한 것만 제거
                remain = removeAccessible(container, target, remain);
            }
        }
        return remain;
    }

    /** 문자열 배열 → char 2차원 배열로 변환 */
    private char[][] toCharGrid(String[] storage) {
        int n = storage.length;
        char[][] grid = new char[n][];
        for (int i = 0; i < n; i++) grid[i] = storage[i].toCharArray();
        return grid;
    }

    /** 전체 칸 개수 */
    private int totalCount(char[][] grid) {
        int sum = 0;
        for (char[] row : grid) sum += row.length;
        return sum;
    }

    /** 크레인: target 문자를 모두 제거 ('.'로) */
    private int removeAll(char[][] w, char target, int remain) {
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < w[i].length; j++) {
                if (w[i][j] == target) {
                    w[i][j] = '.';
                    remain--;
                }
            }
        }
        return remain;
    }

    /** 지게차: 접근 가능한 target만 제거 */
    private int removeAccessible(char[][] w, char target, int remain) {
        boolean[][] outerBlank = findOuterBlanks(w);

        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < w[i].length; j++) {
                if (w[i][j] != target) continue;

                // 접근 가능 여부 판정
                if (isEdge(i, j, w) || isAdjacentToOuterBlank(i, j, w, outerBlank)) {
                    w[i][j] = '.';
                    remain--;
                }
            }
        }
        return remain;
    }

    /** 창고 가장자리 여부 */
    private boolean isEdge(int i, int j, char[][] w) {
        return i == 0 || i == w.length - 1 || j == 0 || j == w[i].length - 1;
    }

    /** 주변 4칸 중 외부와 연결된 빈칸이 있는지 */
    private boolean isAdjacentToOuterBlank(int x, int y, char[][] w, boolean[][] outer) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if (isIn(nx, ny, w) && outer[nx][ny]) return true;
        }
        return false;
    }

    /** 외부와 연결된 빈칸을 BFS로 탐색 */
    private boolean[][] findOuterBlanks(char[][] w) {
        int n = w.length;
        boolean[][] visited = new boolean[n][];
        for (int i = 0; i < n; i++) visited[i] = new boolean[w[i].length];
        Queue<int[]> q = new ArrayDeque<>();

        // 가장자리 빈칸을 큐에 넣음
        for (int i = 0; i < n; i++) {
            enqueueBlank(i, 0, w, visited, q);
            enqueueBlank(i, w[i].length - 1, w, visited, q);
        }
        for (int j = 0; j < w[0].length; j++) {
            enqueueBlank(0, j, w, visited, q);
            enqueueBlank(n - 1, j, w, visited, q);
        }

        // BFS로 외부와 연결된 빈칸 모두 찾기
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d], ny = cur[1] + dy[d];
                if (isIn(nx, ny, w) && !visited[nx][ny] && w[nx][ny] == '.') {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        return visited;
    }

    /** 해당 칸이 빈칸이면 큐에 넣고 방문 표시 */
    private void enqueueBlank(int x, int y, char[][] w, boolean[][] visited, Queue<int[]> q) {
        if (w[x][y] == '.' && !visited[x][y]) {
            visited[x][y] = true;
            q.add(new int[]{x, y});
        }
    }

    /** 범위 체크 */
    private boolean isIn(int x, int y, char[][] w) {
        return 0 <= x && x < w.length && 0 <= y && y < w[x].length;
    }
}
