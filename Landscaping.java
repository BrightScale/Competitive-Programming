import java.io.*;
import java.util.*;
public class Landscaping {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), X = nextInt(), Y = nextInt(), Z = nextInt();
		ArrayList<Integer> A = new ArrayList<>();
		ArrayList<Integer> B = new ArrayList<>();
		for(int i = 0; i < N; ++i) {
			int a = nextInt(), b = nextInt();
			for(int j = 0; j < a; ++j)A.add(i);
			for(int j = 0; j < b; ++j)B.add(i);
		}
		int dp[][] = new int[A.size()+1][B.size()+1];
		for(int i = 0; i <= A.size(); ++i) dp[i][0] = i*Y;
		for(int i = 0; i <= B.size(); ++i) dp[0][i] = i*X;
		for(int i = 1; i <= A.size(); ++i) {
			for(int j = 1; j <= B.size(); ++j) {
				dp[i][j] = Integer.MAX_VALUE;
				dp[i][j] = Math.min(dp[i][j], dp[i][j-1]+X);
				dp[i][j] = Math.min(dp[i][j], dp[i-1][j]+Y);
				dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]+Z*Math.abs(A.get(i-1)-B.get(j-1)));
			}
		}
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		pw.println(dp[A.size()][B.size()]);
		pw.close();
	}

}
