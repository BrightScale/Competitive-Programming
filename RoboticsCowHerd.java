import java.io.*;
import java.util.*;
public class RoboticsCowHerd {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static ArrayList<Integer> micro[];
	private static int N, K;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		N = nextInt(); K = nextInt();
		micro = new ArrayList[N];
		//long r1 = 0;
		for(int i = 0; i < N; ++i) {
			int M = nextInt();
			micro[i] = new ArrayList<>();
			for(int j = 0; j < M; ++j) micro[i].add(nextInt());
			Collections.sort(micro[i]);
			//r1+=micro[i].get(0);
		}
		long l = 0L, r = 10000000000001L;
		while(l<r) {
			long m = (l+r)>>1;
			count = 0;
			DFS(0,m);
			if(count >= K) r = m;
			else l = m+1;
		}
		DFS2(0,r,0);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}
	private static int count;
	private static void DFS(int curr, long limit) {
		if(curr==N) {
			++count;
			return;
		}
		for(int i : micro[curr]) {
			if(count == K)return;
			if((i-micro[curr].get(0)) <= limit) {
				DFS(curr+1,limit-(i-micro[curr].get(0)));
			}else break;
		}
	}
	private static long ans = 0;
	private static void DFS2(int curr, long limit, long cost) {
		if(curr==N) {
			ans += cost;
			return;
		}
		for(int i : micro[curr]) {
			if((i-micro[curr].get(0)) <= limit) {
				DFS2(curr+1,limit-(i-micro[curr].get(0)),cost+i);
			}else break;
		}
	}
}
