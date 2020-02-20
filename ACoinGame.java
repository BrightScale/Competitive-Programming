import java.io.*;
public class ACoinGame {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		int arr[] = new int[N], psum[] = new int[N+1];
		for(int i = N-1; i >= 0; --i) arr[i] = nextInt();
		for(int i = 1; i <= N; ++i)psum[i] = psum[i-1]+arr[i-1];
		int dp[][] = new int[N+1][N+1];
		for(int i = 1; i <= N; ++i) {
			for(int j = 1; j <= N; ++j) {
				dp[i][j] = Math.max(dp[i][j],dp[i][j-1]);
				if(i >= 2*j-1) dp[i][j] = Math.max(dp[i][j], psum[i] - dp[i-(2*j-1)][2*j-1]);
				if(i >= 2*j) dp[i][j] = Math.max(dp[i][j], psum[i] - dp[i-2*j][2*j]);
			}
		}
		System.out.println(dp[N][1]);
	}
}