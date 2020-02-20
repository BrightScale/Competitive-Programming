import java.io.*;
public class GuardMark {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static class Triple implements Comparable<Triple>{
		int h,w,s;
		public Triple(int h, int w, int s) {
			this.h = h;
			this.w = w;
			this.s = s;
		}
		@Override
		public int compareTo(Triple o) {
			return h - o.h;
		}
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), H = nextInt();
		int dp[] = new int[1<<N];
		Triple cow[] = new Triple[N];
		for(int i = 0; i < N; i++) {
			cow[i] = new Triple(nextInt(),nextInt(),nextInt());
		}
		for(int i = 0; i < N; i++) {
			dp[1<<i] = cow[i].s;
		}
		int max =  -1;
		for(int i = 0; i < dp.length; i++) {
			if(i!=0 && dp[i] == 0)continue;
			int count = i;
			int currentHeight = 0;
			int temp = i;
			for(int j = 0; j < N; j++) {
				if((temp&1)==1)currentHeight += cow[j].h;
				temp >>= 1; 
			}
			if(currentHeight >= H)max = Math.max(max, dp[i]);
			for(int j = 0; j < N; j++) {
				if((count & 1)==1) {
					count >>= 1;
					continue;
				}
				//binary search
				if(cow[j].h <= dp[i])dp[i+(1<<j)] = Math.max(dp[i+(1<<j)],Math.min(cow[j].s, dp[i] - cow[j].w));
				count >>= 1;
			}
		}
		System.out.println(max == -1 ? "Mark is too tall" : max);
	}

}
