import java.util.*;
import java.io.*;
public class FuelEconomy {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), G = nextInt(), B = nextInt(), D = nextInt();
		Station[] S = new Station[N];
		for(int i = 0; i< N; ++i) {
			S[i] = new Station(nextInt(),nextInt());
		}
		Arrays.sort(S);
		int nextSmall[] = new int[N];
		Stack<Integer> stk = new Stack<>();
		for(int i = N-1; i >= 0; --i) {
			while(!stk.isEmpty()&&S[stk.peek()].y>=S[i].y)stk.pop();
			nextSmall[i] = stk.isEmpty()?-1:stk.peek();
			stk.add(i);
		}
		B-=S[0].x;
		long ans = 0;
		for(int i = 0; i < N; ++i) {
			if (B < 0) {
				ans = -1;
				break;
			}
			int need = Math.min(G, (nextSmall[i] == -1 ? D :S[nextSmall[i]].x) - S[i].x);
			if (need > B) {
				ans += (long) (need - B) * S[i].y;
				B = need;
			}
			B -= (i == N-1 ? D : S[i+1].x) - S[i].x;
		}
		if(B<0)ans = -1;
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}
	private static class Station implements Comparable<Station>{
		int x, y;
		public Station(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Station o) {
			return x - o.x;
		}
	}
}
