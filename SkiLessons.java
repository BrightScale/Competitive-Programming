import java.io.*;
import java.util.*;
public class SkiLessons {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static class Lesson implements Comparable<Lesson>{
		int M, L, A;
		public Lesson(int M, int L, int A) {
			this.M = M;
			this.L = L;
			this.A = A;
		}
		@Override
		public int compareTo(Lesson o) {
			return L-o.L;
		}
	}
	public static void main(String args[])throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int T = nextInt(), S = nextInt(), N = nextInt();
		Lesson L[] = new Lesson[S+1];
		for(int i = 0; i < S; i++) {
			L[i] = new Lesson(nextInt(),nextInt(),nextInt());
			L[i].L += L[i].M;
		}
		L[S] = new Lesson(0,0,1);
		Arrays.sort(L);
		int C[] = new int[N];
		int D[] = new int[N];
		for(int i = 0; i < N; i++) {
			C[i] = nextInt();
			D[i] = nextInt();
		}
		int dp[] = new int[T];
		//int keep = 0;
		for(int i = 0; i < S+1; i++) {
			if(L[i].L >= T) continue;
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < N; j++) {
				min = Math.min(min, C[j]<=L[i].A ? D[j] : Integer.MAX_VALUE);
			}
			//if(min == keep)continue;
			//keep = min;
			int temp[] = new int[T];
			if(L[i].M!=0) temp[L[i].L-1] = dp[L[i].M-1];
			for(int j = L[i].L-1; j < T-min; j++) {
				temp[j+min] = j < 0 ? 1 : temp[j]+1;
				dp[j+min] = Math.max(dp[j+min], temp[j+min]);
			}
		}
		System.out.println(dp[T-1]);
	}

}
