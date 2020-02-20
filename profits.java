import java.util.*;

public class profits {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int arr[] = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		int dp[] = new int[N+1];
		int max = arr[0];
		for(int i = 1; i <= N; i++) {
			dp[i] = Math.max(dp[i-1], 0) + arr[i-1];
			max = Math.max(max, dp[i]);
			
		}
		System.out.println(max);
		in.close();
	}

}
