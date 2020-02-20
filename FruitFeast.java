import java.util.*;

public class FruitFeast {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt(), A = in.nextInt(), B = in.nextInt();
		in.close();
		boolean dp[][] = new boolean[2][T+1];
		dp[0][0] = true;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < T+1; j++) {
				if(!dp[i][j]) {
					continue;
				}
				if(j+A <= T) {
					dp[i][j+A] = true;
				}
				if(j+B <= T) {
					dp[i][j+B] = true;
				}
				if(i == 0) {
					dp[i+1][j/2] = true;
				}
			}
		}
		int max = T;
		while(!dp[1][max]) {
			max--;
		}
		System.out.println(max);
	}

}
