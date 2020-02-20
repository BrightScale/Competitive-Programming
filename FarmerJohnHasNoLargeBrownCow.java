import java.io.*;
import java.util.*;
public class FarmerJohnHasNoLargeBrownCow {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken())-1;
		ArrayList<String>[] adj = new ArrayList[N];
		for(int i = 0; i < N; ++i) adj[i] = new ArrayList<>();
		int max = 0;
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 4; ++j)st.nextToken();
			int count = 0;
			while(true) {
				String s = st.nextToken();
				if(s.equals("cow."))break;
				adj[i].add(s);
				++count;
			}
			max = Math.max(count, max);
		}
		br.close();
		ArrayList<String>[] prev = adj.clone();
		TreeSet<String> ts[] = new TreeSet[max];
		for(int i = 0; i < max; ++i) {
			ts[i] = new TreeSet<>();
			for(int j = 0; j < N; ++j) {
				if(adj[j].size()<max) continue;
				ts[i].add(adj[j].get(i));
			}
		}
		HashMap<String,Integer> hm[] = new HashMap[max];
		HashMap<Integer,String> match[] = new HashMap[max];
		for(int i = 0; i < max; ++i) {
			hm[i] = new HashMap<>();
			match[i] = new HashMap<>();
			int count = -1;
			for(String j : ts[i]) {
				hm[i].put(j, ++count);
				match[i].put(count,j);
			}
		}
		int prefix[] = new int[max];
		for(int i = max-1; i > 0; --i) {
			if(i==max-1)prefix[i]=1;
			prefix[i-1]+=prefix[i]*ts[i].size();
		}
		//compute already ones
		TreeSet<Integer> prevIndex = new TreeSet<>();
		for(int i = 0; i < N; ++i) {
			int index = 0;
			for(int j = 0; j < max; ++j) {
				index += prefix[j]*hm[j].get(prev[i].get(j));
			}
			prevIndex.add(index);
		}
		for(int i : prevIndex) {
			if(i <= K)++K;
			else break;
		}
		//find
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < max; ++i) {
			pw.print(match[i].get(K/prefix[i]));
			if(i!=max-1)pw.print(" ");
			K%=prefix[i];
		}
		pw.close();
	}

}
