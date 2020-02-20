import java.io.*;
public class TamingTheHerd {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("taming.in")));
		int N = nextInt();
		int arr[] = new int[N];
		for(int i = 0; i < N; ++i)arr[i] = nextInt();
		int save[][] = new int[N][N];
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < N-i; ++j) {
				if(j!=0)save[i][i+j] = save[i][i+j-1];
				if(arr[i+j]!=j)++save[i][i+j];
			}
		}
		int dp[][] = new int[N][N];
		for(int i = 0; i < N; ++i)for(int j = 0; j < N; ++j)dp[i][j] = Integer.MAX_VALUE;
		for(int i = 0; i < N; ++i) {
			dp[i][0] = save[0][i];
		}
		for(int i = 1; i < N; ++i) {
			for(int j = 0; j < N-1 && j < i; ++j) {
				for(int k = i; k < N; ++k) {
					dp[k][j+1] = Math.min(dp[k][j+1],dp[i-1][j]+save[i][k]);
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));
		for(int i = 0; i < N; ++i) {
			pw.println(dp[N-1][i]);
		}
		pw.close();
	}

}
