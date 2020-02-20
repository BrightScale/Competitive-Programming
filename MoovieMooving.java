import java.io.*;
import java.util.Arrays;
public class MoovieMooving {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), L = nextInt();
		int[][] m = new int[N][];
		int[] d = new int[N];
		for(int i = 0; i < N; i++) {
			d[i] = nextInt();
			int C = nextInt();
			m[i] = new int[C];
			for(int j = 0; j < C; j++) {
				m[i][j] = nextInt();
			}
		}
		int dp[] = new int[(int)Math.pow(2, N)];
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < dp.length; i++) {
			if(i!=0 && dp[i] == 0)continue;
			int count = i;
			int countBit = 0;
			if(dp[i] >= L) {
				while(count > 0) {
					countBit += count & 1; 
					count >>= 1; 
				}
				min = Math.min(min, countBit);
				continue;
			}
			for(int j = 0; j < N; j++) {
				if((count & 1)==1) {
					count >>= 1;
					continue;
				}
				//binary search
				int ans = Arrays.binarySearch(m[j], dp[i]);
				if(ans < 0)ans = -(ans+2);
				if(ans == -1)continue;
				dp[i+(1<<j)] = Math.max(dp[i+(1<<j)],m[j][ans]+ d[j]);
				count >>= 1;
			}
		}
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

}
