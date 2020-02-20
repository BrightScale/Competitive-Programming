import java.io.*;
import java.util.*;
public class CowRollerCoaster {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int L = nextInt(), N = nextInt(), B = nextInt();
		Component[] c = new Component[N];
		for(int i = 0; i < N; ++i) c[i] = new Component(nextInt(),nextInt(),nextInt(),nextInt());
		Arrays.sort(c);
		int[][] dp = new int[L+1][B+1];
		for(int i = 0; i <= L; ++i) for(int j = 0; j <= B; ++j) dp[i][j] = -1;
		dp[0][0] = 0;
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j <= B-c[i].c; ++j) {
				if(dp[c[i].x][j]<0) continue;
				dp[c[i].x+c[i].w][j+c[i].c] = Math.max(dp[c[i].x+c[i].w][j+c[i].c], dp[c[i].x][j]+c[i].f);
			}
		}
		int ans = -1;
		for(int i = 0; i <= B; ++i) ans = Math.max(ans,dp[L][i]);
		System.out.println(ans);
	}
	private static class Component implements Comparable<Component>{
		int x, w, f, c;
		public Component(int x, int w, int f, int c){
			this.x = x;
			this.w = w;
			this.f = f;
			this.c = c;
		}
		@Override
		public int compareTo(Component o) {
			return x - o.x;
		}
	}
}
