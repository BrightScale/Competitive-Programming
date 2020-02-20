import java.io.*;
import java.util.*;
import java.awt.Point;
public class ClaustrophobicCows2 {
	private static Point b1, b2;
	private static double min = Integer.MAX_VALUE;
	private static void closestPoints(Point[] pX, Point[] pY, int N){
		//closest points o(nlogn)
		if(N <= 3) {
			for(int i = 0; i < N; ++i) {
				for(int j = i+1; j < N; ++j) {
					double dist = Math.sqrt(Math.pow((double)pX[i].x - pX[j].x,2)
							+ Math.pow((double)pX[i].y-pX[j].y, 2));
					if(dist == min ? Math.min(pt.get(pX[i]),pt.get(pX[j])) < Math.min(pt.get(b1),pt.get(b2)): dist < min) {
						min = dist;
						b1 = pX[i];
						b2 = pX[j];
					}
				}
			}
			return;
		}
		int mid = N/2;
		Point midpt = pX[mid-1];
		Point Pyl[] = new Point[mid];
	    Point Pyr[] = new Point[N-mid]; 
	    int li = 0, ri = 0; 
	    for (int i = 0; i < N; ++i) { 
	      if (pY[i].x == midpt.x ? pY[i].y <= midpt.y : pY[i].x <= midpt.x)
	         Pyl[li++] = pY[i]; 
	      else
	         Pyr[ri++] = pY[i]; 
	    }
	    closestPoints(Arrays.copyOfRange(pX, 0, mid), Pyl, mid); 
	    closestPoints(Arrays.copyOfRange(pX, mid, N), Pyr, N-mid); 
	    
	    Point strip[] = new Point[N]; 
	    int size = 0; 
	    for (int i = 0; i < N; i++) {
	        if (Math.abs(pY[i].x - midpt.x) <= min) {
	            strip[size++] = pY[i];
	        }
	    }
	    for (int i = 0; i < size; ++i) {
	        for (int j = i+1; j < size && (strip[j].y - strip[i].y) <= min; ++j) {
	        	double dist = Math.sqrt(Math.pow((double)strip[i].x - strip[j].x,2)
						+ Math.pow((double)strip[i].y-strip[j].y, 2));
	            if (dist == min ? Math.min(pt.get(strip[i]),pt.get(strip[j])) < Math.min(pt.get(b1),pt.get(b2)): dist < min) { 
	                min = dist;
	                b1 = strip[i];
	                b2 = strip[j];
	            }
	        }
	    }
	}
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static class compareX implements Comparator<Point>{
		@Override
		public int compare(Point o1, Point o2) {
			if(o1.x == o2.x)return o1.y - o2.y;
			return o1.x - o2.x;
		}
	}
	private static class compareY implements Comparator<Point>{
		@Override
		public int compare(Point o1, Point o2) {
			if(o1.y == o2.y)return o1.x - o2.x;
			return o1.y - o2.y;
		}
	}
	private static HashMap<Point,Integer> pt = new HashMap<>();
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		Point pX[] = new Point[N], pY[] = new Point[N];
		for(int i = 0; i < N; i++) {
			int x = nextInt(), y = nextInt();
			pX[i] = new Point(x,y);
			pY[i] = new Point(x,y);
			pt.put(new Point(x,y), i+1);
		}
		Arrays.sort(pX, new compareX());
		Arrays.sort(pY, new compareY());
		closestPoints(pX, pY, N);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(Math.min(pt.get(b1), pt.get(b2)) + " " + Math.max(pt.get(b1), pt.get(b2)));
		pw.close();
	}

}
