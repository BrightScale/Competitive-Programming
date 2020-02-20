import java.util.*;

public class PowerFailure {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), W = in.nextInt();
		double M = in.nextDouble();
		int x[] = new int[N], y[] = new int[N];
		for(int i = 0; i < N; i++) {
			x[i] = in.nextInt();
			y[i] = in.nextInt();
		}
		double poles[][] = new double[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				poles[i][j] = Math.hypot(x[i]-x[j], y[i]-y[j]);
			}
		}
		for(int i = 0; i < W; i++) {
			int pi = in.nextInt()-1, pj= in.nextInt()-1;
			poles[pi][pj] = 0;
			poles[pj][pi] = 0;
		}
		in.close();
		boolean visited[] = new boolean[N];
		double l[] = new double[N];
		for (int i = 1; i < N; i++ )
	        l[i] = -1;
		while(!visited[N-1]) {
			int c = -1;
	        for (int i = 0; i < N; i++ )
	            if (!visited[i] && l[i] >= 0 && (c < 0 || l[i] < l[c]) )
	                c = i;
	        if (c < 0 ) break;
	        visited[c] = true;
	        for (int i = 0; i < N; i++ )
	            if (!visited[i] && poles[c][i] <= M && (l[i] < 0 ||
	                                l[c]+poles[c][i] < l[i]) )
	                l[i] = l[c]+ poles[c][i];
	    }
		if (!visited[N-1])
	        System.out.println(-1);
	    else System.out.println((int)(l[N-1]*1000));
	}
}
