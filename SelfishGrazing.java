import java.io.*;
import java.util.*;
public class SelfishGrazing {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		Cow[] c = new Cow[N];
		for(int i = 0; i < N; ++i) {
			c[i] = new Cow(nextInt(),nextInt());
		}
		Arrays.sort(c);
		int dp[] = new int[N+1];
		for(int i = 0; i < N; ++i) {
			int l = -1, r = N-1;
			while(l < r) {
				int m = (l+r+1)/2;
				if(c[m].r<=c[i].l)l=m;
				else r = m-1;
			}
			dp[i+1] = Math.max(dp[i], dp[l+1]+1);
		}
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		pw.println(dp[N]);
		pw.close();
	}
	private static class Cow implements Comparable<Cow>{
		int l, r;
		public Cow(int l, int r) {
			this.l = l;
			this.r = r;
		}
		@Override
		public int compareTo(Cow o) {
			return r - o.r;
		}
		
	}
}
