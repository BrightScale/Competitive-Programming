import java.util.*;
public class BessieWeight {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int H = in.nextInt(), N = in.nextInt();
		int W[] = new int[N];
		for(int i = 0; i < N; i++) {
			W[i] = in.nextInt();
		}
		in.close();
		Arrays.sort(W);
		boolean dp[] = new boolean[H+1];
		dp[0] = true;
		int max = 0;
		for(int i = 0; i < N; i++) {
			boolean temp[] = new boolean[H+1];
			for(int j = 0; j < H; j++) {
				if(j + W[i] > H) break;
				if(dp[j]) temp[j] = true;
				if(dp[j]) {
					temp[j+W[i]] = true;
					max = Math.max(max, j+W[i]);
				}
			}
			dp = temp;
		}
		System.out.println(max);
	}

}
