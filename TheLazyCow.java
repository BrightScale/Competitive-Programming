import java.io.*;
public class TheLazyCow {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), K = nextInt();
		int grid[][]= new int[N][N];
		for(int i = 0; i < N; ++i) {
			for(int j = 0;j < N; ++j) {
				grid[i][j] = nextInt();
			}
		}
		int sum[] = new int[N];
		//set up first
		for(int i = 0; i <= K && i < N; ++i) {
			for(int j = 0; j <= K-i && j < N; ++j) {
				sum[0]+=grid[i][j];
			}
		}
		int max = sum[0];
		//set up first line
		for(int i = 1; i < N; ++i) {
			sum[i] = sum[i-1];
			//delete
			for(int j = 0; j <= K && i-j-1 >= 0; ++j) {
				if(K-j<N)sum[i]-=grid[K-j][i-j-1];
			}
			//add
			for(int j = 0; j <= K && i+j < N; ++j) {
				if(K-j<N)sum[i]+=grid[K-j][i+j];
			}
			max = Math.max(max,sum[i]);
		}
		//go down the column
		for(int i = 1; i < N; ++i) {
			for(int j = 0; j < N; ++j) {
				//delete
				if(i-K-1 >= 0) sum[j] -= grid[i-K-1][j];
				for(int k = 0; k < K && i-k-1 >= 0; ++k) {
					if(j+K-k<N)sum[j] -= grid[i-k-1][j+K-k];
					if(j-K+k>=0)sum[j] -= grid[i-k-1][j-K+k];
				}
				//add
				if(i+K<N)sum[j] += grid[i+K][j];
				for(int k = 0; k < K && i+k < N; ++k) {
					if(j+K-k<N)sum[j] += grid[i+k][j+K-k];
					if(j-K+k>=0)sum[j] += grid[i+k][j-K+k];
				}
				max = Math.max(max, sum[j]);
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(max);
		pw.close();
	}

}
