import java.io.*;
import java.util.*;
public class HaybaleGuessing {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static class Segment implements Comparable<Segment>{
		int l, r, A;
		public Segment(int l, int r, int A) {
			this.l = l;
			this.r = r;
			this.A = A;
		}
		@Override
		public int compareTo(Segment o) {
			return A-o.A;
		}
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), Q = nextInt();
		Segment[] seg = new Segment[Q];
		for(int i = 0; i < Q; ++i) seg[i] = new Segment(nextInt()-1,nextInt()-1,nextInt());
		int x = (int) (Math.ceil(Math.log(N) / Math.log(2))); 
		int size = 2 * (int) Math.pow(2, x) - 1; 
		int a = -1, b = Q;
		while(a<b) {
			int m = (a+b+1)/2;
			segTree = new int[size];
			lazy = new int[size];
			Segment[] t = new Segment[m];
			Arrays.fill(segTree, Integer.MAX_VALUE);
			for(int i = 0; i < m; ++i) t[i] = seg[i];
			Arrays.sort(t);
			//intersection
			ArrayList<Segment> intersected = new ArrayList<>();
			boolean invalid = false;
			int prevType = 0;
			for(int i = 0; i < m; ++i) {
				if(prevType!=t[i].A) {
					prevType = t[i].A;
					if(i!= 0 && intersected.get(intersected.size()-1).l > intersected.get(intersected.size()-1).r) {
						invalid = true;
						break;
					}
					intersected.add(new Segment(t[i].l,t[i].r,t[i].A));
				}else {
					intersected.get(intersected.size()-1).l = Math.max(
							intersected.get(intersected.size()-1).l, t[i].l);
					intersected.get(intersected.size()-1).r = Math.min(
							intersected.get(intersected.size()-1).r, t[i].r);
				}
			}
			if(!invalid) {
				for(Segment i : intersected) updateTree(i.l,i.r,0,N-1,0,i.A);
				for(Segment i : intersected) {
					if(rangeMinQuery(i.l,i.r,0,N-1,0) != i.A) {
						invalid = true;
						break;
					}
				}
			}
			if(invalid) {
				b = m-1;
			}else {
				a = m;
			}
		}
		System.out.println(a+1);
	}
	private static int segTree[];
	private static int lazy[];
	private static void updateTree(int qLow, int qHigh, int low, int high, int pos, int val) {
		//lazy propagation
		if (lazy[pos] != 0) { 
			if(segTree[pos]==Integer.MAX_VALUE)segTree[pos]=lazy[pos];
			else segTree[pos] = Math.max(segTree[pos],lazy[pos]); 
			 if(low != high) {
				 lazy[pos*2 + 1] = Math.max(lazy[pos*2 + 1],lazy[pos]); 
				 lazy[pos*2 + 2] = Math.max(lazy[pos*2 + 2],lazy[pos]); 
			 }
			 lazy[pos] = 0;
		}
		if(low > high || qLow > high || qHigh < low) { //no overlap
			return;
		}
		//full coverage
		if(high <= qHigh && low >= qLow) {
			if(segTree[pos]==Integer.MAX_VALUE)segTree[pos]=val;
			else segTree[pos] = Math.max(segTree[pos],val); 
	        if (high != low) { 
	            lazy[pos*2 + 1] = Math.max(lazy[pos*2 + 1],val); 
	            lazy[pos*2 + 2] = Math.max(lazy[pos*2 + 2],val); 
	        }
	        return;
		}
		int mid = (low+high)/2; 
		updateTree(qLow,qHigh, low, mid, 2*pos+1,val); 
		updateTree(qLow,qHigh, mid+1, high, 2*pos+2,val); 
		segTree[pos] = Math.min(segTree[2*pos+1],segTree[2*pos+2]); 
	}
	private static int rangeMinQuery(int qLow, int qHigh, int low, int high, int pos) {
		if (lazy[pos] != 0)  { 
			if(segTree[pos]==Integer.MAX_VALUE)segTree[pos]=lazy[pos];
			else segTree[pos] = Math.max(segTree[pos],lazy[pos]); 
	        if (high != low) { 
	        	lazy[pos*2 + 1] = Math.max(lazy[pos*2 + 1],lazy[pos]); 
	        	lazy[pos*2 + 2] = Math.max(lazy[pos*2 + 2],lazy[pos]); 
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
}
