
import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;
	static char[] arr;
	static int[] acgt;
	static int[] dna;
	static int result = 0;
	// A C G T
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = st.nextToken().toCharArray();
		dna = new int[4];
		acgt = new int[4];
		st = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<4;i++) {
			acgt[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0;i<M;i++) {
			addDNA(arr[i]);
		}
		if(isCheck()) result++;
		int cnt = 0;
		for(int i=M;i<N;i++) {
			removeDNA(arr[cnt++]);
			addDNA(arr[i]);
			if(isCheck()) result++;
		}
		System.out.println(result);
	}
	private static void addDNA(char input) {
		switch (input) {
			case 'A':
				dna[0]++;
				break;
			case 'C':
				dna[1]++;
				break;
			case 'G':
				dna[2]++;
				break;
			case 'T':
				dna[3]++;
				break;
		}
	}
	private static void removeDNA(char input) {
		switch (input) {
			case 'A':
				dna[0]--;
				break;
			case 'C':
				dna[1]--;
				break;
			case 'G':
				dna[2]--;
				break;
			case 'T':
				dna[3]--;
				break;
		}
	}
	private static boolean isCheck() {
		for(int i=0;i<4;i++) {
			if(dna[i]<acgt[i]) return false;
		}
		return true;
	}
}
