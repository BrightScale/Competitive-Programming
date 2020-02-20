import java.io.*;
import java.util.*;
public class CowDecathlon {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static class Triple implements Comparable<Triple>{
		int k,p, a;
		public Triple(int k,int p, int a) {
			this.k = k;
			this.p = p;
			this.a = a;
		}
		@Override
		public int compareTo(Triple o) {
			//if(o.p == -1)return k-o.k;
			if(k==o.k)return p-o.p;
			return k-o.k;
		}
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), B = nextInt();
		Triple[] bonus = new Triple[B];
		for(int i = 0; i < B; i++) {
			bonus[i] = new Triple(nextInt(),nextInt(),nextInt());
		}
		int arr[][] = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				arr[i][j] = nextInt();
			}
		}
		Arrays.sort(bonus);
		int dp[] = new int[1<<N];
		int max = 0;
		for(int i = 0; i < dp.length; i++) {
			if(i!=0 && dp[i] == 0)continue;
			int count = i;
			int countBit = 0;
			int temp = i;
			for(int j = 0; j < N; j++) {
				countBit += (temp&1);
				temp >>= 1; 
			}
			//binary search for bonus
			int ans = Arrays.binarySearch(bonus, new Triple(countBit,-1,0));
			if(ans < 0)ans = -(ans+1);
			if(ans >= 0) {
				for(;ans<B && bonus[ans].k == countBit && dp[i] >= bonus[ans].p;ans++)
					dp[i] += bonus[ans].a;
			}
			if(countBit == N) max = Math.max(max, dp[i]);
			for(int j = 0; j < N; j++) {
				if((count & 1)==1) {
					count >>= 1;
					continue;
				}
				dp[i+(1<<j)] = Math.max(dp[i+(1<<j)],dp[i]+arr[j][countBit]);
				count >>= 1;
			}
		}
		System.out.println(max);
	}

}
