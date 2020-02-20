import java.io.*;
public class Snakes {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("snakes.in")));
		int N = nextInt(), K = nextInt();
		int[] val = new int[N+1];
		int sum = 0;
		for(int i = 1; i <= N; ++i) {
			val[i] = nextInt();
			sum+=val[i];
		}
		int[][] dp = new int[N+1][K+1];
		int m = 0;
		for(int i = 1; i <= N; ++i) {
			m = Math.max(m, val[i]);
			dp[i][0] = m*i;
			for(int j = 1; j <= K; ++j) {
				dp[i][j] = Integer.MAX_VALUE;
				int max = val[i];
				for(int k = i-1; k >= 0; --k) {
					dp[i][j] = Math.min(dp[i][j], dp[k][j-1] + max*(i-k));
					max = Math.max(max,val[k]);
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("snakes.out")));
		pw.println(dp[N][K]-sum);
		pw.close();
	}

}
