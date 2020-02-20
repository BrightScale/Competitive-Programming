import java.io.*;
import java.util.*;
public class TalentShow {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), W = nextInt();
		Cow[] cow = new Cow[N];
		for(int i = 0; i < N; ++i) cow[i] = new Cow(nextInt(),nextInt());
		Arrays.sort(cow);
		int ans = 0;
		for(int i = 0; i < N; ++i) {
			int talent = 0, weight = 0;
			for(int j = i; j < N; ++j) {
				if(weight >= W) break;
				talent += cow[j].t;
				weight += cow[j].w;
			}
			ans = Math.max(ans, (int)(((double)talent/weight)*1000));
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}
	private static class Cow implements Comparable<Cow>{
		int w, t;
		public Cow(int w, int t) {
			this.w = w;
			this.t = t;
		}
		@Override
		public int compareTo(Cow o) {
			if((double)t/w>(double)o.t/o.w)return -1;
			return 1;
		}
	}
}
