import java.io.*;
import java.util.*;

public class Main {

	static class Word implements Comparable<Word>{
		String key;
		int word;
		public Word(String key, int word) {
			this.key = key;
			this.word = word;
		}
		@Override
		public int compareTo(Word o) {
			// TODO Auto-generated method stub
			if(o.word - this.word == 0) {
				if(o.key.length() - this.key.length() == 0) {
					return this.key.compareTo(o.key);
				}
				return o.key.length() - this.key.length();
			}
			return o.word - this.word;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N,M;
	    Map<String,Integer> map = new HashMap<>();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		PriorityQueue<Word> pq = new PriorityQueue<>();
		for(int i=0;i<N;i++) {
			String line = br.readLine();
			map.put(line, map.getOrDefault(line, 0)+1);
		}
		for(String it : map.keySet()) {
			if(it.length() >= M) {
				pq.add(new Word(it,map.get(it)));	
			}
		}
		StringBuilder sb = new StringBuilder();
		while(!pq.isEmpty()) {
			sb.append(pq.poll().key).append("\n");
		}
		System.out.print(sb.toString());
	}

}
