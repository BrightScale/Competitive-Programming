import java.io.*;
import java.util.*;
public class SnowBoots {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("snowboots.in")));
		int N = nextInt(), B = nextInt();
		int next[] = new int[N], prev[] = new int[N];
		for(int i = 0; i < N; ++i) {
			next[i] = i+1;
			prev[i] = i-1;
		}
		Pair[] f = new Pair[N];
		for(int i = 0; i < N; ++i) {
			f[i] = new Pair(nextInt(),i);
		}
		Arrays.sort(f);
		Pair[] b = new Pair[B];
		for(int i = 0; i < B; ++i) {
			b[i] = new Pair(nextInt(), nextInt(),i);
		}
		Arrays.sort(b);
		int maxDist = 0;
		int output[] = new int[B];
		for(int i = 0, j = 0; i < B; ++i) {
			while(f[j].a>b[i].a) {
				prev[next[f[j].b]] = prev[f[j].b];
				next[prev[f[j].b]] = next[f[j].b];
				maxDist = Math.max(maxDist, next[f[j].b]-prev[f[j].b]);
				++j;
			}
			if(b[i].b >= maxDist)output[b[i].id] = 1;
			else output[b[i].id] = 0;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
		for(int i = 0; i < B; ++i)pw.println(output[i]);
		pw.close();
	}
	private static class Pair implements Comparable<Pair>{
		int a, b, id;
		public Pair(int a, int b) {
			this.a = a;
			this.b =b;
		}
		public Pair(int a, int b, int id) {
			this.a = a;
			this.b =b;
			this.id = id;
		}
		@Override
		public int compareTo(Pair o) {
			return o.a-a; //decreasing order
		}
	}
}
