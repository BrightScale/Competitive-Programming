import java.util.*;
import java.io.*;
public class BreedCounting {
	static class Type{
		int H;
		int G;
		int J;
		public Type(int H, int G, int J) {
			this.H = H;
			this.G = G;
			this.J = J;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("bcount.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), Q = Integer.parseInt(st.nextToken());
		Type[] pos = new Type[N+1];
		pos[0] = new Type(0,0,0);
		for(int i = 1; i < N+1; i++) {
			int a = Integer.parseInt(br.readLine());
			if(a==1)pos[i] =  new Type(pos[i-1].H+1,pos[i-1].G,pos[i-1].J);
			else if(a==2)pos[i] = new Type(pos[i-1].H,pos[i-1].G+1,pos[i-1].J);
			else pos[i] = new Type(pos[i-1].H,pos[i-1].G,pos[i-1].J+1);
		}
		PrintWriter pw = new PrintWriter("bcount.out");
		for(int i = 0; i  < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1, b = Integer.parseInt(st.nextToken());
			if(a < 0)a = 0;
			if(b > N) b = N;
			pw.println((pos[b].H-pos[a].H) + " " + (pos[b].G-pos[a].G) + " " + (pos[b].J - pos[a].J));
		}
		br.close();
		pw.close();
	}

}
