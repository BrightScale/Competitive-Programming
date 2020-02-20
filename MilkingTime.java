import java.io.*;
import java.util.Arrays;
public class MilkingTime {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static class Time implements Comparable<Time>{
		int s,e,f;
		public Time(int s, int e, int f) {
			this.s = s;
			this.e = e;
			this.f = f;
		}
		@Override
		public int compareTo(Time o) {
			return e-o.e;
		}
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		nextInt();
		int M = nextInt(), R = nextInt();
		Time T[] = new Time[M];
		for(int i = 0; i < M; i++) {
			int s = nextInt(), e = nextInt(), f = nextInt();
			T[i] = new Time(s,e,f);
		}
		Arrays.sort(T);
		int dp[] = new int[M];
		int max = 0;
		for(int i = 0; i < M; i++) {
			dp[i] = T[i].f;
			for(int j = 0; j < i; j++) {
				if(T[j].e <= T[i].s-R)dp[i] = Math.max(dp[i], dp[j]+T[i].f);
			}
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}

}
