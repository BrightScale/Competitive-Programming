import java.io.*;
import java.util.*;
public class SightseeingCows {
	private static StreamTokenizer st;
	private static int nextInt()throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int L = nextInt(), P = nextInt();
		int fun[] = new int[L];
		for(int i = 0; i < L; ++i) fun[i] = nextInt();
		ArrayList<Edge> edge[] = new ArrayList[L];
		for(int i = 0; i < L; ++i)edge[i] = new ArrayList<>();
		for(int i = 0; i < P; ++i) edge[nextInt()-1].add(new Edge(nextInt()-1,nextInt()));
		
	}
	private static class Edge{
		int v, w;
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}
