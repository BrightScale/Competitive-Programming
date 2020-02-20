import java.util.*;

public class CowPie {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int R = in.nextInt(), C = in.nextInt();
		int val[][] = new int[R][C];
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				val[i][j] = in.nextInt();
			}
		}
		int dp[][] = new int[R+2][C+1];
		for (int j = 1; j <= C; j++) 
	        for (int i = 1; i <= j && i <= R; i++)
	            dp[i][j] = val[i-1][j-1] + Math.max(dp[i - 1][j - 1], Math.max(dp[i][j - 1], dp[i + 1][j - 1]));
		System.out.println(dp[R][C]);
		in.close();
	}

}
