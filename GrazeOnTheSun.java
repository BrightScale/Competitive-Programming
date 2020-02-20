import java.util.*;
public class GrazeOnTheSun {
	static int[] P;
	static int[][][] dp;
 	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int L = in.nextInt();
		P = new int[N];
		for(int i = 0; i< N; i++) {
			P[i] = in.nextInt();
		}
		in.close();
		Arrays.sort(P);
		dp = new int[N][N][2];
		int closestIndex = 0;
		for(int i = 1; i < N; i++) {
			if(Math.abs(P[closestIndex] - L) > Math.abs(P[i] - L)) {
				closestIndex = i;
			}
		}
		if(P[closestIndex] - L > 0 && P[0] < L) {
			System.out.println(Math.min(solve(closestIndex,closestIndex,0,N-1) + Math.abs(P[closestIndex]-L) * N,
					solve(closestIndex-1,closestIndex-1,0,N-1) + Math.abs(P[closestIndex-1]-L) * N));
		}else if(P[closestIndex] - L < 0 && P[P.length-1] > L) {
			System.out.println(Math.min(solve(closestIndex,closestIndex,0,N-1) + Math.abs(P[closestIndex]-L) * N,
					solve(closestIndex+1,closestIndex+1,0,N-1) + Math.abs(P[closestIndex+1]-L) * N));
		}else {
			System.out.println(solve(closestIndex,closestIndex,0,N-1) + Math.abs(P[closestIndex]-L) * N);
		}
	}
	public static int solve(int l, int r, int b, int count) {
		if(l == 0 && r == P.length-1) {
			return 0;
		}
		if(l == 0) {
			dp[l][r][b] = dp[l][r][b] == 0 ? solve(l,r+1,1,count-1)+(P[r+1]-P[b == 0 ? l : r])*count : dp[l][r][b];
			//System.out.println(dp[l][r][b]+" " + b);
			return dp[l][r][b];
		}
		if(r == P.length-1) {
			dp[l][r][b] = dp[l][r][b] == 0 ? solve(l-1,r,0,count-1)+(P[b == 0 ? l : r] - P[l-1])*count : dp[l][r][b];
			//System.out.println(dp[l][r][b]+ " " + b);
			return dp[l][r][b];
		}
		dp[l][r][b] = dp[l][r][b] == 0 ? Math.min(solve(l-1,r,0,count-1)+(P[b == 0 ? l : r] - P[l-1])*count,
				solve(l,r+1,1,count-1)+(P[r+1] - P[b == 0 ? l : r])*count) : dp[l][r][b];
		//System.out.println(dp[l][r][b]);
		return dp[l][r][b];
	}
}
