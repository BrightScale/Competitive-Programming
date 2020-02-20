import java.io.*;
import java.util.*;
public class BackwardDigitSums {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static int N, S;
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		N = nextInt();
		S = nextInt();
		//do all permutation
		permute(new HashSet<Integer>(), new int[N]);
	}
	private static int pascal[][] = {{1},{1,1},{1,2,1},{1,3,3,1},{1,4,6,4,1},
			{1,5,10,10,5,1},{1,6,15,20,15,6,1},{1,7,21,35,35,21,7,1},
			{1,8,28,56,70,56,28,8,1},{1,9,36,84,126,126,84,36,9,1},
			{1,10,45,120,210,252,210,120,45,10,1}};
	private static void permute(HashSet<Integer> used, int[] list) {
		if(used.size()==N) {
			int sum = 0;
			for(int i = 0; i < N; i++) {
				sum += pascal[N-1][i]*list[i];
			}
			if(sum == S) {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
				for(int i = 0; i < N; i++) {
					pw.print(list[i]+" ");
				}
				pw.close();
				System.exit(1);
			}
			return;
		}
		for(int i = 1; i <= N; i++) {
			if(used.contains(i))continue;
			used.add(i);
			list[used.size()-1] = i;
			permute(used,list);
			list[used.size()-1] = 0;
			used.remove(i);
		}
	}
	
}
