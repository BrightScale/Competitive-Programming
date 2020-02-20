import java.util.*;
public class ClimbWall {
	static class Point implements Comparable<Point>{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point o) {
			return x - o.x;
		}
	}
	static class Status{
		int position;
		int steps;
		public Status(int position, int steps) {
			this.position = position;
			this.steps = steps;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int H = in.nextInt(), F = in.nextInt();
		Point pt[] = new Point[F];
		for(int i = 0; i < F; i++) {
			pt[i] =  new Point(in.nextInt(), in.nextInt());
		}
		in.close();
		Arrays.sort(pt);
		Queue<Status> q = new LinkedList<>();
		boolean visited[] = new boolean[F];
		@SuppressWarnings("unchecked")
		ArrayList<Integer> edges[] = new ArrayList[F];
		for (int i = 0; i < F; i++){
			edges[i] = new ArrayList<>();
		}
		for(int i = 0;i < F; i++) {
			if(pt[i].y <= 1000) {
				q.add(new Status(i,1));
			}
			for(int j = i-1; j >= 0; j--) {
				if(pt[i].x - pt[j].x > 1000) {
					break;
				}
				if(Math.pow(pt[j].y - pt[i].y, 2)+Math.pow(pt[j].x - pt[i].x, 2) <= 1000000) 
				{
					edges[i].add(j);
					edges[j].add(i);
				}
			}
		}
		while(!q.isEmpty()) {
			Status e = q.poll();
			if (visited[e.position])
				continue;
			visited[e.position] = true;
			if(pt[e.position].y + 1000 >= H) {
				System.out.println(e.steps);
				return;
			}
			for(int i = 0; i < edges[e.position].size(); i++) {
				if(!visited[edges[e.position].get(i)]) 
					q.add(new Status(edges[e.position].get(i),e.steps+1));
			}
		}
	}

}
