import java.io.*;
import java.util.*;
public class CowLineup {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), K = nextInt();
		int val[] = new int[N];
		for(int i = 0; i < N; ++i) val[i] = nextInt();
		int l = 0, r = 0;
		HashMap<Integer,Integer> hm = new HashMap<>();
		TreeMap<Integer,Integer> tm = new TreeMap<>();
		int ans = 0;
		while(r < N) {
			if(hm.size() <= K+1) {
				if(!tm.isEmpty())ans = Math.max(ans, tm.lastKey());
				if(hm.containsKey(val[r])) {
					int c = hm.get(val[r]);
					if(tm.get(c)>1) {
						tm.put(c,tm.get(c)-1);
					}else {
						tm.remove(c);
					}
					hm.put(val[r], c+1);
					if(!tm.containsKey(c+1)) {
						tm.put(c+1, 0);
					}
					tm.put(c+1,tm.get(c+1)+1);
				}
				else {
					hm.put(val[r], 1);
					if(!tm.containsKey(1)) {
						tm.put(1, 0);
					}
					tm.put(1,tm.get(1)+1);
				}
				++r;
			}else {
				int c = hm.get(val[l]);
				if(c > 1) {
					hm.put(val[l],c-1);
					if(!tm.containsKey(c-1)) {
						tm.put(c-1, 0);
					}
					tm.put(c-1, tm.get(c-1)+1);
				}
				else {
					hm.remove(val[l]);
				}
				if(tm.get(c)>1) {
					tm.put(c,tm.get(c)-1);
				}else {
					tm.remove(c);
				}
				++l;
			}
		}
		System.out.println(ans);
	}

}
