import java.util.*;
import java.io.*;
public class GreatCowGathering {
	static long C[];
	static ArrayList<Path>[] path;
	static long ps[];
	private static long DFS(int pos, int parent) {
		ps[pos] = C[pos];
		long ans = 0;
		for(Path i : path[pos]) {
			if(i.v == parent) continue;
			ans += DFS(i.v, pos);
			ans += (ps[i.v]*i.w);
			ps[pos] += ps[i.v];
		}
		return ans;
	}
	private static long Solve(int pos, int parent, long currIncon) {
		long min = currIncon;
		for(Path i : path[pos]) {
			if(i.v == parent) continue;
		    min = Math.min(min, 
		    		Solve(i.v, pos,currIncon-ps[i.v]*i.w+(ps[0] - ps[i.v])*i.w));
		}
		return min;
	}
	static class Path{
		int v;
		long w;
		public Path(int v, long w) {
			this.v = v;
			this.w = w;
		}
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		C = new long[N];
		for(int i = 0; i < N; i++) {
			C[i] = Integer.parseInt(br.readLine());
		}
		path = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		StringTokenizer st;
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;;
			long l = Long.parseLong(st.nextToken());;
			path[a].add(new Path(b,l));
			path[b].add(new Path(a,l));
		}
		br.close();
		ps = new long[N];
		System.out.println(Solve(0,-1,DFS(0,-1)));
		System.out.println();
	}

}
