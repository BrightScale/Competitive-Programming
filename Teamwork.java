import java.io.*;
public class Teamwork {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), K = nextInt();
		int skill[] = new int[N];
		for(int i = 0; i < N; ++i) skill[i] = nextInt();
		buildMax(skill,N);
		int dp[] = new int[N+1];
		for(int i = 1; i <= N; ++i) {
			for(int j = 0; j < K && i-j>0; ++j) {
				dp[i] = Math.max(dp[i], dp[i-j-1]+queryMax(i-j-1,i-1)*(j+1));
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(dp[N]);
		pw.close();
	}
	//sparse table
	private static int log[];
	private static int lookupMax[][];
	private static void buildLog(int N) {
		log = new int[N+1];
		for(int i = 2; i <= N; ++i)log[i] = log[i/2]+1;
	}
	private static void buildMax(int arr[], int N) {
		int size = (int)(Math.log(N)/Math.log(2))+1;
		lookupMax = new int[N][size];
		for (int i = 0; i < N; i++) 
            lookupMax[i][0] = arr[i]; 
        for (int j = 1; (1 << j) <= N; j++) { 
            for (int i = 0; (i + (1 << j) - 1) < N; i++) { 
            	lookupMax[i][j] = Math.max(lookupMax[i][j - 1],lookupMax[i + (1 << (j - 1))][j - 1]);
            } 
        }
		buildLog(N);
	}
	private static int queryMax(int l, int r) {
		int j = log[r - l + 1];
		return Math.max(lookupMax[l][j], lookupMax[r - (1 << j) + 1][j]);
	}
}
