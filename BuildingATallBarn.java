import java.io.*;
import java.util.*;
public class BuildingATallBarn {
	public static void main(String[] args) throws IOException{
		BufferedReader br  = new BufferedReader(new FileReader("tallbarn.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		long a[] = new long[N];
		for(int i = 0; i < N; ++i) a[i] = Long.parseLong(br.readLine());
		double l = 0, r = K;
		for(int i = 0; i < 200; ++i) {
			double m = (l+r)/2;
			long count = 0;
			for(int j = 0; j < N; ++j) {
				count += Math.floor((1D+Math.sqrt(1D+4D*a[j]/m))/2D);
			}
			if(count <= K) r = m;
			else l = m;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("tallbarn.out")));
		double ans = 0;
		for(int i = 0; i < N; ++i) ans += a[i]/Math.floor((1D+Math.sqrt(1D+4D*a[i]/r))/2D);
		pw.println(Math.round(ans));
		pw.close();
	}

}
