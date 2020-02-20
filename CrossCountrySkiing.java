import java.io.*;
import java.util.*;
public class CrossCountrySkiing {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int M = nextInt(), N = nextInt();
		int grid[][] = new int[M][N];
		int id[][] = new int[M][N];
		for(int i = 0; i < M; ++i) {
			for(int j = 0; j < N; ++j) {
				grid[i][j] = nextInt();
				id[i][j] = i*N+j;
			}
		}
		ArrayList<Integer> d = new ArrayList<>();
		for(int i = 0; i < M; ++i) {
			for(int j = 0; j < N; ++j) {
				if(nextInt() == 1) {
					d.add(id[i][j]);
				}
			}
		}
		ArrayList<Edge> edge = new ArrayList<>();
		int dx[] = {0,1};
		int dy[] = {1,0};
		for(int i = 0; i < M; ++i) {
			for(int j = 0; j < N; ++j) {
				for(int k = 0; k < 2; ++k) {
					if(j+dx[k] >= 0 && j+dx[k] < N && i+dy[k] >= 0 && i+dy[k] < M) {
						edge.add(new Edge(id[i][j],id[i+dy[k]][j+dx[k]],Math.abs(grid[i][j]-grid[i+dy[k]][j+dx[k]])));
					}
				}
			}
		}
		Collections.sort(edge);
		UnionFindSet ufs = new UnionFindSet(N*M);
		HashMap<Integer,Integer> hm = new HashMap<>();
		for(int i : d) hm.put(i,1);
		int ans = 0;
		for(Edge i : edge) {
			int a = ufs.find(i.id1), b = ufs.find(i.id2);
			if(a != b) {
				if(ufs.merge(i.id1, i.id2)) {
					if(hm.containsKey(a)) {
						if(hm.containsKey(b))hm.put(b, hm.get(b)+hm.get(a));
						else hm.put(b,hm.get(a));
						hm.remove(a);
					}
				}else {
					if(hm.containsKey(b)) {
						if(hm.containsKey(a))hm.put(a, hm.get(b)+hm.get(a));
						else hm.put(a,hm.get(b));
						hm.remove(b);
					}
				}
				if(hm.size() == 1) {
					ans = i.w;
					break;
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
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
		
		public boolean merge(int x, int y) {
			int px = find(x), py = find(y);
			if(size[px] < size[py]) {
				parent[px] = py;
				size[py] += size[px];
				return true;
			}else{
				parent[py] = px;
				size[px] += size[py];
				return false;
			}
		}
	}
	private static class Edge implements Comparable<Edge>{
		int id1, id2, w;
		public Edge(int id1, int id2, int w) {
			this.id1 = id1;
			this.id2 = id2;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return w - o.w;
		}
	}
}