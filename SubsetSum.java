import java.io.*;
public class SubsetSum {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		int sum = 0;
		for(int i = 1; i <= N; i++) sum+=i;
		if(sum%2==1) {
			System.out.println(0);
			return;
		}
		long dp[][] = new long[N+1][sum/2+1];
		dp[0][0] = 1;
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j <= sum/2; j++) {
				dp[i][j] = dp[i-1][j]; 
				if(i <= j)dp[i][j] += dp[i-1][j-i];
			}
		}
		System.out.println(dp[N][sum/2]/2);
	}

}
