import java.io.*;
public class TastyEating {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), W = nextInt(), L = nextInt();
		int mat[][] = new int[N+1][N+1];
		for(int i = 0; i < W; ++i) {
			mat[nextInt()][nextInt()] = 1;
		}
		for(int i = 1; i <= N; ++i) {
			 for(int j = 1; j <= N; ++j) {
				 mat[i][j] += mat[i-1][j]+mat[i][j-1]-mat[i-1][j-1];
			 }
		}
		int l = 1, r = N+1;
		int max = 0;
		while(l<=r) {
			int m = (l+r)/2;
			boolean valid = false;
			for(int i = 1; i <= N-m; ++i) {
				for(int j = 1; j <= N-m; ++j) {
					int sum = mat[i-1][j-1]+mat[i+m][j+m]-mat[i-1][j+m]-mat[i+m][j-1];
					if(sum <= L) {
						valid = true;
						break;
					}
				}
				if(valid)break;
			}
			if(valid) {
				max = Math.max(max, m+1);
				l=m+1;
			}
			else r=m-1;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(max*max);
		pw.close();
	}

}
