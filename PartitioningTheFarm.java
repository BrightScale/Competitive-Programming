import java.io.*;
public class PartitioningTheFarm {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static void DFS(int val, int pos, int curr) {
		if(pos==N-1) {
			solve(val,curr);
			return;
		}
		if(curr<K)DFS(val+(1<<pos),pos+1,curr+1);
		DFS(val,pos+1,curr);
	}
	private static void solve(int val, int curr) {
		//binary search on sum
		boolean row[] = new boolean[N-1];//if there is a fence between i and i+1
		for(int i = 0; i < N-1; ++i) {
			row[i] = (val&1)==1;
			val>>=1;
		}
		int l = 0, r = 225000;
		while(l<r) {
			int m = (l+r)/2;
			int sum[] = new int[curr+1];
			int count = 0,start = 0;
			for(int i = 0; i < N; ++i) {
				int c = 0, max = 0;
				sum[c] += grid[i][0];
				for(int j = 0; j < N-1; ++j) {
					if(row[j]) {
						max = Math.max(max, sum[c]);
						++c;
					}
					sum[c]+=grid[i][j+1];
				}
				max = Math.max(max, sum[c]);
				if(max > m) {
					if(start==i) {
						count = 16;
						break;
					}else {
						start = i;
						--i;
						sum = new int[curr+1];
						++count;
					}
				}
			}
			if(count > K-curr)l = m+1;
			else r = m;
		}
		dp[curr] = Math.min(dp[curr], r);
	}
	private static int N, K;
	private static int dp[];
	private static int grid[][];
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		N = nextInt(); K = nextInt();
		grid = new int[N][N];
		for(int i = 0; i < N; i++) for(int j = 0; j < N; ++j) grid[i][j] = nextInt();
		dp = new int[N];
		for(int i = 0; i < N; ++i) dp[i] = Integer.MAX_VALUE;
		DFS(0,0,0);
		int ans = Integer.MAX_VALUE;
		for(int i = Math.max(0, K-N+1); i < N; ++i) ans = Math.min(ans, dp[i]);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}

}
