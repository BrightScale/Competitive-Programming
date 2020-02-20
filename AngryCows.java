import java.io.*;
import java.util.*;
public class AngryCows {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("angry.in")));
		int N = nextInt();
		int[] val = new int[N];
		for(int i = 0; i < N; ++i) val[i] = nextInt();
		Arrays.sort(val);
		int[] dpL = new int[N], dpR = new int[N];
		int[] posL = new int[N], posR = new int[N];
		//compute left
		posR[N-1] = val[N-1];
		for(int i = N-2; i >= 0; --i) {
			if(val[i]<posR[i+1]) {
				dpR[i] = Math.max(val[i+1]-val[i], dpR[i+1]+1);
				posR[i] = val[i+1] - dpR[i];
			}else {
				dpR[i] = dpR[i+1];
				posR[i] = posR[i+1];
			}
		}
		//compute right
		posL[0] = val[0];
		for(int i = 1; i < N; ++i) {
			if(val[i]>posL[i-1]) {
				dpL[i] = Math.max(val[i]-val[i-1], dpL[i-1]+1);
				posL[i] = val[i-1] + dpL[i];
			}else {
				dpL[i] = dpL[i-1];
				posL[i] = posL[i-1];
			}
		}
		//ans
		double ans = Integer.MAX_VALUE;
		for(int i = 0; i < N; ++i) {
			ans = Math.min(ans, Math.max(dpL[i], dpR[i]));
			if(i!=N-1) { //compute mid
				double m = (val[i]+val[i+1])/2D;
				double save = Math.max(m-val[i], val[i+1]-m);
				if(posL[i] < m) save = Math.max(save, dpL[i]+0.5);
				else save = Math.max(save, dpL[i]);
				if(posR[i+1] > m) save = Math.max(save, dpR[i+1]+1);
				else save = Math.max(save, dpR[i+1]);
				ans = Math.min(ans, save);
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		pw.printf("%.1f\n",ans);
		pw.close();
	}

}
