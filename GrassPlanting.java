import java.util.*;
import java.io.*;
public class GrassPlanting {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int node[] = new int[N];
		int ans = 0;
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			ans = Math.max(ans, Math.max(++node[Integer.parseInt(st.nextToken())-1],++node[Integer.parseInt(st.nextToken())-1]));
		}
		br.close();
		BufferedWriter bw = new BufferedWriter(new PrintWriter(new OutputStreamWriter(System.out)));
		bw.write(ans+1 + "\n");
		bw.close();
	}
}
