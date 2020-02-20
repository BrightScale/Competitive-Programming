import java.io.*;
public class TeleportationBronze {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("teleport.in")));
		int a = nextInt(), b = nextInt(), x = nextInt(), y = nextInt();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));
		if(b<a) { int t = a; a = b; b = t; }
		if(y<x) { int t = x; x = y; y = t; }
		pw.println(Math.min(Math.abs(x-a)+Math.abs(b-y),b-a));
		pw.close();
	}

}
