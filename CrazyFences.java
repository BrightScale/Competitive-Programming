import java.io.*;
import java.util.*;
import java.awt.Point;
public class CrazyFences {
	private static boolean rayHits(long cx, long cy, long f1x, long f1y, long f2x, long f2y) {
		if ((f1y > cy) ^ (f2y > cy)) {
			return (f1y - f2y < 0) ^ (f2x * (f1y - cy) + f1x * (cy - f2y) > cx * (f1y - f2y));
		} else {
			return false;
		}
	}
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(),C = nextInt();
		Point f1[] = new Point[N], f2[] = new Point[N];
		HashMap<Point,ArrayList<Integer>> hm = new HashMap<>();
		for(int i = 0; i < N; ++i) {
			int x1 = nextInt(), y1 = nextInt(), x2 = nextInt(), y2 = nextInt();
			f1[i] = new Point(x1,y1);
			f2[i] = new Point(x2,y2);
			if(!hm.containsKey(new Point(x1,y1))) hm.put(new Point(x1,y1), new ArrayList<Integer>());
			if(!hm.containsKey(new Point(x2,y2))) hm.put(new Point(x2,y2), new ArrayList<Integer>());
			hm.get(new Point(x1,y1)).add(i);
			hm.get(new Point(x2,y2)).add(i);
		}
		ArrayList<ArrayList<Point>> cycle = new ArrayList<>();
		int current = 0;
		boolean used[] = new boolean[N];
		for(int i = 0; i < N; ++i) {
			if(used[i])continue;
			cycle.add(new ArrayList<Point>());
			cycle.get(current).add(f1[i]);
			cycle.get(current).add(f2[i]);
			Point start = f1[i],end = f2[i];
			used[i] = true;
			HashSet<Point> visited = new HashSet<>();
			visited.add(end);
			while(!start.equals(end)) {
				int num1 = hm.get(end).get(0), num2 = hm.get(end).get(1);
				end = visited.contains(f2[used[num1]?num2:num1])?f1[used[num1]?num2:num1]:f2[used[num1]?num2:num1];
				cycle.get(current).add(end);
				visited.add(end);
				used[used[num1]?num2:num1] = true;
			}
			current++;
		}
		ArrayList<Integer> inside[] = new ArrayList[current];
		for(int i = 0; i < current; ++i) inside[i] = new ArrayList<>();
		for(int i = 0; i < C; ++i) {
			Point pt = new Point(nextInt(), nextInt());
			for(int j = 0; j < current; ++j) {
				int touchNum = 0;
				//this looks like n^3 but it is actually n^2 because the inner 2 loops are just n
				for(int k = 0; k < cycle.get(j).size()-1; ++k) {
					//line intersection
					if(rayHits(pt.x,pt.y,cycle.get(j).get(k).x,cycle.get(j).get(k).y,cycle.get(j).get(k+1).x,cycle.get(j).get(k+1).y)) {
						++touchNum;
					}
				}
				if(touchNum%2 == 1) {
					inside[j].add(i+1);
				}
			}
		}
		TreeMap<Integer,ArrayList<Integer>> tm = new TreeMap<>();
		for(int i = 0; i < current; ++i) tm.put(inside[i].size(), inside[i]);
		HashSet<Integer> visited = new HashSet<>();
		int max = 0;
		while(!tm.isEmpty()) {
			int count = 0;
			for(int i : tm.pollFirstEntry().getValue()) {
				if(!visited.contains(i)) {
					visited.add(i);
					++count;
				}
			}
			max = Math.max(count, max);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(Math.max(C-visited.size(), max));
		pw.close();
		
	}

}
