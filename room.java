import java.io.*;
import java.util.*;
public class room {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 1; i <= t; ++i) {
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken());
			int num[] = new int[(int) Math.log10(g)+1];
			for(int j = 0; j<num.length; ++j) {
				num[j] = g%10;
				g/=10;
			}
			long d = Long.parseLong(st.nextToken());
			for(int j = 10; ; ++j) {
				long add = 0;
				for(int k = 0; k < num.length && add < d; ++k) {
					add += num[k]*Math.pow(j, k);
				}
				if(add==d) {
					pw.println("Hotel Visit #" + i + ": Base " + j);
					break;
				}
			}
		}
		pw.close();
	}
}
