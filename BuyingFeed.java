import java.io.*;
import java.util.*;
public class BuyingFeed {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int K = nextInt(), E = nextInt(), N = nextInt();
		
	}

}
