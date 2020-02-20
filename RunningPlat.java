import java.util.*;
public class RunningPlat {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), M = in.nextInt();
		int D[] = new int[N];
		for(int i = 0; i < N; i++) {
			D[i] = in.nextInt();
		}
		in.close();
		int dp[] = new int[N+2];
		/*
		//start dp with first distance value
		dp[1] = D[0];
		*/
		for (int i = 0; i < N; i++){
			dp[i+1] = Math.max(dp[i+1], dp[i]);
	        //save dp[i]
	        int save = dp[i];
	        //update values
	        for (int j = 0, k = i; j < M && k < N; j++){
	            k = k + 2;
	            save += D[i+j];
	            dp[k] = Math.max(save, dp[k]);
	        }
	    }
		/*
		 for(int i = 0; i < N; i++){
		 	System.out.println(dp[i]);
		 }
		 */
		System.out.println(dp[N]);
	}

}
