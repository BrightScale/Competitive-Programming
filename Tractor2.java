import java.util.*;
public class Tractor2 {
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
				if(size[py] >= (N*N)/2) {
					found = true;
				}
			}else{	
				parent[py] = px;
				size[px] += size[py];
				if(size[px] >= (N*N)/2) {
					found = true;
				}
			}
		}
	}
	static boolean found = false;
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
			for(Edge e : edgeList) {
				if(ufs.find(e.u) != ufs.find(e.v)) {
					ufs.merge(e.u, e.v);
				}
				if(found == true) {
					return e.w;
				}
			}
			return 0;
		}
	}
	static int N;
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
		N = in.nextInt();
		List<Edge> edge = new ArrayList<Edge>();
		int arr[][] = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				arr[i][j] = in.nextInt();
				if(j != 0) {
					edge.add(new Edge(i*N+j,i*N+j-1,Math.abs(arr[i][j]-arr[i][j-1])));
					//edge.add(new Edge(i*N+j-1,i*N+j,Math.abs(arr[i][j]-arr[i][j-1])));
				}
				if(i != 0) {
					edge.add(new Edge(i*N+j,(i-1)*N+j,Math.abs(arr[i][j]-arr[i-1][j])));
					//edge.add(new Edge((i-1)*N+j,i*N+j,Math.abs(arr[i][j]-arr[i-1][j])));
				}
			}
		}
		Collections.sort(edge);
		Kruskul ans = new Kruskul(N*N,edge);
		System.out.println(ans.KrusKal());
		in.close();
	}

}
