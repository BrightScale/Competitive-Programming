import java.io.*;
public class TowerOfHay {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		int[] w = new int[N], sum = new int[N+1];
		for(int i = 0; i < N; ++i) {
			w[i] = nextInt();
			sum[i+1] = sum[i]+w[i];
		}
		int[] dp = new int[N+1], h = new int[N+1];
		for(int i = N; i > 0;--i) {
			for(int j = i-1; j >= 0; --j) {
				if(sum[i]-sum[j] >= dp[i]) {
					dp[j] = sum[i]-sum[j];
					h[j] = h[i]+1;
				}
			}
		}
		System.out.println(h[0]);
	}
	
}
