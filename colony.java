import java.io.*;
import java.util.*;
public class colony {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int M = nextInt();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < M; ++i) {
			int N = nextInt();
			HashSet<Integer>[] edge = new HashSet[N];
			for(int j = 0; j < N; ++j)edge[j] = new HashSet<>();
			for(int j = 1; j < N; ++j) {
				int u = nextInt()-1, v = nextInt()-1;
				edge[u].add(v);
				edge[v].add(u);
			}
			int Q = nextInt();
			Query query[] = new Query[Q];
			for(int j = 0; j < Q; ++j) {
				query[j] = new Query(nextInt(),nextInt()-1,nextInt()-1);
				if(query[j].t==1) {  
					edge[query[j].c].remove(query[j].d);
					edge[query[j].d].remove(query[j].c);
				}
			}
			UnionFindSet ufs = new UnionFindSet(N);
			for(int j = 0; j < N; ++j) {
				for(int k : edge[j]) {
					if(ufs.find(j)!=ufs.find(k)) {
						ufs.merge(j, k);
					}
				}  
			}
			pw.println("Colony #" + (i+1) +":");
			String[] s = new String[Q];
			for(int j = Q-1; j >= 0; --j) {
				if(query[j].t==1) {
					s[j] = "Tunnel from " + (query[j].c+1) + " to " + (query[j].d+1) + " collapsed!";
					ufs.merge(query[j].c, query[j].d);
				}else {
					if(ufs.find(query[j].c)==ufs.find(query[j].d)) {
						s[j] = "Room " + (query[j].c+1) + " can reach " + (query[j].d+1);
					}else {
						s[j] = "Room " + (query[j].c+1) + " cannot reach " + (query[j].d+1);
					}
				}
			}
			for(int j = 0; j < Q; ++j) {
				pw.println(s[j]);
			}
			if(i<M-1)pw.println();
		}
		pw.close();
	}
	private static class UnionFindSet{
		int n;
		int[]parent;
		int[]size;
		public UnionFindSet(int n) {
			this.n= n;
			parent = new int[n];
			size = new int[n];
			init();
		}
		
		public void init() {
			for(int i = 0; i < n; ++i) {
				parent[i] = i;
				size[i] = 1;
			}
		}
		public int find(int x) {
			if(parent[x] == x) {
				return x;
			}
			return parent[x] = find(parent[x]);
		}
		
		public void merge(int x, int y) {
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
	private static class Query{
		int t,c,d;
		public Query(int t, int c, int d) {
			this.t = t;
			this.c = c;
			this.d = d;
		}
	}
}
