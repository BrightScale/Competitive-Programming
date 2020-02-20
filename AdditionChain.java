import java.util.*;
public class AdditionChain {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		in.close();
		int dp[] = new int[N+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1] = 1;
		DFS(dp);
		System.out.println(max);
	}
	static int N;
	static int max = 0;
	private static void DFS(int[] dp) {
		for(int i = 1; i <= N; i++) {
			if(dp[i] == 0) continue;
			for(int j = 1; j <= i && i+j <= N; j++) {
				dp[i+j] = Math.min(dp[i+j], Math.max(dp[i],dp[j])+1);
			}
		}
	}
}
