/*
ID: kevinch12
LANG: JAVA
TASK: skidesign
*/
import java.io.*;
public class skidesign {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("skidesign.in")));
		int N = nextInt();
		int h[] = new int[N];
		for(int i = 0; i < N; ++i)h[i] = nextInt();
		int ans = Integer.MAX_VALUE;
		for(int i = 0; i <= 100; ++i) {
			int sum = 0;
			for(int j = 0; j < N; ++j) {
				if(h[j]>i)sum+=Math.pow(h[j]-i,2);
				else if(h[j]<i-17)sum+=Math.pow(i-17-h[j], 2);
			}
			ans = Math.min(ans, sum);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
		pw.println(ans);
		pw.close();
	}
}
