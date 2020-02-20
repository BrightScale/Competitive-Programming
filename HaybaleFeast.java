import java.io.*;
import java.util.*;
public class HaybaleFeast {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static long nextLong() throws IOException {
		st.nextToken();
		return (long)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		long M = nextLong();
		int ans = Integer.MAX_VALUE;
		long sum = 0; //flavor sum
		TreeMap<Integer,Integer> spice = new TreeMap<>();
		HashSet<Integer> hs = new HashSet<>();
		int f[] = new int[N];
		int s[] = new int[N];
 		//sliding window
		for(int l = 0, r = 0; r < N;) {
			if(sum < M) {
				//add
				f[r] = nextInt();
				s[r] = nextInt();
				
				if(hs.contains(s[r])) {
					spice.put(s[r], spice.get(s[r])+1);
				}else {
					spice.put(s[r],1);
					hs.add(s[r]);
				}
				sum+=f[r];
				++r;
			}
			if(sum >= M){
				//remove
				ans = Math.min(ans, spice.lastKey());
				sum-=f[l];
				if(spice.get(s[l]) == 1) {
					spice.remove(s[l]);
					hs.remove(s[l]);
				}else {
					spice.put(s[l], spice.get(s[l])-1);
				}
				++l;
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}

}
