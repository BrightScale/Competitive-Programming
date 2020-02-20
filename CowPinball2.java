import java.io.*;
public class CowPinball2 {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int R = nextInt();
		int max = 0;
		int dp[] = new int[R];
		for(int i = 0; i < R; i++) {
			int temp[] = new int[R+1];
			for(int j = 0; j <= i; j++) {
				dp[j] = dp[j]+nextInt();
				max = Math.max(dp[j], max);
				temp[j] = Math.max(dp[j], temp[j]);
				temp[j+1] = Math.max(dp[j], temp[j+1]);
			}
			dp = temp;
		}
		System.out.println(max);
	}

}
