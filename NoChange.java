import java.io.*;
import java.util.Arrays;
public class NoChange {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int K = nextInt(), N = nextInt();
		int coins[] = new int[K];
		int ps[] = new int[N+1];
		ps[0] = 0;
		for(int i = 0; i < K; i++) coins[i] = nextInt();
		int val[] = new int[N];
		for(int i = 1; i <= N; i++) {
			val[i-1] = nextInt();
			ps[i] = ps[i-1] + val[i-1];
		}
		int dp[] = new int[1<<K];
		int max = -1;
		for(int i = 0; i < dp.length; i++) {
			if(i!=0 && dp[i] == 0)continue;
			int count = i;
			if(dp[i] == N) {
				int ans = 0;
				for(int j = 0; j < K; j++) {
					if((count&1)==0)ans += coins[j]; 
					count >>= 1; 
				}
				max = Math.max(max, ans);
				continue;
			}
			for(int j = 0; j < K; j++) {
				if((count & 1)==1) {
					count >>= 1;
					continue;
				}
				//binary search
				int ans = Arrays.binarySearch(ps, coins[j]+ps[dp[i]]);
				if(ans < 0)ans = -(ans+2);
				if(ans == -1)continue;
				dp[i+(1<<j)] = Math.max(dp[i+(1<<j)],ans);
				count >>= 1;
			}
		}
		System.out.println(max);
	}

}
