import java.io.*;
public class BaleShare {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		int S[] = new int[N];
		int sum = 0;
		for(int i = 0; i < N; i++) {
			S[i] = nextInt();
			sum += S[i];
		}
		boolean valid[][] = new boolean[1000][1000];
		valid[0][0] = true;
		for(int i = 0; i < N; i++) {
			boolean validnl[][] = new boolean[1000][1000];
			for(int j = 0; j < 1000; j++) {
				for(int k = 0; k < 1000; k++) {
					if(valid[j][k]) {
						if(j+S[i] < 1000)
							validnl[j+S[i]][k] = true;
						if(k+S[i] < 1000)
							validnl[j][k+S[i]] = true;
						validnl[j][k] = true;
					}
				}
			}
			valid = validnl;
		}
		int ans = Integer.MAX_VALUE;
		for(int i = 0; i < 1000; i++) {
			for(int j = 0; j < 1000; j++) {
				if(valid[i][j]) {
					ans = Math.min(ans, Math.max(i, Math.max(j, sum-i-j)));
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}

}
