import java.io.*;
public class CowHopscotch {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), K = nextInt();
		int val[] = new int[N+2];
		long ps[] = new long[N+2];
		for(int i = 2; i <= N+1; i++) {
			val[i] = nextInt();
			ps[i] = ps[i-1] + Math.max(val[i],0);
		}
		long dp[] = new long[N+2],add[] = new long[N+2];
		int pos[] = new int[N+2];
		int l = 1, r = 0;
		long ans = 0;
		for(int i = 2; i <= N+1; i++){
			while(l <= r && add[r] <= dp[i-2]-ps[i-2]) r--;
			add[++r] = dp[i-2] - ps[i-2];
			pos[r] = i-2;
			while(l < r && pos[l] < i-K) l++;
			dp[i] = add[l] + ps[i-2] + val[i-1] + val[i];
		}
		ans = dp[1] + ps[Math.min(1+K,N+1)];
		for(int i = 2; i <= N+1; i++) ans = Math.max(dp[i] + 
				((i+K-1 <= N+1)?(ps[i+K-1] - ps[i]):(ps[N+1] - ps[i])),ans);
		System.out.println(ans);
	}
}
