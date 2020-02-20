import java.io.*;
import java.util.*;
public class MooTube {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("mootube.in")));
		int N = nextInt(), Q = nextInt();
		Path[] p = new Path[N-1];
		for(int i = 0; i < N-1; ++i) {
			p[i] = new Path(nextInt()-1,nextInt()-1,nextInt());
		}
		Arrays.sort(p);
		Question[] q = new Question[Q];
		for(int i = 0; i < Q; ++i) {
			q[i] = new Question(nextInt(),nextInt()-1,i);
		}
		Arrays.sort(q);
		parent = new int[N];
		size = new int[N];
		for(int i = 0; i < N; ++i) {
			parent[i] = i;
			size[i] = 1;
		}
		int ans[] = new int[Q];
		int b = N-2;
		for(int i = Q-1; i >=0; --i) {
			while(b >= 0 && p[b].r >= q[i].k) {
				merge(p[b].p,p[b].q);
				b--;
			}
			ans[q[i].n] = size[find(q[i].v)]-1;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		for(int i : ans)pw.println(i);
		pw.close();
	}
	private static class Path implements Comparable<Path>{
		int p, q, r;
		public Path(int p, int q, int r) {
			this.p = p;
			this.q = q;
			this.r = r;
		}
		@Override
		public int compareTo(Path o) {
			return r-o.r;
		}
	}
	private static class Question implements Comparable<Question>{
		int k,v,n;
		public Question(int k, int v, int n) {
			this.k = k;
			this.v = v;
			this.n = n;
		}
		@Override
		public int compareTo(Question o) {
			return k-o.k;
		}
	}
	private static int[] parent;
	private static int[] size;
	public static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	public static void merge(int x, int y) {
		int px = find(x), py = find(y);
		if(size[px] < size[py]) {
			parent[px] = py;
			size[py] += size[px];
		}else{
			parent[py] = px;
			size[px] += size[py];
		}
	}
}
