import java.io.*;
import java.util.*;
public class hanukkah {
	public static void main(String[] args) throws IOException{
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int g = Integer.parseInt(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 1; i <= g; ++i) {
			st = new StringTokenizer(br.readLine());
			long n = Long.parseLong(st.nextToken()), a = Long.parseLong(st.nextToken()), b = Long.parseLong(st.nextToken());
			if(n%2==0) {
				if(a%2!=b%2)pw.println("Graph #"+i+": Impossible");
				else{
					long ans = Math.abs(a-b)/2;
					pw.println("Graph #" + i + ": " + Math.min(ans, n/2-ans));
				}
			}else {
				if(a%2==b%2) {
					pw.println("Graph #" + i + ": " + Math.min(n-Math.abs(a-b)/2, Math.abs(a-b)/2));
				}else {
					a += n;
					pw.println("Graph #" + i + ": " + Math.min(n-Math.abs(a-b)/2, Math.abs(a-b)/2));
				}
			}
		}
		pw.close();
	}

}
