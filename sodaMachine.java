import java.io.*;
import java.util.*;
public class sodaMachine {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		ArrayList<Pair> pairs = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			int A = nextInt();
			pairs.add(new Pair(A,1));
			int B = nextInt();
			pairs.add(new Pair(B,-1));
		}
		Collections.sort(pairs);
		int sum = 0;
		int max = 0;
		for(int i = 0; i < pairs.size(); i++) {
			sum += pairs.get(i).B;
			max = Math.max(max, sum);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(max);
		pw.close();
	}
}

class Pair implements Comparable<Pair>{
	int A;
	int B;
	public Pair(int A, int B) {
		this.A = A;
		this.B = B;
	}
	@Override
	public int compareTo(Pair o) {
		if(this.A == o.A){
			if(this.B > o.B) return o.B;
			return this.B;
		}
		return this.A - o.A;
	}
	
}