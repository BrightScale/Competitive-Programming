import java.util.*;
public class Game {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int dp[][] = new int[N+1][N+1];
		for(int i=1;i<=N;i++){
	        dp[i][i] = in.nextInt();
	    }
		in.close();
	    for(int i=1;i<=N;i++) {
		    for(int j=i+1;j<=N;j++) {
		        dp[i][j]=dp[i][j-1]+dp[j][j];
		    }
	    }
	    for(int i=1;i<N;i++) {
		    for(int j=1;j+i<=N;j++) {
		        dp[j][j+i]=dp[j][j+i]-Math.min(dp[j][j+i-1],dp[j+1][j+i]);
		    }
	    }
	    System.out.println(dp[1][N]);
	}
}
