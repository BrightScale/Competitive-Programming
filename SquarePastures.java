import java.io.*;
public class SquarePastures {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("square.in")));
		int x1 = nextInt(), y1 = nextInt(), x2 = nextInt(), y2 = nextInt();
		int a1 = nextInt(), b1 = nextInt(), a2 = nextInt(), b2 = nextInt();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("square.out")));
		pw.println((int)Math.pow(Math.max(Math.max(a2, x2)-Math.min(a1, x1),Math.max(b2, y2)-Math.min(b1, y1)),2));
		pw.close();
	}

}
