import java.io.*;
public class CowLine {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static String read() throws IOException{
		st.nextToken();
		return st.sval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), K = nextInt();
		int[] fac = new int[N]; fac[N-1] = 1;
		for(int i = 2; i <= N; ++i)fac[N-i] = fac[N-i+1]*i;
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < K; ++i) {
			String c = read();
			if(c.equals("P")) {
				int n = nextInt();
				for(int j = 0; j < N; ++j) {
					
				}
			}else {
				int ans = 0;
				for(int j = 0; j < N; ++j) {
					int n = nextInt();
					
				}
			}
		}
	}

}
