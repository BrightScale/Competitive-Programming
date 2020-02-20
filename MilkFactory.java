import java.io.*;
import java.util.*;
public class MilkFactory {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("factory.in")));
		int N = nextInt();
		ArrayList<Integer> edge[] = new ArrayList[N];
		for(int i = 0; i < N; ++i) edge[i] = new ArrayList<>();
		for(int i = 0; i < N-1; ++i) {
			int u = nextInt()-1, v = nextInt()-1;
			edge[v].add(u);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("factory.out")));
		for(int i = 0; i < N; ++i) {
			boolean visited[] = new boolean[N];	visited[i] = true;
			Queue<Integer> q = new LinkedList<>(); q.add(i);
			while(!q.isEmpty()) {
				int e = q.poll();
				for(int j : edge[e]) {
					if(visited[j])continue;
					visited[j] = true;
					q.add(j);
				}
			}
			boolean valid = true;
			for(int j = 0; j < N; ++j) {
				if(!visited[j]) {
					valid = false;
					break;
				}
			}
			if(valid) {
				pw.println(i+1);
				pw.close();
				return;
			}
		}
		pw.println(-1);
		pw.close();
	}

}
