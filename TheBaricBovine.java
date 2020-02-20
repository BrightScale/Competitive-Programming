import java.io.*;
import java.util.Arrays;
public class TheBaricBovine {
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
		int N = nextInt(), E = nextInt();
		long M[] = new long[N+2];
		long dp[][] = new long[N+2][N+2];
		for(int i=1;i<=N;i++)M[i] = nextLong();
		for(int i = 0; i < dp.length; i++)Arrays.fill(dp[i],Integer.MAX_VALUE);
		dp[0][0]=0;
		long ps[][] = new long[N+2][N+2];
		for(int i=0;i< N+2;i++) {
			for(int j = i+1; j < N+2; j++) {
				for(int k=i+1; k < j; k++) {
					if(i==0) {
						ps[i+1][j-1]+=2 * Math.abs(M[k] - M[j]);
					}else if(j==N+1) {
						ps[i+1][j-1] += 2 * Math.abs(M[k] - M[i]);
					}else ps[i+1][j-1] += Math.abs(M[k]*2-M[j] - M[i]);
				}
			}
		}
		for(int i = 0;i <= N; i++) {
			for(int j = 0; j <= N;j++) {
				if(dp[i][j] <= E) {
					for(int k = i+1; k <= N+1; k++) {
						dp[k][j+1] = Math.min(dp[k][j+1],dp[i][j]+ps[i+1][k-1]);
					}
				}
			}
		}
		for(int i = 2;i < N+2; i++){
			if(dp[N+1][i]<=E){
				System.out.println(i-1 +" "+ dp[N+1][i]);
				System.exit(1);
			}
		}
		/*
		for(int i = 0; i < N; i++)Arrays.fill(dp[i],Integer.MAX_VALUE);
		long ps[][] = new long[N][N+1];
		for(int i = 0; i < N; i++) {
			int add = 0;
			for(int j = 0; j < i; j++) {
				add += (Math.abs(M[j]*2-M[i]-M[i]));
			}
			dp[0][i] = add;
		}
		for(int i = 0; i < N;i++) {
			for(int j = i + 1; j <= N; j++) {
				for(int k = i+1;k < j; k++) {
					if(j==N) {
						ps[i][j] += Math.abs(M[k]*2-M[i]-M[i]);
					}else {
						ps[i][j]+= Math.abs(M[k]*2-M[j]-M[i]);
					}
				}
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(dp[i][j] <= E) {
					for(int k = j+1; k <= N; k++) {
						if(k==N || M[j] < M[k])
						dp[i+1][k] = Math.min(dp[i+1][k], dp[i][j]+ps[j][k]);
					}
				}
			}
		}
		for(int i = 0; i < N; i++) {
			System.out.println(dp[i][N]);
			if(dp[i][N] <= E) {
				//System.out.println(i + " " + dp[i][N]);
				System.exit(1);
			}
		}
	}
	*/
	}
}
