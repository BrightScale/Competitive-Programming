import java.io.*;
import java.util.*;
public class MilkingOrder {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt();
		ArrayList<Integer>order[] = new ArrayList[M];
		for(int i = 0; i < M; ++i) order[i] = new ArrayList<>();
		for(int i = 0; i < M; ++i) {
			int m = nextInt();
			for(int j = 0; j < m; ++j) order[i].add(nextInt()-1);
		}
		int l = 0, r = M;
		ArrayList<Integer>[] path = new ArrayList[N];
		int ans[] = new int[N];
		int save[] = new int[N];
		while(l<r) {
			int m = (l+r+1)/2;
			//topological sort
			int prev[] = new int[N];
			for(int i = 0; i < N; ++i)path[i] = new ArrayList<>();
			for(int i = 0; i < m; ++i) {
				for(int j = 0; j < order[i].size()-1; ++j) {
					path[order[i].get(j)].add(order[i].get(j+1));
					++prev[order[i].get(j+1)];
				}
			}
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for(int i = 0; i < N; ++i) {
				if(prev[i] == 0)pq.add(i);
			}
 			boolean valid = true;
			for(int i = 0; i < N; ++i) {
				if(pq.isEmpty()) {
					valid = false;
					break;
				}
				int e = pq.poll();
				ans[i] = e;
				for(int j : path[e]) if(--prev[j]==0)pq.add(j);
			}
			if(!valid) r = m-1;
			else {
				l = m;
				save = ans.clone();
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < N-1; ++i)pw.print(save[i]+1 + " ");
		pw.println(save[N-1]+1);
		pw.close();
	}
	
}
