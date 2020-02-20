import java.io.*;
import java.util.*;
public class Candy {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static class Pair{
		int pos;
		int val;
		public Pair(int pos, int val) {
			this.pos = pos;
			this.val = val;
		}
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), Nopt = nextInt(), F = nextInt(), M = nextInt();
		int C[] = new int[Nopt];
		for(int i = 0; i < Nopt; i++) {
			C[i] = nextInt();
		}
		HashSet<Integer> hs = new HashSet<>();
		for(int i = 0; i < F; i++) {
			hs.add(nextInt());
		}
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(N,0));
		int visited[] = new int[N];
		int max = 0;
		for(int i = 0; i <= 500000 && !q.isEmpty(); i++) {
			Pair e = q.poll();
//			System.out.println(e.val + " " + e.pos);
			if(hs.contains(e.pos) && e.pos+M<N+M) {
				q.add(new Pair(e.pos+M,e.val));
			}
			if(i == 500000) {
				System.out.println(-1);
				return;
			}
			for(int j= 0; j < Nopt; j++) {
				if(e.pos - C[j] < 0) {
					max = Math.max(max, e.val);
					continue;
				}
				if(visited[e.pos-C[j]] < e.val+C[j]) {
					q.add(new Pair(e.pos-C[j],e.val+C[j]));
					visited[e.pos-C[j]] = e.val+C[j];
				}
				
			}
		}
		System.out.println(max);	
	}

}
