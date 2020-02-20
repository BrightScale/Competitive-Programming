import java.io.*;
public class CowCoupons {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static long nextLong() throws IOException {
		st.nextToken();
		return(long)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), K = nextInt();
		long M = nextLong();
		long P[] = new long[N];
		long C[] = new long[N];
		for(int i = 0; i  < N; i++) {
			P[i] = nextLong();
			C[i] = nextLong();
		}
		int max = 0;
		
	}

}
