import java.io.*;
import java.util.*;
public class GoldBalencedLineup {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), K = nextInt();
		int features[][] = new int[N+1][K];
		for(int i = 1; i <= N; ++i) {
			int b = nextInt();
			for(int j = 0; j < K; ++j) {
				features[i][j] = features[i-1][j]+(b&1);
				b>>=1;
			}
		}
		for(int i = 1; i <= N; ++i) {
			for(int j = K-1; j >= 0; --j) {
				features[i][j] = features[i][0] - features[i][j];
			}
		}
		HashMap<ArrayList<Integer>,Integer> hm = new HashMap<>();
		int ans = 0;
		for(int i = 0; i <= N; ++i) {
			ArrayList<Integer >keep = new ArrayList<>();
			for(int j = 0; j < K; ++j) keep.add(features[i][j]);
			if(hm.containsKey(keep))ans = Math.max(ans, i-hm.get(keep));
			else hm.put(keep, i);
		}
		System.out.println(ans);
	}

}
