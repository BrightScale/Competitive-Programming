import java.io.*;
public class MooBuzz {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("moobuzz.in")));
		int N = nextInt();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moobuzz.out")));
		long l = 0, r = Integer.MAX_VALUE;
		while(l<r) {
			long m = (l+r)>>1;
			if(N<=m-(m/3)-(m/5)+(m/15))r=m;
			else if(N>m-(m/3)-(m/5)+(m/15))l=m+1;
		}
		pw.println(r);
		pw.close();
	}

}
