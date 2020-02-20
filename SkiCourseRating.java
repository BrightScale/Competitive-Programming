import java.awt.Point;
import java.io.*;
import java.util.*;
public class SkiCourseRating {
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
		int[]post;
		int[]size;
		
		public UnionFindSet(int n) {
			this.n= n;
			parent = new int[n];
			post = new int[n];
			size = new int[n];
			init();
		}
		
		public void init() {
			for(int i = 0; i < n; i ++) {
				parent[i] = i;
				size[i] = 1;
				post[i] = start.contains(i) ? 1:0;
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
				post[py] += post[px];
				if(size[py] >= T) {
					found = true;
				}
			}else{	
				parent[py] = px;
				size[px] += size[py];
				post[px] += post[py];
				if(size[px] >= T) {
					found = true;
				}
			}
		}
	}
	static boolean found = false;
	private static class Kruskul {
		ArrayList<Edge> edgeList;
		UnionFindSet ufs;
		public Kruskul(int n, ArrayList<Edge> list) {
			this.edgeList = list;
			ufs = new UnionFindSet(n);
		}
		
		public long KrusKal() {
			ufs.init();
			long sum = 0;
			for(Edge e : edgeList) {
				if(ufs.find(e.u) != ufs.find(e.v)) {
					ufs.merge(e.u, e.v);
				}
				if(found) {
					found = false;
					sum += ((long)e.w*(long)ufs.post[ufs.find(e.u)]);
					ufs.post[ufs.find(e.u)] = 0;
				}
			}
			return sum;
		}
	}
	static int M;
	static int N;
	static int T;
	static int map[][];
	static HashSet<Integer> start = new HashSet<>();
	private static boolean isValid(Point target) {
		if(target.x<0) return false;
		if(target.y<0) return false;
		if(target.x>=N)return false;
		if(target.y>=M)return false;
		return true;
	}
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		M = nextInt();
		N = nextInt();
		T = nextInt();
		map = new int[M][N];
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = nextInt();
			}
		}
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(nextInt() == 1)
					start.add(i*N+j);
			}
		}
		ArrayList<Edge> list= new ArrayList<Edge>();
		int[] dx = {1,0};
		int[] dy = {0,1};
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < dx.length; k++) {
					if(isValid(new Point(j+dx[k],i+dy[k]))) {
						list.add(new Edge(i*N+j,(i+dy[k])*N+(j+dx[k]),Math.abs(map[i][j] - map[i+dy[k]][j+dx[k]])));
					}
				}
			}
		}
		Collections.sort(list);
		Kruskul kr = new Kruskul(M*N,list);
		long sum = kr.KrusKal();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(sum);
		pw.close();
	}

}
