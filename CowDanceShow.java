import java.io.*;
import java.util.*;
public class CowDanceShow {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("cowdance.in")));
		int N = nextInt(), T = nextInt();
		int d[] = new int[N];
		for(int i = 0; i < N; ++i)d[i] = nextInt();
		int l = 1, r = N;
		while(l<r) {
			int m = (l+r)/2;
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			int t = 0;
			for(int i = 0; i < N;++i) {
				if(pq.size()==m) t = pq.poll();
				pq.add(t + d[i]);
			}
			while(!pq.isEmpty())t = pq.poll();
			if(t <= T)r = m;
			else l = m+1;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
		pw.println(l);
		pw.close();
	}

}
