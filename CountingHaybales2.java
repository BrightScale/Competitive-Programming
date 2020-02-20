import java.io.*;
import java.util.*;
public class CountingHaybales2 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), Q = Integer.parseInt(st.nextToken());
		int input[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) input[i] = Integer.parseInt(st.nextToken());
		int x = (int) (Math.ceil(Math.log(N) / Math.log(2))); 
		int size = 2 * (int) Math.pow(2, x) - 1; 
		segTreeMIN = new long[size];
		segTreeSUM = new long[size];
		lazy = new long[size];
		constructTrees(0,N-1,0,input);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < Q; ++i) {
			st = new StringTokenizer(br.readLine());
			String c = st.nextToken();
			int a = Integer.parseInt(st.nextToken())-1, b = Integer.parseInt(st.nextToken())-1;
			if(c.equals("M")) {
				pw.println(rangeMinQuery(a,b,0,N-1,0));
			}else if(c.equals("P")){
				int d = Integer.parseInt(st.nextToken());
				updateTrees(a,b,0,N-1,0,d);
			}else {
				pw.println(rangeSumQuery(a,b,0,N-1,0));
			}
		}
		pw.close();
	}
	private static long segTreeMIN[];
	private static long lazy[];
	private static long segTreeSUM[];
	private static void constructTrees(int low, int high, int pos,int[] input) {
		if(low > high)return;
		if(low == high) {
			segTreeMIN[pos] = input[low];
			segTreeSUM[pos] = input[low];
			return;
		}
		int mid = low + (high - low) / 2;
		constructTrees(low,mid,2*pos+1,input);
		constructTrees(mid+1,high,2*pos+2,input);
		segTreeMIN[pos] = Math.min(segTreeMIN[2*pos+1],segTreeMIN[2*pos+2]);
		segTreeSUM[pos] = segTreeSUM[2*pos+1]+segTreeSUM[2*pos+2]; 
	}
	private static void updateTrees(int qLow, int qHigh, int low, int high, int pos, long val) {
		//lazyMIN propagation
		if (lazy[pos] != 0) {
			 segTreeMIN[pos] += lazy[pos]; 
			 segTreeSUM[pos] += (high-low+1)*lazy[pos]; 
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
			segTreeMIN[pos] += val; 
			segTreeSUM[pos] += (high-low+1) * val; 
	        if (high != low) { 
	        	lazy[pos*2 + 1] += val; 
	        	lazy[pos*2 + 2] += val; 
	        }
	        return;
		}
		int mid = (low+high)/2; 
		updateTrees(qLow,qHigh, low, mid, 2*pos+1,val); 
		updateTrees(qLow,qHigh, mid+1, high, 2*pos+2,val); 
		segTreeMIN[pos] = Math.min(segTreeMIN[2*pos+1],segTreeMIN[2*pos+2]); 
		segTreeSUM[pos] = segTreeSUM[2*pos+1]+segTreeSUM[2*pos+2]; 
	}
	private static long rangeMinQuery(int qLow, int qHigh, int low, int high, int pos) {
		if (lazy[pos] != 0)  { 
			segTreeMIN[pos] += lazy[pos];
			segTreeSUM[pos] += (high-low+1)*lazy[pos]; 
	        if (high != low) { 
	        	lazy[pos*2 + 1] += lazy[pos];
	        	lazy[pos*2 + 2] += lazy[pos];
	        } 
	        lazy[pos] = 0; 
	    }
		if(low > high || qLow > high || qHigh < low) { //no overlap
			return Long.MAX_VALUE;
		}
		if(qLow <= low && qHigh >= high) { //total overlap
			return segTreeMIN[pos];
		}
		//partial overlap
		int mid = (low+high)/2;
		return Math.min(rangeMinQuery(qLow,qHigh,low,mid,2*pos+1),
				rangeMinQuery(qLow,qHigh,mid+1,high,2*pos+2));
	}
	private static long rangeSumQuery(int qLow, int qHigh, int low, int high, int pos) {
		if (lazy[pos] != 0)  { 
			segTreeSUM[pos] += (high-low+1)*lazy[pos]; 
			segTreeMIN[pos] += lazy[pos];
	        if (high != low) { 
	        	lazy[pos*2 + 1] += lazy[pos];
	        	lazy[pos*2 + 2] += lazy[pos];
	        } 
	        lazy[pos] = 0; 
	    }
		if(low > high || qLow > high || qHigh < low) { //no overlap
			return 0;
		}
		if(qLow <= low && qHigh >= high) { //total overlap
			return segTreeSUM[pos];
		}
		//partial overlap
		int mid = (low+high)/2;
		return rangeSumQuery(qLow,qHigh,low,mid,2*pos+1)+rangeSumQuery(qLow,qHigh,mid+1,high,2*pos+2);
	}
}
