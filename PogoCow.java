import java.io.*;
import java.util.*;
public class PogoCow{
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		Target t[] = new Target[N];
		for(int i = 0; i < N; ++i) t[i] = new Target(nextInt(),nextInt());
		Arrays.sort(t);
		int dp[][] = new int[N][N], ans = 0;
		for(int i = 0; i < N; ++i) dp[i][i] = t[i].p;
		for(int l = 0; l < 2; ++l) {
			for(int i = N-1; i >= 0; --i) {
				int k = N, val = 0;
				for(int j = 0; j <= i; ++j) {
					 while(k - 1 > i &&  t[k - 1].x - t[i].x >= t[i].x - t[j].x) {
				          --k;
				          val = Math.max(val, t[k].p + dp[k][i]);
					 }
					 dp[i][j] = val;
				}
				ans = Math.max(ans, t[i].p+val);
			}
			for(int i = 0; i < N; ++i) t[i].x = -t[i].x;
			Arrays.sort(t);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}
	private static class Target implements Comparable<Target>{
		int x, p;
		public Target(int x, int p) {
			this.x = x;
			this.p = p;
		}
		@Override
		public int compareTo(Target o) {
			return x - o.x;
		}
	}
}