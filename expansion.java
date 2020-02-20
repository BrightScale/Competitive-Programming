import java.io.*;
import java.util.*;
public class expansion {
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
			int o = nextInt(), n = nextInt();
			int oTeam[] = new int[o];
			for(int j = 0; j < o; ++j)oTeam[j] = nextInt();
			Arrays.sort(oTeam);
			int[] nTeam = new int[n];
			for(int j = 0; j < n; ++j) nTeam[j] = nextInt();
			Arrays.sort(nTeam);
			int count = 0;
			for(int j = 0; j < n; ++j) {
				if(nTeam[j] >= oTeam[o/2]) {
					++count;
				}
			}
			pw.println("Expansion #" + i + ": " + count);
		}
		pw.close();
	}

}
