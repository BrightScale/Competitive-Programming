import java.io.*;
import java.util.*;
public class HLDalgorithm {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		HLD hld = new HLD(N);
		for(int i = 0; i < N-1; ++i) hld.addEdge(nextInt()-1, nextInt()-1);
		hld.initTree();
		hld.update(1, 5);
		System.out.println(hld.query(0,2));
		hld.update(2, 4);
		System.out.println(hld.query(2,2));
	}
	private static class HLD{
		private static int N; //number of node on the tree
		private static ArrayList<Integer> edge[]; //edge list
		private static int[] depth, size, val; //tree values
		@SuppressWarnings("unchecked")
		public HLD(int numNode) { //initial lists
			N = numNode;
			depth = new int[N]; size = new int[N];
			chainHead = new int[N]; 
			nodeToSegTree = new int[N]; segTreeToNode = new int[N];
			Arrays.fill(chainHead, -1);
			edge = new ArrayList[N];
			for(int i = 0; i < N; ++i) edge[i] = new ArrayList<>();
			lca = new int[17][N];
			val = new int[N];
			int x = (int) (Math.ceil(Math.log(N) / Math.log(2))); 
			int size = 2 * (int) Math.pow(2, x) - 1; 
			segTree = new int[size];
		}
		public void addEdge(int u, int v) { //add bidirectional edge
			edge[u].add(v); edge[v].add(u);
		}
		public void initTree() { //no previous value
			DFS(0,-1);
			initLCA();
			hld(0,0);
		}
		/*
		public void initTree(int[] value) { //have previous value
			DFS(0,-1);
			initLCA();
			hld(0,0);
			val = value;
			buildST(0, N-1, 0);
		}
		*/
		public int query(int u, int v) {
			int anc = LCA(u,v);
			return Math.max(val[anc],Math.max(pathQuery(u,anc), pathQuery(v,anc)));
		}
		public void update(int idx, int value) {
			val[idx] = value;
			updateST(nodeToSegTree[idx],0,N-1,0,value);
		}
		private static int pathQuery(int child, int par) {
			int ret = 0;
			while(child != par) {
				if(chainHead[child] == child) {
					//light edge
					ret = Math.max(ret,val[child]);
					child = lca[0][child];
				}else if(depth[chainHead[child]] > depth[par]){
					ret = Math.max(ret, queryST(nodeToSegTree[chainHead[child]],nodeToSegTree[child],0,N-1,0));
					child = lca[0][chainHead[child]];
				}else {
					ret = Math.max(ret,queryST(nodeToSegTree[par]+1,nodeToSegTree[child],0,N-1,0));
					break;
				}
			}
			return ret;
		}
		private static int lca[][];
		private static void initLCA(){
			for(int i = 1; i < 17; ++i) {
			    for(int j = 0; j < N; ++j) {
			      lca[i][j] = lca[i-1][lca[i-1][j]];
			    }
			  }
		}
		private static int LCA(int u, int v) {
			if(depth[u]<depth[v]) { //swap
				int t = u; u = v; v = t;
			}
			for(int i = 16; i >= 0; --i) {
				if(depth[u] - (1<<i) >= depth[v]) {
					u = lca[i][u];
				}
			}
			for(int i = 16; i >= 0; --i) {
				if(lca[i][u] != lca[i][v]) {
					u = lca[i][u]; v = lca[i][v];
				}
			}
			if(u!=v) {
				u = lca[0][u]; v = lca[0][v];
			}
			return u;
		}
		private static void DFS(int curr, int prev){
			++size[curr];
			for(int i : edge[curr]) {
				if(i == prev)continue;
				lca[0][i] = curr; 
				depth[i] = depth[curr]+1; 
				DFS(i,curr);
				size[curr]+=size[i];
			}
		}
		private static int segTreeIdxCount = 0;
		private static int[] chainHead, nodeToSegTree, segTreeToNode;
		private static void hld(int curr, int top) {
			segTreeToNode[segTreeIdxCount] = curr;
			nodeToSegTree[curr] = segTreeIdxCount; ++segTreeIdxCount;
			chainHead[curr]=top;
		    int schild = -1, maxSize = -1;
		    for(int i : edge[curr]) {         
		    	if(i != lca[0][curr] && size[i] > maxSize) {
		            maxSize = size[i];
		            schild = i;
		        }
		    }
		    if(schild >= 0) hld(schild,top);
		    for(int i : edge[curr]) {
		        if(i != lca[0][curr] && i != schild) {
		            hld(i,i);
		        }
		    }
		}
		private static int segTree[];
		/*
		private static void buildST(int low, int high, int pos) { //segment tree (max query)
			if(low == high) {
				segTree[pos] = val[segTreeToNode[low]];
				return;
			}
			int mid = (low+high)/2;
			buildST(low,mid,2*pos+1); buildST(mid+1,high,2*pos+2);
			segTree[pos] = Math.max(segTree[2*pos+1], segTree[2*pos+2]);
		}
		*/
		private static void updateST(int point, int low, int high, int pos, int val) { //point update
			if(low > high || point > high || point < low) return; //no overlap
			if(point == low && point == high) { //total overlap
				segTree[pos] = val;
				return;
			}
			int mid = (low+high)/2; //partial overlap
			updateST(point,low,mid,2*pos+1,val); updateST(point,mid+1,high,2*pos+2,val);
			segTree[pos] = Math.max(segTree[2*pos+1], segTree[2*pos+2]);
		}
		private static int queryST(int qLow, int qHigh, int low, int high, int pos) { //range query
			if(qLow > high || qHigh < low) return 0; //no coverage
			if(qLow <= low && qHigh >= high) return segTree[pos]; //total overlap
			int mid = (low+high)/2;//partial overlap
			return Math.max(queryST(qLow,qHigh,low,mid,2*pos+1),queryST(qLow,qHigh,mid+1,high,2*pos+2));
		}
	}
}
