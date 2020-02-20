import java.io.*;
import java.util.*;
public class FencedIn {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		long A = nextInt(), B = nextInt();
		int N = nextInt(), M = nextInt();
		long H[] = new long[N+1];
		for(int i = 0; i < N; ++i)H[i] = nextInt();
		Arrays.sort(H);
		for(int i = 0; i < N; ++i)H[i] = H[i+1]-H[i];
		H[N] = A - H[N];
		Arrays.sort(H);
		long V[] = new long[M+1];
		for(int i = 0; i < M; ++i)V[i] = nextInt();
		Arrays.sort(V);
		for(int i = 0; i < M; ++i)V[i] = V[i+1]-V[i];
		V[M] = B - V[M];
		Arrays.sort(V);
		long ans = H[0]*M+V[0]*N;
		for(int v = 1, h = 1; v < M+1 && h < N+1;) {
			if(H[h] < V[v]) {
				ans += H[h]*(M-v+1);
				++h;
			}else {
				ans += V[v]*(N-h+1);
				++v;
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}
}
