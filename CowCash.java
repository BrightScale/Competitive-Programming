import java.io.*;
public class CowCash {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int V = nextInt(), N = nextInt();
		int val[] = new int[V];
		for(int i = 0; i < V; i++) {
			val[i] = nextInt();
		}
		//unlimited knapsack
		long dp[] = new long[N+1];
		dp[0] = 1;
		for(int i = 0; i < V; i++) {
			for(int j = 0; j <= N; j++) {
				if(val[i] <= j)dp[j] += dp[j-val[i]];
			}
		}
		System.out.println(dp[N]);
	}

}
