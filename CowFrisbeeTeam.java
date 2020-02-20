import java.io.*;
public class CowFrisbeeTeam {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), F = nextInt();
		int arr[] = new int[N];
		for(int i = 0; i < N; ++i) {
			arr[i] = nextInt();
		}
		long dp[] = new long[F];
		dp[0] = 1L;
		for(int i = 0; i < N; ++i) {
			long temp[] = new long[F];
			for(int j = 0; j < F; ++j) {
				temp[j] = ((temp[j]+dp[j])%100000000+100000000)%100000000;
				temp[(j+arr[i])%F] = ((dp[j]+temp[(j+arr[i])%F])%100000000+100000000)%100000000;
			}
			dp = temp;
		}
		System.out.println(dp[0]-1);
	}

}
