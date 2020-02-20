import java.io.*;
import java.util.*;
public class Bessieball {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException {
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		int M = nextInt();
		int m1[] = new int[M], m2[] = new int[M];
		for(int i = 0; i < M; ++i) {
			m1[i] = nextInt();
			m2[i] = nextInt();
		}
		HashSet<Long> hs = new HashSet<>();
		Queue<Game> q = new LinkedList<>();
		q.add(new Game(0, 0));
		while(!q.isEmpty()) {
			Game e = q.poll();
			if(e.index==M) {
				//run undefeated
				boolean undefeated[] = new boolean[N];
				long count = e.hash;
				for(int i = 0; i < M; ++i) {
					undefeated[(count & 1)==1?m2[i]-1:m1[i]-1] = true;
					count >>= 1;
				}
				long keep = 0;
				for(int i = 0; i < N; ++i) {
					keep += undefeated[i]?0:(1<<i);
				}
				hs.add(keep);
				continue;
			}
			//#1 win
			q.add(new Game(e.hash+(1<<e.index),e.index+1));
			//#2 win
			q.add(new Game(e.hash,e.index+1));
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(hs.size());
		pw.close();
	}
	private static class Game{
		long hash;
		int index;
		public Game(long hash, int index) {
			this.hash = hash;
			this.index = index;
		}
	}
}
