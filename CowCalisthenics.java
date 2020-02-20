import java.util.*;
import java.io.*;
public class CowCalisthenics {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static int DFS(int pos, int parent) {
		return 0;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int V = nextInt(), S = nextInt();
		ArrayList<Integer> arr[] = new ArrayList[V];
		for(int i = 0; i < V; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < V-1; i++) {
			int a = nextInt()-1, b = nextInt()-1;
			arr[a].add(b);
			arr[b].add(a);
		}
		BufferedWriter bw = new BufferedWriter(new PrintWriter(new OutputStreamWriter(System.out)));
		bw.write(V + "\n");
		bw.close();
	}
}
