import java.io.*;
public class OptimalMilking {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static void constructTree(int input[], int low, int high, int pos) {
		if(low == high) {
			segTreeB[pos] = input[low];
			return;
		}
		int mid = (low+high)/2;
		constructTree(input, low,mid,2*pos+1);
		constructTree(input, mid+1,high,2*pos+2);
		segTreeL[pos] = Math.max(segTreeL[2*pos+1]+segTreeL[2*pos+2], 
				segTreeN[2*pos+1]+segTreeB[2*pos+2]);
		segTreeR[pos] = Math.max(segTreeR[2*pos+1]+segTreeR[2*pos+2],
				segTreeB[2*pos+1]+segTreeN[2*pos+2]);
		segTreeB[pos] = Math.max(segTreeR[2*pos+1]+segTreeL[2*pos+2],
				Math.max(segTreeB[2*pos+1]+segTreeL[2*pos+2],
						segTreeR[2*pos+1]+segTreeB[2*pos+2]));
		segTreeN[pos] = Math.max(segTreeL[2*pos+1]+segTreeN[2*pos+2],
				Math.max(segTreeN[2*pos+1]+segTreeR[2*pos+2],
						segTreeN[2*pos+1]+segTreeN[2*pos+2]));
	}
	private static void updateTree(int point, int low, int high, int pos, int val) {
		if(low > high || point > high || point < low) return;
		if(point == low && point == high) { //total overlap
			segTreeB[pos] = val;
			return;
		}
		//partial overlap
		int mid = (low+high)/2;
		updateTree(point,low,mid,2*pos+1, val);
		updateTree(point,mid+1,high,2*pos+2, val);
		segTreeL[pos] = Math.max(segTreeL[2*pos+1]+segTreeL[2*pos+2], 
				segTreeN[2*pos+1]+segTreeB[2*pos+2]);
		segTreeR[pos] = Math.max(segTreeR[2*pos+1]+segTreeR[2*pos+2],
				segTreeB[2*pos+1]+segTreeN[2*pos+2]);
		segTreeB[pos] = Math.max(segTreeR[2*pos+1]+segTreeL[2*pos+2],
				Math.max(segTreeB[2*pos+1]+segTreeL[2*pos+2],
						segTreeR[2*pos+1]+segTreeB[2*pos+2]));
		segTreeN[pos] = Math.max(segTreeL[2*pos+1]+segTreeN[2*pos+2],
				Math.max(segTreeN[2*pos+1]+segTreeR[2*pos+2],
						segTreeN[2*pos+1]+segTreeN[2*pos+2]));
	}
	private static long segTreeL[];//not touching left
	private static long segTreeR[];//not touching right
	private static long segTreeB[];//touching both
	private static long segTreeN[];//not touching both
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), D = nextInt();
		int input[] = new int[N];
		for(int i = 0; i < N; ++i) {
			input[i] = nextInt();
		}
		int x = (int) (Math.ceil(Math.log(N) / Math.log(2))); 
		int size = 2 * (int) Math.pow(2, x) - 1; 
		segTreeL = new long[size];
		segTreeR = new long[size];
		segTreeB = new long[size];
		segTreeN = new long[size];
		constructTree(input,0,N-1,0);
		long ans = 0;
		for(int i = 0; i < D; ++i) {
			int u = nextInt()-1, v = nextInt();
			updateTree(u,0,N-1,0,v);
			ans += Math.max(segTreeL[0], Math.max(segTreeR[0], Math.max(segTreeB[0], segTreeN[0])));
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}

}
