import java.util.*;
public class Moocast {
	static class UnionFindSet{
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
			for(int i = 0; i < n; i ++) {
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
			if(px == py) {
				return;
			}
			if(size[px] < size[py]) {
				parent[px] = py;
				size[py] += size[px];
			}else{	
				parent[py] = px;
				size[px] += size[py];
			}
		}
	}
	static class Kruskul {
		List<Edge> edgeList;
		int n;
		UnionFindSet ufs;
		public Kruskul(int n, List<Edge> list) {
			this.n = n;
			this.edgeList = list;
			ufs = new UnionFindSet(n);
		}
		
		public int KrusKal() {
			ufs.init();
			int last = 0;
			for(Edge e : edgeList) {
				if(ufs.find(e.u) != ufs.find(e.v)) {
					ufs.merge(e.u, e.v);
					last = e.w;
				}
			}
			return last;
		}
	}
	static class Edge implements Comparable<Edge>{
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
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int x[] = new int[N];
		int y[] = new int[N];
 		for(int i = 0; i < N; i++) {
			x[i] = in.nextInt();
			y[i] = in.nextInt();
		}
 		in.close();
		List<Edge> edge = new ArrayList<Edge>();
		for(int i = 0; i < N-1; i++) {
			for(int j = i +1; j < N; j++){
				edge.add(new Edge(i,j,(int)Math.pow(Math.abs(x[i]-x[j]), 2) + (int)Math.pow(Math.abs(y[i]-y[j]), 2)));
			}
		}
		Collections.sort(edge);
		Kruskul ans = new Kruskul(N,edge);
		System.out.println(ans.KrusKal());
	}

}
