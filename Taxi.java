import java.io.*;
import java.util.*;
public class Taxi {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt();
		long ans = 0;
		int[] s = new int[N+1], t = new int[N+1];
		for(int i = 0; i < N; ++i) {
			s[i] = nextInt(); t[i] = nextInt();
			ans += Math.abs(t[i]-s[i]);
		}
		s[N] = M; t[N] = 0;
		Arrays.sort(s); Arrays.sort(t);
		for(int i = 0; i <= N; ++i) {
			ans += Math.abs(t[i]-s[i]);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}

}
