import java.io.*;
import java.util.*;
public class PalindromicPaths {
	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		char[][] map = new char[N][N];
		for(int i = 0; i < N; i++) {
			map[i] = in.next().toCharArray();
		}
		in.close();
		long[][] dp = new long[N][N];
		for(int i = 0; i < N; i++) {
			dp[i][i] = 1;
		}
		for(int i = N-1; i >= 1; i--) {
			long[][] dp2 = new long[N][N];
			for(int j = 0; j < N; j++) {
				if((i-1-j) < 0) continue;
				for(int k = 0; k < N; k++) {
					if(2*N-k-i-1 >= N) continue;
					if(map[j][(i-1-j)] != map[k][2*N-i-k-1]) continue;
					dp2[j][k] += dp[j][k];
					if(j <= N) {
						dp2[j][k] += dp[j+1][k];
					}
					if(j <= N && k > 0) {
						dp2[j][k] += dp[j+1][k-1];
					}
					if(k > 0) {
						dp2[j][k] += dp[j][k-1];
					}
					dp2[j][k] %= 1000000007;
				}
			}
			dp = dp2;
		}
		System.out.println(dp[0][N-1]);
	}
}