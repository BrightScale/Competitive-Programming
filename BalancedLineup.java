import java.io.*;
import java.util.*;
public class BalancedLineup {
	static int input[];
	static int segTree[];
	static int segTreeMAX[];
	private static void constructTree(int low, int high, int pos) {
		if(low == high) {
			segTree[pos] = input[low];
			return;
		}
		int mid = low + (high - low) / 2;
		constructTree(low,mid,2*pos+1);
		constructTree(mid+1,high,2*pos+2);
		segTree[pos] = Math.min(segTree[2*pos+1],segTree[2*pos+2]);
		
	}
	private static int rangeMinQuery(int qLow, int qHigh, int low, int high, int pos) {
		if(qLow <= low && qHigh >= high) { //total overlap
			return segTree[pos];
		}
		if(qLow > high || qHigh < low) { //no overlap
			return Integer.MAX_VALUE;
		}
		//partial overlap
		int mid = (low+high)/2;
		return Math.min(rangeMinQuery(qLow,qHigh,low,mid,2*pos+1),
				rangeMinQuery(qLow,qHigh,mid+1,high,2*pos+2));
	}
	private static void constructTreeMAX(int low, int high, int pos) {
		if(low == high) {
			segTreeMAX[pos] = input[low];
			return;
		}
		int mid = (low+high)/2;
		constructTreeMAX(low,mid,2*pos+1);
		constructTreeMAX(mid+1,high,2*pos+2);
		segTreeMAX[pos] = Math.max(segTreeMAX[2*pos+1],segTreeMAX[2*pos+2]);
		
	}
	private static int rangeMAXQuery(int qLow, int qHigh, int low, int high, int pos) {
		if(qLow <= low && qHigh >= high) { //total overlap
			return segTreeMAX[pos];
		}
		if(qLow > high || qHigh < low) { //no overlap
			return Integer.MIN_VALUE;
		}
		//partial overlap
		int mid = (low+high)/2;
		return Math.max(rangeMAXQuery(qLow,qHigh,low,mid,2*pos+1),
				rangeMAXQuery(qLow,qHigh,mid+1,high,2*pos+2));
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), Q = in.nextInt();
		input = new int[N];
		for(int i = 0; i < N; i++) {
			input[i] = in.nextInt();
		}
		int x = (int) (Math.ceil(Math.log(N) / Math.log(2))); 
		int size = 2 * (int) Math.pow(2, x) - 1; 
		segTree = new int[size];
		segTreeMAX = new int[size];
		for(int i = 0; i < size; i++) {
			segTree[i] = Integer.MAX_VALUE;
			segTreeMAX[i] = Integer.MIN_VALUE;
		}
		constructTree(0,N-1,0);
		constructTreeMAX(0,N-1,0);
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		for(int i = 0; i < Q; i++) {
			int a = in.nextInt()-1;
			int b = in.nextInt()-1;
			pw.println(rangeMAXQuery(a,b,0,N-1,0)-rangeMinQuery(a,b,0,N-1,0));
		}
		pw.close();
		in.close();
	}

}
