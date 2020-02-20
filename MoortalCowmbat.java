import java.io.*;
import java.util.*;
public class MoortalCowmbat {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		int arr[] = new int[N];
		String s = br.readLine();
		for(int i = 0; i < N; ++i) {
			arr[i] = s.charAt(i)-'a';
		}
		int cost[][] = new int[M][M];
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; ++j) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		//floyd warshall get better path
		int dist[][] = new int[M][M];
		for(int i = 0; i < M; ++i) for(int j = 0; j < M; ++j)dist[i][j] = cost[i][j];
		for(int k = 0; k < M; ++k) {
			for(int i = 0; i < M; ++i) {
				for(int j = 0; j < M; ++j) {
					if (dist[i][k] + dist[k][j] < dist[i][j]) 
                        dist[i][j] = dist[i][k] + dist[k][j]; 
				}
			}
		}
 		int psum[][] = new int[M][N+1];
		for(int i = 0; i < M; ++i) {
			for(int j = 0; j < N; ++j) {
				psum[i][j+1] = dist[arr[j]][i]+psum[i][j];
			}
		}
		int dp[][] = new int[N][M];
		for(int i = 0; i < N; ++i) for(int j = 0; j < M; ++j)dp[i][j] = Integer.MAX_VALUE;
		for(int i = 0 ; i < M; ++i) {
			dp[K-1][i] = psum[i][K];
		}
		for(int i = K; i < N; ++i) {
			for(int j = 0; j < M; ++j) {
				dp[i][j] = Math.min(dp[i][j], dp[i-1][j]+dist[arr[i]][j]);
				//change color
				if(i < N-K+1) {
					for(int k = 0; k < M; ++k) {
						dp[i+K-1][k] = Math.min(dp[i+K-1][k], dp[i-1][j] + psum[k][i+K]-psum[k][i]);
					}
				}
			}
		}
		int ans = Integer.MAX_VALUE;
		for(int i = 0; i < M; ++i) {
			ans = Math.min(ans, dp[N-1][i]);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	} 
}
