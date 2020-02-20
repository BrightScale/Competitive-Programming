import java.io.*;
import java.util.*;
public class milk3 {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int A = nextInt(), B = nextInt(), C = nextInt();
		TreeSet<Integer> ts = new TreeSet<>();
		//empty A
		for(int i = 0; i <= Math.min(B, C); ++i) ts.add(C-i);
		//full B
		if(B<C)for(int i = 0; i < Math.min(C-B, A); ++i) ts.add(C-B-i);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i : ts)pw.println(i);
		pw.close();
	}

}
