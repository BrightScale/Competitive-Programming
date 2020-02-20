import java.io.*;
import java.util.*;
public class GoldilocksAndTheNCows {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), X = nextInt(), Y = nextInt(), Z = nextInt();
		Status s[] = new Status[N*2];
		for(int i = 0; i < N; ++i) {
			s[i] = new Status(nextInt(),true);
			s[i+N] = new Status(nextInt(),false);
		}
		Arrays.sort(s);
		int c = N, m = 0, h = 0, ans = c*X+m*Y+h*Z;
		for(int i = 0; i < 2*N; ++i) {
			if(s[i].start) {--c;++m;}
			else {--m;++h;}
			ans = Math.max(ans,c*X+m*Y+h*Z);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}
	private static class Status implements Comparable<Status>{
		int x; boolean start;
		public Status(int x, boolean start) {
			this.x = x;
			this.start = start;
		}
		@Override
		public int compareTo(Status o) {
			return x - o.x;
		}
	}
}
