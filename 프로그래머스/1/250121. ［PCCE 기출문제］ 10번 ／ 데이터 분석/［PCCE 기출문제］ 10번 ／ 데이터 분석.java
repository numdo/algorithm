import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        // 각 필드명에 해당하는 인덱스 설정
        Map<String,Integer> map = new HashMap<>();
        map.put("code", 0);
        map.put("date", 1);
        map.put("maximum", 2);
        map.put("remain", 3);

        int extIdx = map.get(ext);         // 필터링 기준 인덱스
        int sortIdx = map.get(sort_by);    // 정렬 기준 인덱스

        // 조건에 맞는 항목만 필터링
        List<int[]> filtered = new ArrayList<>();
        for (int[] row : data) {
            if (row[extIdx] < val_ext) {
                filtered.add(row);
            }
        }

        filtered.sort(Comparator.comparingInt(a -> a[sortIdx]));

        int[][] answer = new int[filtered.size()][4];
        for (int i = 0; i < filtered.size(); i++) {
            answer[i] = filtered.get(i);
        }

        return answer;
    }
}
