import java.io.*;
import java.util.*;
public class BarnExpansion {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		//line sweep horizontally and vertically
		Corner c[] = new Corner[4*N];
		for(int i = 0; i < N; ++i) {
			int x1 = nextInt(), y1 = nextInt(), x2 = nextInt(), y2 = nextInt();
			c[i] = new Corner(x1,y1,i);
			c[i+N] = new Corner(x1,y2,i);
			c[i+2*N] = new Corner(x2,y1,i);
			c[i+3*N] = new Corner(x2,y2,i);
		}
		Arrays.sort(c,new compareX());
		boolean vEdge[] = new boolean[N]; //check if barn i edge touch
		//sweep horizontally
		int currX;
		for(int i = 0; i < N; ++i) {
			
		}
	}
	private static class Corner{
		int x, y, id;
		public Corner(int x, int y, int id) {
			this.x = x;
			this.y = y;
			this.id = id;
		}
	}
	private static class compareX implements Comparator<Corner>{
		@Override
		public int compare(Corner o1, Corner o2) {
			return o1.x-o2.x;
		}
	}
	private static class compareY implements Comparator<Corner>{
		@Override
		public int compare(Corner o1, Corner o2) {
			return o1.y-o2.y;
		}
	}
}
