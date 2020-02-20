import java.util.*;
public class cowHopscorchSil {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int r = in.nextInt();
		int c = in.nextInt();
		in.nextInt();
		int[][] grid = new int[r][c];
	    for(int i = 0; i < r; i++) {
	      for(int j = 0; j < c; j++) {
	        grid[i][j] = in.nextInt();
	      }
	    }
	    final int MOD = 1000000007;
	    int[][] dp = new int[r][c];
	    dp[0][0] = 1;
	    for(int i = 0; i < r; i++) {
	      for(int j = 0; j < c; j++) {
	    	 for (int dx=1; i+dx<r; dx++)
	    		 for (int dy=1; j+dy<c; dy++)
					if (grid[i][j] != grid[i+dx][j+dy])
						dp[i+dx][j+dy] = (dp[i+dx][j+dy]+dp[i][j])%MOD;
	      }
	    }
	    System.out.println(dp[r-1][c-1]);
	    in.close();
	}

}
