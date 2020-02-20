import java.util.*;
import java.io.*;
public class GenericCowProtests {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	static int BIT[];
	private static void update(int i, int x) {
		while(i <= BIT.length) {
			BIT[i-1] = (BIT[i-1]+ x)%1000000009;
			i += i & -i;
		}
	}
	private static int query(int i) {
		int ret = 0;
		while(i > 0) {
			ret = (ret + BIT[i-1])%1000000009;
			i -= i & -i;
		}
		return ret;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		int psum[] = new int[N+1];
		for(int i = 1; i <= N; ++i) {
			psum[i] = psum[i-1]+nextInt();
		}
		int sorted[] = psum.clone();
		Arrays.sort(sorted);
		int dp = 0;
		BIT = new int[N+1];
		update(Arrays.binarySearch(sorted,0)+1,1);
		for(int i = 1; i <= N; ++i) {
			dp = (query(Arrays.binarySearch(sorted,psum[i])+1));
			update(Arrays.binarySearch(sorted,psum[i])+1,dp);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(dp);
		pw.close();
	}

}
