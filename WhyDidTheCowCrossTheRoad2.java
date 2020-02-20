import java.io.*;
public class WhyDidTheCowCrossTheRoad2 {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		int A[] = new int[N], B[] = new int[N];
		for(int i = 0; i < N; i++)A[i] = nextInt();
		for(int i = 0; i < N; i++)B[i] = nextInt();
		int dp[] = new int[N+1];
		for(int i = 0; i < N; i++) {
			int temp[] = new int[N+1];
			for(int j = 0; j < N; j++) {
				temp[j+1] = Math.max(dp[j+1], temp[j]);
				if(Math.abs(A[i]-B[j]) <=4)temp[j+1] = Math.max(temp[j+1], dp[j]+1);
			}
			dp = temp;
		}
		int max = 0;
		for(int i = 0; i <= N; i++)max = Math.max(dp[i],max);
		System.out.println(max);
	}

}
