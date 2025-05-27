import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        // 1️⃣ 신고 내역 중복 제거
        Set<String> uniqueReports = new HashSet<>(Arrays.asList(report));

        // 2️⃣ 신고당한 사람별로 신고자 목록을 생성
        Map<String, Set<String>> reportedMap = uniqueReports.stream()
                .map(r -> r.split(" "))
                .collect(Collectors.groupingBy(
                        arr -> arr[1],
                        Collectors.mapping(arr -> arr[0], Collectors.toSet())
                ));

        // 3️⃣ k번 이상 신고당한 사람만 필터링 (정지 대상자)
        Set<String> suspendedUsers = reportedMap.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        // 4️⃣ 각 유저별로 메일 받을 횟수를 계산
        Map<String, Long> mailCounts = uniqueReports.stream()
                .map(r -> r.split(" "))
                .filter(arr -> suspendedUsers.contains(arr[1]))
                .collect(Collectors.groupingBy(arr -> arr[0], Collectors.counting()));

        // 5️⃣ id_list 순서대로 결과 배열 생성
        return Arrays.stream(id_list)
                .mapToInt(id -> mailCounts.getOrDefault(id, 0L).intValue())
                .toArray();
    }
}
