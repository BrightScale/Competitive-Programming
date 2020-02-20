import java.io.*;
import java.util.*;
public class LuxuryRiverCruise {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static String read() throws IOException{
		st.nextToken();
		return st.sval;
	}
	public static void main(String[] args) throws IOException{
		 st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		 int N = nextInt(), M = nextInt(), K = nextInt();
		 int l[] = new int[N], r[] = new int[N];
		 for(int i = 0; i < N; ++i) {
			 l[i] = nextInt()-1;
			 r[i] = nextInt()-1;
		 }
		 boolean dir[] = new boolean[M];
		 for(int i = 0; i < M; ++i)dir[i] = read().equals("R");
		 int end[] = new int[N];
		 for(int i = 0; i < N; ++i) {
			 int curr = i;
			 for(int j = 0; j < M; ++j) {
				 curr = dir[j]?r[curr]:l[curr];
			 }
			 end[i] = curr;
		 }
		 HashMap<Integer,Integer> hm = new HashMap<>();
		 int curr = 0;
		 boolean found = false;
		 for(int i = 0; i < K;) {
			 if(found) {
				 curr = end[curr];
				 ++i;
				 continue;
			 }
			 if(hm.containsKey(curr)) {
				 //found cycle
				 found = true;
				 i = K-(K-i)%(i-hm.get(curr));
			 }else {
				 hm.put(curr, i);
				 curr = end[curr];
				 ++i;
			 }
		 }
		 PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		 pw.println(curr+1);
		 pw.close();
	}

}
