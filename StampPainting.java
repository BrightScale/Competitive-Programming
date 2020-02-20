import java.io.*;
public class StampPainting {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt(), K = nextInt();
		long dp[] = new long[N+1];
		for(int i = 1; i < K; i++) {
			dp[i] = ((M*dp[i-1] + M)%1000000007+1000000007)%1000000007;
		}
		for(int i = K; i <= N;i++) {
			dp[i] = ((M*dp[i-1] %1000000007)+1000000007 - ((M-1)*dp[i-K]))%1000000007;
		}
		long ans = 1;
		for(int i = 1; i <= N;i++) {
			ans = (M*ans)%1000000007;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(((ans - dp[N] + dp[N-1])%1000000007+1000000007)%1000000007);
		pw.close();
	}
}
