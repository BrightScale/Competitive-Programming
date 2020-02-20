import java.io.*;
public class CharmBracelet {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt();
		int dp[] = new int[M+1];
		int max = 0;
		for(int i = 0; i < N; i++) {
			int W = nextInt(), D = nextInt();
			int temp[] = new int[M+1];
			temp[W] = Math.max(dp[W],D);
			for(int j = 0; j <= M; j++) {
				temp[j] = Math.max(temp[j],dp[j]);
				if(j+W<=M && dp[j] != 0)temp[j+W] = Math.max(temp[j+W], dp[j]+D);
				max = Math.max(temp[j], max);
			}
			dp = temp;
		}
		System.out.println(max);
	}
	
}
