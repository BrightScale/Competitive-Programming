import java.io.*;
import java.util.*;
public class Dishwashing {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("dishes.in")));
		int N = nextInt(),small = 0, ans = N, base[] = new int[N];
		ArrayList<Integer> stk[] = new ArrayList[N];
		for(int i = 0; i < N; ++i)stk[i] = new ArrayList<>();
		Arrays.fill(base, -1);
		for(int i = 0; i < N; ++i) {
			int d = nextInt()-1;
			if(d<small) {
				ans = i;
				break;
			}
			for(int j = d; j >= 0 && base[j]==-1; --j)base[j] = d;
			while(!stk[base[d]].isEmpty()&&stk[base[d]].get(stk[base[d]].size()-1) < d) {
				small = stk[base[d]].get(stk[base[d]].size()-1);
				stk[base[d]].remove(stk[base[d]].size()-1);
			}
			stk[base[d]].add(d);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dishes.out")));
		pw.println(ans);
		pw.close();
	}

}
