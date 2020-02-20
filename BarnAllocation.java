import java.util.*;
public class BarnAllocation {
	private static class Pair implements Comparable<Pair>{
		int r,l;
		public Pair(int l, int r) {
			this.l = l;
			this.r = r;
		}
		@Override
		public int compareTo(Pair o) {
			if(r == o.r) return l - o.l;
			return r-o.r;
			
		}
	}
	private static void constructTree(int low, int high, int pos) {
		if(low > high)return;
		if(low == high) {
			segTree[pos] = input[low];
			return;
		}
		int mid = low + (high - low) / 2;
		constructTree(low,mid,2*pos+1);
		constructTree(mid+1,high,2*pos+2);
		segTree[pos] = Math.min(segTree[2*pos+1],segTree[2*pos+2]);
	}
	private static void updateTree(int qLow, int qHigh, int low, int high, int pos) {
		//lazy propagation
		if (lazy[pos] != 0) { 
			 segTree[pos] -= lazy[pos]; 
			 if(low != high) {
				 lazy[pos*2 + 1] += lazy[pos]; 
		         lazy[pos*2 + 2] += lazy[pos]; 
			 }
			 lazy[pos] = 0;
		}
		if(low > high || qLow > high || qHigh < low) { //no overlap
			return;
		}
		//full coverage
		if(high <= qHigh && low >= qLow) {
			segTree[pos]--; 
	        if (high != low) { 
	            lazy[pos*2 + 1] += 1; 
	            lazy[pos*2 + 2] += 1; 
	        }
	        return;
		}
		int mid = (low+high)/2; 
		updateTree(qLow,qHigh, low, mid, 2*pos+1); 
		updateTree(qLow,qHigh, mid+1, high, 2*pos+2); 
		segTree[pos] = Math.min(segTree[2*pos+1],segTree[2*pos+2]); 
	}
	private static int rangeMinQuery(int qLow, int qHigh, int low, int high, int pos) {
		if (lazy[pos] != 0)  { 
			segTree[pos] -= lazy[pos]; 
	        if (high != low) { 
	            lazy[pos*2 + 1] += lazy[pos];
	            lazy[pos*2 + 2] += lazy[pos];
	        } 
	        lazy[pos] = 0; 
	    }
		if(low > high || qLow > high || qHigh < low) { //no overlap
			return Integer.MAX_VALUE;
		}
		if(qLow <= low && qHigh >= high) { //total overlap
			return segTree[pos];
		}
		//partial overlap
		int mid = (low+high)/2;
		return Math.min(rangeMinQuery(qLow,qHigh,low,mid,2*pos+1),
				rangeMinQuery(qLow,qHigh,mid+1,high,2*pos+2));
	}
	private static int segTree[];
	private static int lazy[];
	private static int input[];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), M = in.nextInt();
		input = new int[N];
		for(int i = 0; i < N; i++) {
			input[i] = in.nextInt();
		}
		int x = (int) (Math.ceil(Math.log(N) / Math.log(2))); 
		int size = 2 * (int) Math.pow(2, x) - 1; 
		segTree = new int[size];
		for(int i = 0; i < size; i++) {
			segTree[i] = Integer.MAX_VALUE;
		}
		lazy = new int[size];
		constructTree(0,N-1,0);
		Pair pair[] = new Pair[M];
		for(int i = 0; i < M; i++) {
			pair[i] = new Pair(in.nextInt()-1,in.nextInt()-1);
		}
		in.close();
		Arrays.sort(pair);
		int ans = 0;
		for(int i = 0; i < M; i++) {
			if(rangeMinQuery(pair[i].l,pair[i].r,0,N-1,0) > 0) {
				ans++;
				updateTree(pair[i].l,pair[i].r,0,N-1,0);
			}
		}
		System.out.println(ans);
	}

}
