import java.io.*;
import java.util.*;
public class fireworks {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int t = nextInt();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 1; i <= t; ++i) {
			int n = nextInt();
			Rocket[] r = new Rocket[n];
			for(int j = 0; j < n; ++j) {
				r[j] = new Rocket(nextInt(),j+1);
			}
			Arrays.sort(r);
			pw.println("Scenario #" + i + ":");
			pw.println("Highest Firework: " + r[n-1].id);
			pw.println("Earliest Firework: " + r[0].id);
			if(i<t)pw.println();
	  	}
		pw.close();
	}
	private static class Rocket implements Comparable<Rocket>{
		int v, id;
		public Rocket(int v, int id) {
			this.v = v;
			this.id = id;
		}
		@Override
		public int compareTo(Rocket o) {
			return v - o.v;
		}
	}
}
