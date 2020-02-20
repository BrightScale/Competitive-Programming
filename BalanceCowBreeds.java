import java.util.*;
public class BalanceCowBreeds {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		in.close();
		//dp[index][numebr of (]
		int dp[][] = new int[s.length()+1][s.length()+1];
		for(int i = 0; i < s.length(); i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		dp[0][0] = 1;
		dp[0][1] = 1;
		int ps[] = new int[s.length()];
		ps[0] = 1;
		for(int i = 1; i < s.length(); i++) {
			if(s.charAt(i) == '(') ps[i] = ps[i-1] + 1;
			else ps[i] = ps[i-1] -1;
		}
		//solve
		for(int i = 0; i < s.length(); i++) {
			for(int j = 0; j < s.length(); j++) {
				if ((i == 0 && j == 0) || (i == 0 && j == 1)) continue;
				if (s.charAt(i) == '(') {
					dp[i][j] = 0;
					if(i > 0 && j > 0) dp[i][j] = dp[i-1][j-1] % 2012;
					if(i > 0) dp[i][j] += dp[i-1][j] % 2012;
				} else {
					dp[i][j] = dp[i-1][j+1] % 2012;
					if(ps[i]+1-j>0) dp[i][j] = (dp[i][j]+dp[i-1][j])%2012;
				}
			}
		}
		//output the last index when the number of ( is 0
		System.out.println(dp[s.length()-1][0] % 2012);
	}

}
