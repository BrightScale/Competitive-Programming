import java.util.*;
public class CheapestPalindrome {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), M = in.nextInt();
		String s = in.next();
		HashMap<Character,Integer> insert = new HashMap<>();
		HashMap<Character,Integer> delete = new HashMap<>();
		for(int i = 0; i < N; i++) {
			String c = in.next();
			insert.put(c.charAt(0),in.nextInt());
			delete.put(c.charAt(0), in.nextInt());
		}
		in.close();
		int dp[][] = new int[M][M];
		for(int i = 1; i < M; i++) {
			for(int j = 0; j+i < M; j++) {
				if(s.charAt(j) == s.charAt(j+i)) {
					dp[j][j+i] = dp[j+1][j+i-1];
				}else {
					dp[j][j+i] = Math.min(dp[j+1][j+i] + Math.min(delete.get(s.charAt(j)),insert.get(s.charAt(j))),
							dp[j][j+i-1] + Math.min(delete.get(s.charAt(j+i)),insert.get(s.charAt(j+i))));
				}
			}
		}
		System.out.println(dp[0][M-1]);
	}

}
