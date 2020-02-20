import java.io.*;
public class SkiLift {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String args[])throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), K = nextInt();
		int H[] = new int[N];
		for(int i = 0; i < N; i++) {
			H[i] = nextInt();
		}
		int dp[] = new int[N];
		for(int i = 1; i < N; i++) dp[i] = Integer.MAX_VALUE;
		dp[0] = 1;
		for(int i = 0; i < N; i++) {
			double max = Integer.MIN_VALUE;
			for(int j = 1; j <= K && i+j < N; j++) {
				if((double)(H[i+j]-H[i])/j>=max){
					dp[i+j] = Math.min(dp[i+j],dp[i]+1);
					max = (double)(H[i+j]-H[i])/j;
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(dp[N-1]);
		pw.close();
	}

}
