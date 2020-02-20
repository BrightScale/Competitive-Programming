import java.io.*;
import java.util.*;
import java.awt.Point;
public class LotsOfTriangles {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static boolean CCW(Point a, Point b, Point c) {
		double target = (double)a.y + (((double)c.x-a.x)/((double)b.x-a.x)*((double)b.y-a.y));
		return target > c.y;//((b.x-a.x)*(c.y-a.y)-(b.y-a.y)*(c.x-a.x)) >= 0;
	}
	private static class compareX implements Comparator<Point>{
		@Override
		public int compare(Point o1, Point o2) {
			return o1.x-o2.x;
		}
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		Point[] pt = new Point[N];
		for(int i = 0; i < N; ++i)pt[i] = new Point(nextInt(),nextInt());
		Arrays.sort(pt,new compareX());
		int under[][] = new int[N][N];
		for(int i = 0; i < N-1; ++i) {
			for(int j = i+1; j < N; ++j) {
				int count = 0;
				for(int k = 0; k < N; ++k) {
					if(i == k || j == k || pt[k].x < pt[i].x || pt[k].x >= pt[j].x) continue;
					if(CCW(pt[j],pt[i],pt[k]))++count;
				}
				under[i][j] = count;
			}
		}
		int ans[] = new int[N];
		for(int i = 0; i < N-2; ++i) {
			for(int j = i+1; j < N-1; ++j) {
				for(int k = j+1; k < N; ++k) {
					int count = 0;
					if(CCW(pt[i],pt[k],pt[j])){
						count = under[i][k]-under[i][j]-under[j][k]-1;
					}else {
						count = under[i][j]+under[j][k]-under[i][k];
					}
					++ans[count];
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < N-2; ++i)pw.println(ans[i]);
		pw.close();
	}

}
