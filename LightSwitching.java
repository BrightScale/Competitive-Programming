import java.io.*;
public class LightSwitching {
	static int segTree[];
	static int lazy[];
	private static int rangeSumQuery(int qLow, int qHigh, int low, int high, int pos) {
		//lazy propagation
		if (lazy[pos] != 0)  { 
			segTree[pos] = (high-low+1)-segTree[pos]; 
	        if (high != low) { 
	            lazy[pos*2 + 1] ^= lazy[pos];
	            lazy[pos*2 + 2] ^= lazy[pos];
	        } 
	        lazy[pos] = 0; 
	    }
		if(qLow <= low && qHigh >= high) { //total overlap
			return segTree[pos];
		}
		if(qLow > high || qHigh < low) { //no overlap
			return 0;
		}
		//partial overlap
		int mid = (low+high)/2;
		return rangeSumQuery(qLow,qHigh,low,mid,2*pos+1)+
				rangeSumQuery(qLow,qHigh,mid+1,high,2*pos+2);
	}
	private static void updateST(int qLow, int qHigh, int low, int high, int pos) {
		//lazy propagation
		if(lazy[pos] != 0) {
			 segTree[pos] = (high-low+1)-segTree[pos]; 
			 if(low != high) {
				 lazy[pos*2 + 1]  ^= lazy[pos]; 
		         lazy[pos*2 + 2]  ^= lazy[pos]; 
			 }
			 lazy[pos] = 0;
		}
		//no coverage
		if(low > high || low > qHigh || high < qLow) {
			return;
		}
		//full coverage
		if(high <= qHigh && low >= qLow) {
			segTree[pos] = (high-low+1)-segTree[pos]; 
	        if (high != low) { 
	            lazy[pos*2 + 1] ^= 1; 
	            lazy[pos*2 + 2] ^= 1; 
	        }
	        return;
		}
		int mid = (low+high)/2; 
		updateST(qLow,qHigh, low, mid, 2*pos+1); 
		updateST(qLow,qHigh, mid+1, high, 2*pos+2); 
		segTree[pos] = segTree[2*pos+1] + segTree[2*pos+2]; 
	}
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt();
		int x = (int) (Math.ceil(Math.log(N) / Math.log(2))); 
		int size = 2 * (int) Math.pow(2, x) - 1; 
		segTree = new int[size];
		lazy = new int[size];
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < M; i++) {
			if(nextInt() == 0) {
				updateST(nextInt()-1,nextInt()-1,0,N-1,0);
			}else {
				pw.println(rangeSumQuery(nextInt()-1,nextInt()-1,0,N-1,0));
			}
		}
		pw.close();
	}

}
