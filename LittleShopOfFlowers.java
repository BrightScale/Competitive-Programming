import java.io.*;
public class LittleShopOfFlowers {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String args[])throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int F = nextInt(), V = nextInt();
		int A[][] = new int[F][V];
		for(int i = 0; i < F; i++) {
			for(int j = 0; j < V; j++) {
				A[i][j] = nextInt();
			}
		}
		int dp[][] = new int[F][V];
		for(int i = 0; i < F; i++) {
			for(int j = 0; j < V; j++) {
				dp[i][j] = Integer.MIN_VALUE;
			}
		}
		for(int i = 0; i < V; i++) {
			dp[0][i] = A[0][i];
		}
		for(int i = 0; i < F-1; i++) {
			for(int j = 0; j < V; j++) {
				if(dp[i][j] == Integer.MIN_VALUE)continue;
				for(int k = j+1; k < V; k++) {
					dp[i+1][k] = Math.max(dp[i+1][k], dp[i][j]+A[i+1][k]);
				}
			}
		}
		int ans = 0;
		for(int i = 0; i < V; i++) {
			ans = Math.max(ans, dp[F-1][i]);
		}
		System.out.println(ans);
	}

}
