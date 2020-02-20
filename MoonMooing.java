import java.io.*;
import java.util.*;
public class MoonMooing {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static long f(long val, int a, int b, int d) {
		return a*val/d+b;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int c = nextInt(), N = nextInt();
		int a1 = nextInt(), b1 = nextInt(), d1 = nextInt();
		int a2 = nextInt(), b2 = nextInt(), d2 = nextInt();
		ArrayList<Long> arr = new ArrayList<>();arr.add((long)c);
		int p1 = 0, p2 = 0;
		long prev = c;
		for(int i = 1; i < N;) {
			long n1 = f(arr.get(p1),a1,b1,d1), n2 = f(arr.get(p2),a2,b2,d2);
			if(n2>=n1) {
				if(n1!=prev) {
					++i;
					arr.add(n1);
				}
				++p1;
				prev = n1;
			}else {
				if(n2!=prev) {
					++i;
					arr.add(n2);
				}
				++p2;
				prev = n2;
			}
		}
		System.out.println(arr.get(N-1));
	}

}
