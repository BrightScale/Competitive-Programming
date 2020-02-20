import java.util.*;
import java.io.*;
public class TheFewestCoins {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), T = nextInt();
		int V[] = new int[N];
		for(int i = 0; i < N; i++)V[i] = nextInt();
		int C[] = new int[N];
		for(int i = 0; i < N; i++)C[i] = nextInt();
		Arrays.sort(V);
		//unlimited knapsack farmer john
		int dp[] = new int[1000000]; 
		for(int i = 1; i < 1000000; i++)dp[i] = Integer.MAX_VALUE;
		for(int j = 0; j < N; j++){
			for(int i = 0; i < 1000000; i++){
				if(i + V[j] >= 1000000)break;
				if(dp[i]==Integer.MAX_VALUE)continue;
				else dp[i+V[j]] = Math.min(dp[i]+1,Math.min(dp[i+V[j]], dp[i] + 1)); 
			} 
		}
		//unlimited knapsack storekeeper
		for(int j = 0; j < N; j++){ 
			for(int i = 0; i < 1000000; i++){
				if(i - V[j] < 0)continue;
				if(dp[i] == Integer.MAX_VALUE)continue;
				else dp[i-V[j]] = Math.min(dp[i]+1,Math.min(dp[i-V[j]], dp[i] + 1)); 
			} 
		}
		System.out.println(dp[T] == Integer.MAX_VALUE ? -1 : dp[T]);
	}

}
