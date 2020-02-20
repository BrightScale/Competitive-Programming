import java.io.*;
import java.util.*;
public class DirectoryTraversal {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static String next() throws IOException{
		st.nextToken();
		return st.sval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("dirtraverse.in")));
		int N = nextInt();
		w = new int[N];
		parent = new int[N];
		Arrays.fill(parent, -1);
		child = new ArrayList[N];
		for(int i = 0; i < N; ++i)child[i] = new ArrayList<>();
		size = new int[N];
		for(int i = 0;i < N; ++i) {
			w[i] = next().length();
			int m = nextInt();
			if(m==0)size[i]=1;
			for(int j = 0; j < m; ++j) {
				int v = nextInt()-1;
				child[i].add(v);
				parent[v] = i;
			}
		}
		root = 0;
		while(parent[root]!=-1)++root;
		downSum = new long[N];
		add = new long[N];
		DFSdown(root);
		upSum = new long[N];
		for(int i : child[root])DFSup(i);
		long ans = Long.MAX_VALUE;
		for(int i = 0; i < N; ++i) {
			if(!child[i].isEmpty()) {
				ans = Math.min(ans, upSum[i]+downSum[i]);
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dirtraverse.out")));
		pw.println(ans);
		pw.close();
	}
	private static int root;
	private static int[] w, parent, size;
	private static long[] downSum,upSum, add;
	private static ArrayList<Integer> child[];
	private static long DFSdown(int pos) {
		//isFile
		if(child[pos].isEmpty()) {
			add[pos] = w[pos];
			return w[pos];
		}
		//directory
		for(int i : child[pos]) {
			long num = DFSdown(i);
			add[pos] += (w[pos]+1)*size[i]+num;
			downSum[pos]+=num;
			size[pos]+=size[i];
		}
		return add[pos];
	}
	private static void DFSup(int pos) {
		upSum[pos] = downSum[parent[pos]]-add[pos]+3*(size[root]-size[pos])+upSum[parent[pos]];
		for(int i : child[pos])DFSup(i);
	}
}
