import java.io.*;
public class even {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int T = nextInt();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 1; i <= T; ++i) {
			int x = nextInt();
			pw.println("Pokemon " + i + ": " + ((long)(Integer.highestOneBit(x)<<1L)-x));
		}
		pw.close();
	}
}
