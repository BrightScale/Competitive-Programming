import java.io.*;
public class CornFields {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int M = nextInt(), N = nextInt();
		int field[][] = new int[M+1][N+1];
		int f[] = new int[M+1];
		for(int i = 1; i <= M; ++i) {
			for(int j = 1; j <= N; ++j) {
				field[i][j] = nextInt();
				f[i] = (f[i]<<1) + field[i][j];
			}
		}
		boolean g[] = new boolean[1<<N];
		for(int i = 0; i < (1<<N); ++i) g[i] = ((i&(i<<1))==0) && ((i&(i>>1))==0);
		int dp[][] = new int[M+1][1<<N];
		dp[0][0] = 1;
		for(int i = 1; i <= M; ++i) {
			for(int j = 0; j < (1<<N); ++j) {
				if(g[j]&&((j & f[i]) == j)) {
					for(int k = 0; k < (1<<N); ++k) {
						if ((k & j) == 0) dp[i][j] = (dp[i][j] + dp[i-1][k]) % 100000000;
					}
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < (1<<N); ++i) {
	        ans += dp[M][i];
			ans %= 100000000;
		}
		System.out.println(ans);
	}

}
