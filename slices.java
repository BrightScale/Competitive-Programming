import java.io.*;
public class slices {
	private static StreamTokenizer st;
	private static int nextInt()throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static String read() throws IOException{
		st.nextToken();
		return st.sval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int t = nextInt();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < t; ++i) {
			int n = nextInt(), k = nextInt();
			boolean kelly = false, jim = false;
			int countK = 1, countJ = 1;
			for(int j = 0; j < n; ++j) {
				String name = read();
				nextInt();
				if(name.equals("Kelly"))++countK;
				else ++countJ;
				if(countJ - countK > k)kelly = true;
				else if(countK-countJ>k)jim=true;
			}
			if(!kelly&&!jim)pw.println("Everything is good");
			else if(jim&&kelly)pw.println("Their friendship is doomed");
			else if(jim)pw.println("Jim hates Kelly");
			else pw.println("Kelly hates Jim");
		}
		pw.close();
	}

}
