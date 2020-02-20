import java.util.*;
import java.io.*;
public class Marathon {
	private static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int segMaxTree[];
	static int segSumTree[];
	static int input[];
	static int seg[];
	private static void constructSumTree(int low, int high, int pos) {
		if(low == high) {
			segSumTree[pos] = input[low];
			return;
		}
		int mid = (low+high)/2;
		constructSumTree(low,mid,2*pos+1);
		constructSumTree(mid+1,high,2*pos+2);
		segSumTree[pos] = segSumTree[2*pos+1]+segSumTree[2*pos+2];
	}
	private static int rangeSumQuery(int qLow, int qHigh, int low, int high, int pos) {
		if(qLow <= low && qHigh >= high) { //total overlap
			return segSumTree[pos];
		}
		if(qLow > high || qHigh < low) { //no overlap
			return 0;
		}
		//partial overlap
		int mid = (low+high)/2;
		return rangeSumQuery(qLow,qHigh,low,mid,2*pos+1)+rangeSumQuery(qLow,qHigh,mid+1,high,2*pos+2);
	}
	private static void updateSumTree(int qLow, int qHigh, int low, int high, int pos, int upd) {
		//no coverage
		if(low > high || low > qHigh || high < qLow) {
			return;
		}
		if(low == high) {
			segSumTree[pos] += upd;
			return;
		}
		//full coverage
		int mid = (low+high)/2;
		updateSumTree(qLow,qHigh,low,mid,2*pos+1,upd);
		updateSumTree(qLow,qHigh,mid+1,high,2*pos+2,upd);
		segSumTree[pos] = segSumTree[2*pos+1] + segSumTree[2*pos+2];
	}
	private static void constructMaxTree(int low, int high, int pos) {
		if(low == high) {
			segMaxTree[pos] = seg[low];
			return;
		}
		int mid = (low+high)/2;
		constructMaxTree(low,mid,2*pos+1);
		constructMaxTree(mid+1,high,2*pos+2);
		segMaxTree[pos] = Math.max(segMaxTree[2*pos+1], segMaxTree[2*pos+2]);		
	}
	private static int rangeMaxQuery(int qLow, int qHigh, int low, int high, int pos) {
		if(qLow <= low && qHigh >= high) { //total overlap
			return segMaxTree[pos];
		}
		if(qLow > high || qHigh < low) { //no overlap
			return 0;
		}
		//partial overlap
		int mid = (low+high)/2;
		return Math.max(rangeMaxQuery(qLow,qHigh,low,mid,2*pos+1),
				rangeMaxQuery(qLow,qHigh,mid+1,high,2*pos+2));
	}
	private static void updateMaxTree(int qLow, int qHigh, int low, int high, int pos, int upd) {
		//no coverage
		if(low > high || low > qHigh || high < qLow) {
			return;
		}
		//on leaf
		if(low == high) {		
			segMaxTree[pos] = upd;
			return;
		}
		//partial coverage
		int mid = (low+high)/2;
		updateMaxTree(qLow,qHigh,low,mid,2*pos+1,upd);
		updateMaxTree(qLow,qHigh,mid+1,high,2*pos+2,upd);
		segMaxTree[pos] = Math.max(segMaxTree[2*pos+1],segMaxTree[2*pos+2]);
		
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), Q = in.nextInt();
		Point[] pt = new Point[N];
		for(int i = 0; i < N; i++) {
			pt[i] = new Point(in.nextInt(), in.nextInt());
		}
		int x = (int) (Math.ceil(Math.log(N-1) / Math.log(2))); 
		int size = 2 * (int) Math.pow(2, x) - 1; 
		segSumTree = new int[size];
		x = (int) (Math.ceil(Math.log(N-2) / Math.log(2))); 
		size = 2 * (int) Math.pow(2, x) - 1; 
		segMaxTree = new int[size];
		seg = new int[N-2];
		input = new int[N-1];
		for(int i = 0; i < N-1; i++) {
			input[i] = Math.abs(pt[i].x-pt[i+1].x) + Math.abs(pt[i].y-pt[i+1].y);
			//a+b-c
			if(i < N-3) {
				seg[i] = (Math.abs(pt[i].x-pt[i+1].x) + Math.abs(pt[i].y-pt[i+1].y)) 
						+ (Math.abs(pt[i+1].x-pt[i+2].x) + Math.abs(pt[i+1].y-pt[i+2].y))
						-(Math.abs(pt[i].x-pt[i+2].x) + Math.abs(pt[i].y-pt[i+2].y));
			}
		}
		constructSumTree(0,N-2,0);
		constructMaxTree(0,N-3,0);
		
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		for(int i = 0; i < Q; i++) {
			String S = in.next();
			if(S.equals("U")) {
				int I = in.nextInt()-1;
				int X = in.nextInt();
				int Y = in.nextInt();
				if(I-2>=0) {
					updateMaxTree(I-2,I-2,0,N-3,0,
							Math.abs(pt[I-2].x-pt[I-1].x) + Math.abs(pt[I-2].y-pt[I-1].y)
							+(Math.abs(pt[I-1].x-X) + Math.abs(pt[I-1].y-Y))
							-(Math.abs(pt[I-2].x-X) + Math.abs(pt[I-2].y-Y)));
				}
				if(I-1>=0 && I+1 < N) {
					updateMaxTree(I-1,I-1,0,N-3,0, Math.abs(pt[I-1].x-X) + Math.abs(pt[I-1].y-Y)
							+(Math.abs(X-pt[I+1].x) + Math.abs(Y-pt[I+1].y))
							-(Math.abs(pt[I-1].x-pt[I+1].x) + Math.abs(pt[I-1].y-pt[I+1].y)));
				}
				if(I+2 < N) {
					updateMaxTree(I,I,0,N-3,0, Math.abs(X-pt[I+1].x) + Math.abs(Y-pt[I+1].y)
							+(Math.abs(pt[I+1].x-pt[I+2].x) + Math.abs(pt[I+1].y-pt[I+2].y))
							-(Math.abs(X-pt[I+2].x) + Math.abs(Y-pt[I+2].y)));
				}
				if(I-1 >= 0) {
					updateSumTree(I-1,I-1,0,N-2,0,((Math.abs(pt[I-1].x-X) + Math.abs(pt[I-1].y-Y))-
									(Math.abs(pt[I-1].x-pt[I].x) + Math.abs(pt[I-1].y-pt[I].y))));
				}
				if(I+1 < N) {
					updateSumTree(I,I,0,N-2,0,((Math.abs(X-pt[I+1].x) + Math.abs(Y-pt[I+1].y))-
									(Math.abs(pt[I].x-pt[I+1].x) + Math.abs(pt[I].y-pt[I+1].y))));
				}
				pt[I] = new Point(X,Y);
			}else {
				int L = in.nextInt()-1;
				int R = in.nextInt()-1;
				pw.println(rangeSumQuery(L,R-1,0,N-2,0)-rangeMaxQuery(L,R-2,0,N-3,0));
			}
		}
		in.close();
		pw.close();
	}

}
