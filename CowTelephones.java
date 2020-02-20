import java.io.*;
import java.util.*;
public class CowTelephones {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static ArrayList<Integer> edge[];
	private static int K;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(); K = nextInt();
		edge = new ArrayList[N];
		for(int i = 0; i < N; ++i)edge[i] = new ArrayList<>();
		for(int i = 1; i < N; ++i) {
			int u = nextInt()-1, v = nextInt()-1;
			edge[u].add(v); edge[v].add(u);
		}
		DFS(-1,0);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(pairs);
		pw.close();
	}
	private static int pairs = 0;
	private static int DFS(int par, int cur) {
		int unpairs = 0;
		if(edge[cur].size()==1)++unpairs;
		for(int i : edge[cur]) {
			if(i!=par) unpairs+=DFS(cur,i);
		}
		pairs += Math.min(K, unpairs>>1);
		if(unpairs>>1>=K)return 0;
		return unpairs&1;
	}
}
