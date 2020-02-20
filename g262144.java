import java.io.*;
public class g262144 {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		int dp[][] = new int[60][N+1];
		for(int i = 0; i < 60; ++i) for(int j = 0; j < N+1; ++j) dp[i][j] = -1;
		for(int i = 0; i < N; ++i) {
			dp[nextInt()][i] = i;
		}
		for(int i = 1; i < 60; ++i) {
			for(int j = 0; j < N-1; ++j) {
				if(dp[i][j]==-1)continue;
				if(dp[i][dp[i][j]+1]!=-1) dp[i+1][j] = dp[i][dp[i][j]+1];
			}
		}
		int ans = 0;
		for(int i = 1; i < 60; ++i) {
			boolean valid = false;
			for(int j = 0; j < N; ++j) {
				if(dp[i][j]!=-1) {
					valid = true;
					break;
				}
			}
			if(valid)ans = i;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}

}
