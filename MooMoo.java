import java.util.*;
public class MooMoo {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), B = in.nextInt();
		int V[] = new int[B];
		for(int i = 0; i < B; i++) {
			V[i] = in.nextInt();
		}
		//Arrays.sort(V);
		int map[] = new int[N+1];
		for(int i = 0; i < N; i++) {
			map[i] = in.nextInt();
		}
		in.close();
		int position = 0;
		int sum = 0;
		for(int i = 1; i < N+1; i++) {
			if(map[i] !=  map[i-1]-1) {
				int K = i-1-position + map[i-1];
				//knapsack
				int dp[] = new int[K+1];
				for(int j = 1; j < K+1; j++) {
					dp[j] = Integer.MAX_VALUE;
				}
				for(int j = 0; j < B; j++) {
					for(int k = 0; k < K+1; k++) {
						if(dp[k] != Integer.MAX_VALUE) {
							if(k+V[j] < K+1) {
								dp[k+V[j]] = Math.min(dp[k]+1,dp[k+V[j]]);
							}
						}
					}
				}
				if(dp[K] == Integer.MAX_VALUE) {
					sum = -1;
					break;
				}else sum += dp[K];
				position = i;
			}
		}
		System.out.println(sum);
	}

}
