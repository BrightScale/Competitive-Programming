import java.io.*;
public class ChocolaeEating {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), D = nextInt();
		int H[] = new int[N];
		long l = 0, r = 0;
		for(int i = 0; i < N; ++i) {
			H[i] = nextInt();
			r += H[i];
		}
		while(l<r) {
			long m = (l+r+1)/2;
			boolean valid = true;
			long happy = 0;
			int j = 0;
			for(int i = 0; i < D; ++i) {
				happy >>= 1;
				while(happy < m && j < N) {
					happy += H[j];
					++j;
				}
				if(happy < m) {
					valid = false;
					break;
				}
			}
			if(!valid) r = m-1;
			else {
				l = m;
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(l);
		pw.close();
	}

}
