import java.io.*;
import java.util.*;
import java.awt.Point;
public class SimplifyingTheFarm {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt();
		Edge edge[] = new Edge[M];
		for(int i = 0; i < M; ++i) edge[i] = new Edge(nextInt()-1,nextInt()-1,nextInt());
		Arrays.sort(edge);
		UnionFindSet ufs = new UnionFindSet(N);
		long length = 0L, sum = 1L;
		for(int i = 0; i < M;) {
			HashSet<Point> hs = new HashSet<>();//remove duplicates
			int j = i, totalAdd = 0, totalEdge = 0;
			for(; j < M && edge[i].w == edge[j].w; ++j) {
				int x = ufs.find(edge[j].u), y = ufs.find(edge[j].v);
				if(x!=y) {
					++totalEdge;
					hs.add(new Point(Math.min(x,y),Math.max(x,y)));
				}
			}
			for(; i < j; ++i) {
				if(ufs.find(edge[i].u) != ufs.find(edge[i].v)) {
					ufs.merge(edge[i].u, edge[i].v);
					++totalAdd;
					length = ((length+edge[i].w)%1000000007+1000000007)%1000000007;
				}
			}
			if(totalAdd == 1) sum = ((sum*totalEdge)%1000000007+1000000007)%1000000007;
			else if(totalAdd == 2 && totalEdge == 3) sum = ((sum*hs.size())%1000000007+1000000007)%1000000007;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(length + " " + sum);
		pw.close();
	}
	private static class Edge implements Comparable<Edge>{
		int u, v, w;
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return w - o.w;
		}
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
			if(parent[x] == x) return x;
			return parent[x] = find(parent[x]);
		}
		public void merge(int x, int y) {
			int px = find(x), py = find(y);
			if(px == py) return;
			if(size[px] < size[py]) {
				parent[px] = py;
				size[py] += size[px];
			}else{	
				parent[py] = px;
				size[px] += size[py];
			}
		}
	}
}
