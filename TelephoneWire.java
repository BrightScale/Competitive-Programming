import java.io.*;
public class TelephoneWire {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), C = nextInt();
		int h[] = new int[N];
		int max = 0;
		for(int i = 0; i < N; i++) {
			h[i] = nextInt();
			max = Math.max(h[i], max);
		}
		int dp[] = new int[max+1];
		for(int i = h[0]; i <= max; i++) {
			dp[i] = (i-h[0])*(i-h[0]);
		}
		for(int i = 1; i < N; i++) {
			int temp[] = new int[max+1];
			for(int j = h[i]; j <= max; j++) {
				temp[j] = Integer.MAX_VALUE;
				for(int k = h[i-1]; k <= max; k++) {
					temp[j] = Math.min(temp[j],C*Math.abs((j-k)) + (dp[k] + (j-h[i])*(j-h[i])));
				}
			}
			dp = temp;
		}
		int ans = Integer.MAX_VALUE;
		for(int i = h[N-1]; i <= max; i++) {
			ans = Math.min(ans, dp[i]);
		}
		System.out.println(ans);
	}

}
