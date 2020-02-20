import java.io.*;
import java.util.*;
public class TrappedInTheHaybales {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("trapped.in")));
		int N = nextInt();
		TreeMap<Integer,Integer> tm = new TreeMap<>();
		int b = nextInt();
		for(int i = 0; i < N; ++i) {
			int s = nextInt(), p = nextInt();
			tm.put(p,s);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("trapped.out")));
		Integer l = tm.lowerKey(b), r = tm.higherKey(b);
		if(l == null || r == null) {
			pw.println(-1);
			pw.close();
			return;
		}
		int dist = r-l;
		int ans = Integer.MAX_VALUE;
		//left
		while(true) {
			if(r == null || l == null) {
				break;
			}
			dist = r-l;
			//right not cover
			if(dist>tm.get(r)) {
				r = tm.higherKey(r);
				continue;
			}
			//covered
			//if can block prev by self
			if(tm.get(l) >= dist) {
				ans = 0;
				break;
			}
			//cannot block prev by self
			ans = Math.min(dist-tm.get(l), ans);
			l = tm.lowerKey(l);
		}
		//right
		l = tm.lowerKey(b); r = tm.higherKey(b);
		while(true) {
			if(r == null || l == null) {
				break;
			}
			dist = r-l;
			//right not cover
			if(dist>tm.get(l)) {
				l = tm.lowerKey(l);
				continue;
			}
			//covered
			//if can block prev by self
			if(tm.get(r) >= dist) {
				ans = 0;
				break;
			}
			//cannot block prev by self
			ans = Math.min(dist-tm.get(r), ans);
			r = tm.higherKey(r);
		}
		pw.println(ans==Integer.MAX_VALUE?-1:ans);
		pw.close();
	}

}
