import java.io.*;
public class VideoGamesTrouble {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String args[])throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), V = nextInt();
		int prev[] = new int[V+1];
		for(int i = 0; i < N; ++i) {
			int P = nextInt(), G = nextInt();
			int curr[] = new int[V+1];
			for(int j = P; j <= V; ++j) curr[j] = prev[j-P];
			for (int j = 0; j < G; ++j) {
				int GP = nextInt(),PV = nextInt();
				for (int k = V-GP; k >= P; --k) curr[k+GP] = Math.max(curr[k+GP],curr[k] + PV);
			}
			for (int j = P; j <= V; ++j) prev[j] = Math.max(prev[j],curr[j]);
		}
		System.out.println(prev[V]);
	}
}
