import java.io.*;
import java.util.*;
public class NearbyCows {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), K = nextInt();
		ArrayList<Integer> edge[] = new ArrayList[N];
		for(int i = 0; i < N; ++i) edge[i] = new ArrayList<>();
		for(int i = 1; i < N; ++i) {
			int u = nextInt()-1, v = nextInt()-1;
			edge[u].add(v); edge[v].add(u);
		}
		int dp[][] = new int[K+1][N];
		for(int i = 0; i < N; ++i) dp[1][i] = dp[0][i] = nextInt();
		for(int i = 0; i < N; ++i) for(int j : edge[i]) dp[1][i]+=dp[0][j];
		for(int i = 2; i <= K; ++i) {
			for(int j = 0; j < N; ++j) {
				for(int k : edge[j]) dp[i][j]+=dp[i-1][k];
				dp[i][j]-=(edge[j].size()-1)*dp[i-2][j];
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < N; ++i) pw.println(dp[K][i]);
		pw.close();
	}

}
