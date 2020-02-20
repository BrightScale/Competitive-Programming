import java.io.*;
public class TheTriangle {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static long nextLong() throws IOException {
		st.nextToken();
		return(long)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), K = nextInt();
		long ps[][] = new long[N][];
		for(int i = 0; i < N; i++) {
			ps[i] = new long[i+2];
			ps[i][0] = 0;
			for(int j = 0; j <= i; j++) {
				ps[i][j+1] = ps[i][j] + nextLong();
			}
		}
		long div = 0;
		for(int i = 1; i <= K; i++) {
			div+=i;
		}
		long ans = Long.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j <= i; j++) {
				if(i+K <= N) {
					long count = 0;
					for(int k = 0; k < K; k++) {
						count += (ps[i+k][j+k+1]-ps[i+k][j]);
					}
					ans = (long)Math.max(ans, count/div);
				}
				
				if(j-K+1 >= 0 && j + K <= i+1) {
					long count = 0;
					for(int k = 0; k < K; k++) {
						count += (ps[i-k][j+1]-ps[i-k][j-k]);
					}
					ans = (long)Math.max(ans, count/div);
				}
				
			}
		}
		System.out.println(ans);
	}

}
