import java.io.*;
public class TheCountryFair {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		int P[] = new int[N];
		for(int i = 0; i < N; i++) {
			P[i] = nextInt();
		}
		int T[][] = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				T[i][j] = nextInt();
			}
		}
		int dp[] = new int[N];
		for (int i = 0; i < N;i++) {
	        if (T[0][i] <= P[i]) {
	            dp[i]++;
	        }
		}
	    boolean changed = true;
	    while(changed) {
	    	changed = false;
		    for (int i = 0; i < N; i++) {
		    	for (int j = 0; j < N;j++)
		    		if (i != j && dp[j] <= dp[i] && P[j]-P[i] >= T[i][j]) {
		    			dp[j] = dp[i] + 1;
		    			changed = true;
            	}
		    }
		} 
	    int max = 0;
	    for (int i = 0; i < N; i++) {
	    	max = Math.max(max, dp[i]);	
	    }
		 System.out.println(max);
	}

}
