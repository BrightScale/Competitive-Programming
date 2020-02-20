import java.io.*;
import java.util.*;
public class RedundantPaths {
	private static ArrayList<Integer> edge[];
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int F = Integer.parseInt(st.nextToken()), R = Integer.parseInt(st.nextToken()), count = 0;
		edge = new ArrayList[F];
		for(int i = 0; i < F; ++i) edge[i] = new ArrayList<>();
		for(int i = 0; i < R; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken())-1, v = Integer.parseInt(st.nextToken())-1;
			edge[u].add(v); edge[v].add(u);
		}
		boolean visited[] = new boolean[F];
		int[] disc = new int[F], low = new int[F], parent = new int[F], base = new int[F]; in = new int[F];
		Arrays.fill(parent,-1);
		ufs = new UnionFindSet(F);
		for(int i = 0; i < F; ++i) if(!visited[i]) bridge(i,visited,disc,low,parent);
		for(int i = 0; i < F; ++i) base[ufs.find(i)]+=in[i];
		for(int i = 0; i < F; ++i) count+=base[i]==1?1:0;
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println((count+1)/2);
		pw.close();
	}
	private static int time, in[];
	private static UnionFindSet ufs;
	private static void bridge(int u, boolean visited[], int disc[], int low[], int parent[]) {
		visited[u] = true;
		disc[u] = low[u] = time; ++time;
		for(int i : edge[u]) {
			if(!visited[i]) {
				parent[i] = u;
				bridge(i,visited,disc,low,parent);
				low[u] = Math.min(low[u], low[i]);
				if (low[i] > disc[u]) {
					++in[u]; ++in[i];
				}else ufs.merge(i, u);
			}else if(i!=parent[u] )low[u]  = Math.min(low[u], disc[i]); 
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
}
