import java.io.*;
public class bunker {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static double nextDouble() throws IOException{
		st.nextToken();
		return st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int t = nextInt();
		for(int i = 0; i < t; ++i) {
			int r = nextInt(), c = nextInt();
			double angle[] = new double[c];
			for(int j = 0; j < c; ++j)angle[j]=nextDouble();
			
		}

	}

}
