import java.io.*;
import java.util.*;
public class BessieBuffet{
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), E = nextInt();
		int[] Q = new int[N];
		Grass[] grass = new Grass[N];
		int[][] path = new int[N][];
		for(int i = 0; i < N; ++i) {
			int q = nextInt(), d = nextInt();
			Q[i] = q;
			grass[i] = new Grass(i,q);
			path[i] = new int[d];
			for(int j = 0; j < d; ++j) path[i][j] = nextInt();
		}
		Arrays.sort(grass);
		int dp[] = new int[N];
		
	}
	private static class Grass implements Comparable<Grass>{
		int id, e;
		public Grass(int id, int e) {
			this.id = id;
			this.e = e;
		}
		@Override
		public int compareTo(Grass o) {
			return o.e - e;
		}
	}
}