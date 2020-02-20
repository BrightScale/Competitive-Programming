import java.io.*;
import java.util.*;
public class TreeDecoration {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static ArrayList<Integer> [] child;
	private static int[] parent, min, time;;
	private static long[] ans, count;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		parent = new int[N]; min = new int[N]; time = new int[N]; 
		ans = new long[N]; count = new long[N];
		child = new ArrayList[N];
		for(int i = 0; i < N; ++i)child[i] = new ArrayList<>();
		for(int i = 0; i < N; ++i) {
			int p = nextInt()-1, c = nextInt(), t = nextInt();
			parent[i] = p; min[i] = c; time[i] = t;
			if(i!=0)child[p].add(i);
		}
		DFS(0);
		System.out.println(ans[0]);
	}
	private static int DFS(int idx) {
		int m = time[idx];
		for(int i : child[idx]) m = Math.min(m, DFS(i));
		if(count[idx] < min[idx]) {
			ans[idx] += m * (min[idx]-count[idx]);
			count[idx] = min[idx];
		}
		if(idx!=0) {
			ans[parent[idx]]+=ans[idx];
			count[parent[idx]] += count[idx];
		}
		return m;
	}
}
