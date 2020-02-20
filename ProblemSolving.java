import java.io.*;
public class ProblemSolving {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int M = nextInt(), P = nextInt();
		int[] A = new int[P], B = new int[P];
		for(int i = 0; i < P; ++i) {
			A[i] = nextInt(); B[i] = nextInt();
		}
		int[] sumA = new int[P+1], sumB = new int[P+1];
		for(int i = 0; i < P; ++i) {
			sumA[i+1] = sumA[i]+A[i];
			sumB[i+1] = sumB[i]+B[i];
		}
		int dp[][] = new int[P+1][P+1];
		for(int i = 0; i <= P; ++i)for(int j = 0; j <= P; ++j)dp[i][j] = Integer.MAX_VALUE;
		dp[0][0] = 1;
		for(int i = 0; i < P; ++i) {
			for(int j = 0; j < P; ++j) {
				if(dp[i][j]==Integer.MAX_VALUE)continue;
				//doing k-i amount of task in one month
				for(int k = i+1; k <= P; ++k) {
					if(M-(sumB[i]-sumB[i-j])-(sumA[k]-sumA[i])<0 || M-(sumB[k]-sumB[i])<0)break; 
					dp[i+(k-i)][k-i] = Math.min(dp[i+(k-i)][k-i], dp[i][j]+1);
				}
				//work two months after (after completing B)
				dp[i][0] = Math.min(dp[i][0], dp[i][j]+1);
			}
			for(int k = i+1; k <= P; ++k) {
				if(M-(sumA[k]-sumA[i])<0|| M-(sumB[k]-sumB[i])<0)break; 
				dp[i+(k-i)][k-i] = Math.min(dp[i+(k-i)][k-i], dp[i][0]+1);
			}
		}
		int ans = Integer.MAX_VALUE;
		for(int i = 0; i <= P; ++i)ans = Math.min(ans, dp[P][i]);
		System.out.println(ans+1);
	}

}
