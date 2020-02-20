import java.util.*;
import java.io.*;
public class ClosingTheFarm{
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
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] path = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			path[a].add(b);
			path[b].add(a);
		}
		int order[] = new int[N];
		boolean visited[] = new boolean[N];
		for(int  i = N-1; i >= 0; i--) {
			st = new StringTokenizer(in.readLine());
			order[i] = Integer.parseInt(st.nextToken())-1;
		}
		in.close();
		UnionFindSet ufs = new UnionFindSet(N);
		boolean ans[] = new boolean[N];
		for(int i = 0; i < N; i++) {
			visited[order[i]] = true;
			for(int j = 0; j < path[order[i]].size(); j++) {
				if(visited[path[order[i]].get(j)]){
					ufs.merge(order[i], path[order[i]].get(j));
				}
			}
			if(ufs.size[ufs.find(order[i])] >= i+1) {
				ans[N-1-i] = true;
			}else {
				ans[N-1-i] = false;
			}
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 0; i < N; i++) {
			bw.write(ans[i]?"YES\n":"NO\n");
		}
		bw.flush();
	}
}
