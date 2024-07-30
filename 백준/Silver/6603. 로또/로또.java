import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	/**
	 * 최대 49C6 임
	 * 최소 7C6이고
	 * 조합으로 6개를 골라야함
	 * 1번째 for문은 nC6 임
	 * 입력값을 받고 map 에 n-1만큼의 value를 넣고
	 * foreach를 돌면서 
	 * @param args
	 */
	/*
	 * 리스트 리스트를 쓴다 넣고 빼고 한다 재귀 돌린다
	 * 
	 */
	static final int N = 6;
	static List<List<Integer>> loto = new ArrayList<>();
	static int[] input;
    static int inputSize;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;

        // 0 이 들어올때까지 반복
		while(!(line = br.readLine()).equals("0")){
			String[] parts = line.trim().split(" ");
            inputSize = Integer.parseInt(parts[0]);
			input = new int[inputSize];

			for(int i=0;i<inputSize;i++){
				input[i] = Integer.parseInt(parts[i+1]);
			}

            loto.clear();
			combination(new ArrayList<>(), 0);


            // 출력문 형식
			for (List<Integer> comb : loto) {
                for (int i = 0; i < comb.size(); i++) {
                    System.out.print(comb.get(i));
                    if (i < comb.size() - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
            System.out.println();

		}
		

	}

	static void combination(List<Integer> current, int start){
		// 재귀 마지막 현재 리스트에 담겨 있는게 N(6)개가 되면 이중리스트에 추가
		if(current.size() == N){
			loto.add(new ArrayList<>(current));
			return;
		}
        // start 포인트를 받고
		// 입력받은 배열의 크기까지 for문 돌림
		for(int i=start;i<inputSize;i++){
            // index지점에 있는 input배열 값을 1중 리스트에 넣어줌
			current.add(input[i]);
            // 다음 재귀함수를 호출! 기존에 담고있는 리스트를 그대로 넣어주고
            // index를 한칸 더해줌
			combination(current, i+1); 
            // 재귀를 탈출하면 가장 마지막에 있는값을 빼줌
			current.remove(current.size() - 1);
		}
		
	}

}

/**
 * 첫번째 재귀
 * combination([1], 1)
 * 두번째 재귀
 * combination([1, 2], 2)
 * 세번째 재귀
 * combination([1, 2, 3], 3)
 * 네번째 재귀
 * combination([1, 2, 3, 4], 4)
 * 다섯번째 재귀
 * combination([1, 2, 3, 4, 5], 5)
 * 여섯번째 재귀
 * combination([1, 2, 3, 4, 5, 6], 6)
 * add List([1, 2, 3, 4, 5, 6])
 * => 재귀 탈출 후 이중 리스트에 삽입
 * => 리스트의 사이즈 - 1 즉 마지막에 있는 리스트 값을 빼줌
 * => 다음루프 수행
 * 1 2 3 4 5
 * 다시 함수로 돌아와서 루프 수행
 * combination([1, 2, 3, 4, 5, 7], 7)
 * add List([1, 2, 3, 4, 5, 7])
 * 돌아가기
 * 
 * combination([1, 2, 3, 4], 5) 이시점으로 돌아오게됨
 * 다시 for문 수행
 * combination([1, 2, 3, 4, 6], 6)
 * combination([1, 2, 3, 4, 6, 7], 7)
 * ...
 * 이런식으로 수행
 */