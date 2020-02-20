import java.io.*;
import java.util.*;
import java.awt.Point;
public class BuildingANewBarn {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		int x[] = new int[N], y[] = new int[N];
		int xMax = 0, xMin = Integer.MAX_VALUE, yMax = 0, yMin = Integer.MAX_VALUE;
		HashSet<Point> hs = new HashSet<>();
		for(int i = 0; i < N; ++i) {
			x[i] = nextInt();
			y[i] = nextInt();
			xMax = Math.max(xMax, x[i]);
			xMin = Math.min(xMin, x[i]);
			yMax = Math.max(yMax, y[i]);
			yMin = Math.min(yMin, y[i]);
		}
		for(int i = 0; i  < N; ++i) {
			x[i]-=xMin;
			y[i]-=yMin;
			hs.add(new Point(x[i],y[i]));
		}
		xMax-=xMin;
		yMax-=yMin;
		int xDifference[] = new int[xMax+1];
		int yDifference[] = new int[yMax+1];
		for(int i = 0; i <= xMax; ++i) {
			for(int j = 0; j < N; ++j) {
				xDifference[i]+=Math.abs(i-x[j]);
			}
		}
		for(int i = 0; i <= yMax; ++i) {
			for(int j = 0; j < N; ++j) {
				yDifference[i]+=Math.abs(i-y[j]);
			}
		}
		int count = 0;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i <= yMax; ++i) {
			for(int j = 0; j <= xMax; ++j) {
				if(hs.contains(new Point(j,i)))continue;
				if(xDifference[j]+yDifference[i]<min) {
					min = xDifference[j]+yDifference[i];
					count = 1;
				}else if(xDifference[j]+yDifference[i]==min) ++count;
			}
		}
		PrintWriter pw =new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(min + " " + count);
		pw.close();
	}

}
