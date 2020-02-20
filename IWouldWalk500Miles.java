import java.io.*;
import java.util.*;
public class IWouldWalk500Miles {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("walk.in")));
		int N = nextInt(), K = nextInt();
		//PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
//		pw.println(-84*(K-1)-48*N+2019201997);
		boolean visited[] = new boolean[N];
		long dist[] = new long[N]; for(int i = 1; i < N; ++i) dist[i] = Long.MAX_VALUE;
		for(int i = 0; i < N-1; ++i) {
			int cur = -1;
			long min = Long.MAX_VALUE;
			for(int j = 0; j < N; ++j) {
				if(visited[j]) continue;
				if(dist[j] < min) {
					cur = j;
					min = dist[j];
				}
			}
			visited[cur] = true;
			for(int j = 0; j < N; ++j) {
				if(!visited[j] && j != cur) {
					if(cur<j) {
						dist[j] = Math.min(dist[j], ((cur+1)*2019201913L+(j+1)*2019201949L)%2019201997L);
					}else {
						dist[j] = Math.min(dist[j], ((j+1)*2019201913L+(cur+1)*2019201949L)%2019201997L);
					}
				}
			}
		}
		Arrays.sort(dist);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("walk.out")));
		pw.println(dist[N+1-K]);
		pw.close();
	}
}
