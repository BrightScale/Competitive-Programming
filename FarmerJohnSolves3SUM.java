import java.io.*;
import java.util.*;
public class FarmerJohnSolves3SUM {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("threesum.in")));
		int N = nextInt(), Q = nextInt();
		int num[] = new int[N];
		for(int i = 0; i < N; ++i) num[i] = nextInt();
		long psum[][] = new long[N][N];
		//compute psum with hashmap sliding window
		HashMap<Integer,Integer> hm;
		for(int i = 0; i < N-2; ++i) {
			hm = new HashMap<>();
			for(int j = i+2; j < N; ++j) {
				psum[i][j] = psum[i][j-1];
				if(!hm.containsKey(num[j-1]))hm.put(num[j-1], 1);
				else hm.put(num[j-1], hm.get(num[j-1])+1);
				if(hm.containsKey(-(num[i]+num[j])))psum[i][j]+=(long)hm.get(-(num[i]+num[j]));
			}
		}
		for(int i = 0; i < N; ++i) {
			for(int j = N-1; j > 0; --j) {
				psum[j-1][i] += psum[j][i];
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("threesum.out")));
		for(int i = 0; i < Q; ++i) pw.println(psum[nextInt()-1][nextInt()-1]);
		pw.close();
	}

}
