import java.io.*;
import java.util.*;
public class MowingTheLawn {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return (int)st.nval;
	}
	private static long nextLong() throws IOException {
		st.nextToken();
		return (long)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), K = nextInt();
		long E[] = new long[N];
		long sum = 0;
		for(int i = 0; i < N; i++) {
			E[i] = nextLong();
			sum+=E[i];
		}
		TreeMap<Long,Long> tm = new TreeMap<>();
		long dp[] = new long[N];
		for(int i = 0; i < K+1; i++) {
			dp[i] = E[i];
			if(tm.containsKey(E[i]))tm.put(E[i], tm.get(E[i])+1);
			else tm.put(E[i],1L);
		}
		for(int i = K+1; i < N; i++) {
			dp[i] = E[i]+tm.firstKey();
			if(tm.get(dp[i-K-1]) == 1)tm.remove(dp[i-K-1]);
			else tm.put(dp[i-K-1],tm.get(dp[i-K-1])-1);
			if(tm.containsKey(dp[i]))tm.put(dp[i], tm.get(dp[i])+1);
			else tm.put(dp[i],1L);
		}
		Long min = Long.MAX_VALUE;
		while(!tm.isEmpty()){
			min = Math.min(tm.firstKey(), min);
			tm.pollFirstEntry();
		}
		System.out.println(sum - min);
	}

}
