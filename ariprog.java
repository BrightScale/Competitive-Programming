/*
ID: kevinch12
LANG: JAVA
TASK: ariprog
*/
import java.io.*;
import java.util.*;
public class ariprog {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("ariprog.in")));
		int N = nextInt(), M = nextInt();
		boolean[] bisquares = new boolean[M*M*2+1];
		for(int i = 0; i <= M; ++i) for(int j = 0; j <= M; ++j) bisquares[i*i+j*j]=true;
		ArrayList<Sequence> ans = new ArrayList<>();
		for(int i = 0; i < M*M*2; ++i) {
			if(!bisquares[i])continue;
			diffLoop:for(int j = 1; j <= (M*M*2-i)/(N-1); ++j) {
				for(int k = 1; k < N; ++k) {
					if(!bisquares[i+j*k])continue diffLoop;
				}
				ans.add(new Sequence(i,j));
			}
		}
		Collections.sort(ans);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
		for(Sequence i : ans)pw.println(i.f + " "+ i.d);
		if(ans.isEmpty())pw.println("NONE");
		pw.close();
	}
	private static class Sequence implements Comparable<Sequence>{
		int f, d;
		public Sequence(int f, int d) {
			this.f = f;
			this.d = d;
		}
		@Override
		public int compareTo(Sequence o) {
			if(d==o.d)return f-o.f;
			return d-o.d;
		}
	}
}
