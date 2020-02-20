import java.io.*;
import java.util.*;
public class HighCardLowCardPlatinum {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		boolean valid[] = new boolean[N*2+1];
		int A[] = new int[N];
		for(int i = 0; i < N; ++i) {
			A[i] = nextInt();
			valid[A[i]] = true;
		}
		TreeSet<Integer> ts1  = new TreeSet<>(), ts2 = new TreeSet<>();
		for(int i = 1; i <= 2*N; ++i) {
			if(!valid[i]) {
				ts1.add(i);
				ts2.add(i);
			}
		}
		//prefix
		int ps[] = new int[N+1];
		for(int i = 0; i < N; ++i) {
			if(ts1.higher(A[i]) != null ) {
				ts1.remove(ts1.higher(A[i]));
				ps[i+1] = ps[i]+1;
			}else ps[i+1] = ps[i];
		}
		//suffix
		int ss[] = new int[N+1];
		for(int i = N-1; i >= 0; --i) {
			if(ts2.lower(A[i]) != null ) {
				ts2.remove(ts2.lower(A[i]));
				ss[i] = ss[i+1]+1;
			}else ss[i] = ss[i+1];
		}
		int ans = 0;
		for(int i = 0; i <= N; ++i) {
			ans = Math.max(ans, ps[i]+ss[i]);
		}
		System.out.println(ans);
	}

}
