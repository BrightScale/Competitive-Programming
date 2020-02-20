import java.io.*;
public class Stringsobits {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static long nextLong() throws IOException {
		st.nextToken();
		return(long)st.nval;
	}
	private static int C[][] = new int[33][33];
	private static void binomialCoeff() { 
		for(int i = 0; i <= 32; ++i) {
	        C[0][i] = 0;
	        C[i][0] = 1;
	    }
	    for(int i = 1; i <= 32; ++i) {
	        for(int j = 1; j <= 32; ++j) {
	            C[i][j] = C[i-1][j] + C[i-1][j-1];
	        }
	    }
	} 
	public static void main(String[] args) throws IOException{
		binomialCoeff();
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), S = nextInt();
		long I = nextLong();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 1; i <= N; ++i) {
			long sum = 0;
			for(int j = 0; j <= S; ++j) {
				sum += C[N-i][j];
			}
			if (I > sum) {
	            pw.print(1);
	            S--;
	            I -= sum;
	        } else {
	            pw.print(0);
	        }
		}
		pw.close();
	}
}
