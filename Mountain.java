import java.io.*;
import java.util.*;
public class Mountain {
	private static void updateTree(int qLow, int qHigh, int low, int high, int pos, int val) {
		//lazy propagation
		if (lazy[pos] != 0) { 
			 segTree[pos] += lazy[pos]; 
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
			segTree[pos]++; 
	        if (high != low) { 
	            lazy[pos*2 + 1] += 1; 
	            lazy[pos*2 + 2] += 1; 
	        }
	        return;
		}
		int mid = (low+high)/2; 
		updateTree(qLow,qHigh, low, mid, 2*pos+1,val); 
		updateTree(qLow,qHigh, mid+1, high, 2*pos+2,val); 
		segTree[pos] = Math.min(segTree[2*pos+1],segTree[2*pos+2]); 
	}
	private static int rangeMaxQuery(int qLow, int qHigh, int low, int high, int pos) {
		if (lazy[pos] != 0)  { 
			segTree[pos] += lazy[pos]; 
	        if (high != low) { 
	            lazy[pos*2 + 1] += lazy[pos];
	            lazy[pos*2 + 2] += lazy[pos];
	        } 
	        lazy[pos] = 0; 
	    }
		if(low > high || qLow > high || qHigh < low) { //no overlap
			return -1;
		}
		if(qLow <= low && qHigh >= high) { //total overlap
			return segTree[pos];
		}
		//partial overlap
		int mid = (low+high)/2;
		return Math.max(rangeMaxQuery(qLow,qHigh,low,mid,2*pos+1),
				rangeMaxQuery(qLow,qHigh,mid+1,high,2*pos+2));
	}
	private static int segTree[];
	private static double delta[];
	private static int lazy[];
	public static void main(String[]args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int N = 2;
		StringTokenizer st;
		ArrayList<Query> query = new ArrayList<>();
		//tag 
		HashSet<Integer> used = new HashSet<>();
		ArrayList<Integer> node = new ArrayList<>();
		used.add(0); used.add(n); node.add(0); node.add(n);
		while(true) {
			st = new StringTokenizer(br.readLine());
			String letter = st.nextToken();
			if(letter.equals("E"))break;
			else if(letter.equals("Q")) {
				query.add(new Query(letter, Integer.parseInt(st.nextToken())));
			}else {
				int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
				if(!used.contains(a)) {
					node.add(a);
					used.add(a);
					++N;
				}
				if(!used.contains(b)) {
					node.add(b);
					used.add(b);
					++N;
				}
				query.add(new Query(letter,a,b,Integer.parseInt(st.nextToken())));
			}
		}
		Collections.sort(node);
		int x = (int) (Math.ceil(Math.log(N) / Math.log(2))); 
		int size = 2 * (int) Math.pow(2, x) - 1; 
		segTree = new int[size];
		delta = new double[size];
		lazy = new int[size];
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < query.size(); ++i) {
			if(query.get(i).letter.equals("Q")) {
				//Ride binary search with max query o((log n)^2)
				int l = 0, r = N;
				while(l<r) {
					int m = (l+r)/2;
					if(rangeMaxQuery(0,m,0,N-1,0) <= query.get(i).h) {
						l = m+1;
					}else {
						r = m-1;
					}
				}
				pw.println(node.get(l-1));
			}else {
				//Reconfiguration delta difference and normal difference
			}
		}
		pw.close();
	}
	private static class Query{
		String letter;
		int a,b,d,h;
		public Query(String letter, int a, int b, int d) {
			this.letter = letter;
			this.a = a;
			this.b = b;
			this.d = d;
		}
		public Query(String letter, int h) {
			this.letter = letter;
			this.h = h;
		}
	}
}
