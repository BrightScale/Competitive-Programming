import java.io.*;
import java.util.*;
public class CheeringUpTheCows {
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
			if(size[px] < size[py]) {
				parent[px] = py;
				size[py] += size[px];
			}else{
				parent[py] = px;
				size[px] += size[py];
			}
		}
	}
	private static class Kruskul {
		ArrayList<Edge> edgeList;
		UnionFindSet ufs;
		public Kruskul(int n, ArrayList<Edge> list) {
			this.edgeList = list;
			ufs = new UnionFindSet(n);
		}
		
		public long KrusKal() {
			ufs.init();
			long ans = 0;
			for(Edge e : edgeList) {
				if(ufs.find(e.u) != ufs.find(e.v)) {
					ufs.merge(e.u, e.v);
					ans+=e.w;
				}
			}
			return ans;
		}
	}
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static ArrayList<Edge> edge = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(),P = nextInt();
		int C[] = new int[N];
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			C[i] = nextInt();
			min = Math.min(C[i], min);
		}
		for(int i = 0; i < P; i++) {
			int a = nextInt()-1, b = nextInt()-1;
			edge.add(new Edge(a,b,C[a]+C[b]+2*nextInt()));
		}
		Collections.sort(edge);
		Kruskul kr = new Kruskul(N,edge);
		long ans = kr.KrusKal();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans+min);
		pw.close();
	}

}
