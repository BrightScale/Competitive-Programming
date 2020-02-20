import java.util.*;
public class g248 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int  N = in.nextInt();
		int dp[][] = new int[N][N];
		int max = 0;
		for(int i = 0; i < N; i++)
			dp[i][i] = in.nextInt();
		in.close();
		for(int i = 1; i < N; i++)
			for(int j = 0; j < N-i; j++) 
				for(int k = 0; k < i; k++) 
					if(dp[j][j+k] == dp[j+k+1][j+i]) 
						dp[j][j+i] = Math.max(dp[j][j+i], dp[j][j+k] + 1);
					
		for(int i = 0; i < N; i++) 
			for(int j = 0; j < N; j++) 
				max = Math.max(dp[i][j], max);
		System.out.println(max);
	}
}