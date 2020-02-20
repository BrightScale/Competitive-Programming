import java.io.*;
import java.util.*;
public class rect1 {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int A = nextInt(), B = nextInt(), N = nextInt();
		int map[][] = new int[B+1][A+1];
		int colors[] = new int[N+1];
		HashMap<Integer,Integer> hm = new HashMap<>();
 		for(int i = 0; i < N; ++i) {
			int llx = nextInt(), lly = nextInt(), urx = nextInt(), ury = nextInt(), c = nextInt();
			colors[i] = c;
			hm.put(c, 0);
			for(int j = llx; j <= urx; ++j) {
				for(int k = lly; k <= ury; ++k) {
					map[k][j] = c;
				}
			}
		}
 		colors[N] = 0;
 		hm.put(0, 0);
 		for(int i = 1; i <= B; ++i) {
 			for(int j = 1; j <= A; ++j) {
 				hm.put(map[i][j], hm.get(map[i][j])+1);
 			}
 		}
		Arrays.sort(colors);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(1 + " " + hm.get(0));
		for(int i = 0; i < N; i++) {
			
		}
		
	}

}
