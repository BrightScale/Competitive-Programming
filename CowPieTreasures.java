import java.io.*;
public class CowPieTreasures {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int R = nextInt(), C = nextInt();
		int map[][] = new int[C][R];
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				map[j][i] = nextInt();
			}
		}
		int dp[][] = new int[C][R];
		for(int i = 0; i < C-1; i++) {
			for(int j = 0; j < R; j++) {
				if(i==0 && j > 0 || (i!=0 && dp[i][j] == 0))break;
				if(j > 0)dp[i+1][j-1] = Math.max(dp[i+1][j-1], dp[i][j] + map[i][j]);
				dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j] + map[i][j]);
				if(j < R-1)dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j] + map[i][j]);
			}
		}
		System.out.println(dp[C-1][R-1] + map[C-1][R-1]);
	}

}
