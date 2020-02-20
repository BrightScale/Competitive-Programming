import java.util.*;
public class Bobsledding {
	static class Line implements Comparable <Line>{
		int x;
		int v;
		public Line(int x, int v) {
			this.x = x;
			this.v = v;
		}
		@Override
		public int compareTo(Line o) {
			return (x+v)-(o.x+o.v);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int L = in.nextInt(), N = in.nextInt();
		Line[] line = new Line[N];
		for(int i = 0; i < N; i++) {
			line[i] = new Line(in.nextInt(),in.nextInt());
		}
		in.close();
		Arrays.sort(line);
		int ans = 0;
		int position = 0;
		int velocity = 1;
		for(int i = 0; i < N; i++) {
			if(line[i].x > position && !((line[i].x-position)+velocity < line[i].v)) {
				ans = Math.max(ans, (int)Math.floor((line[i].x + line[i].v - position + velocity)/2));
				velocity = line[i].v;
				position = line[i].x;
			}
		}
		ans = Math.max(ans, velocity + (L - position));
		System.out.println(ans);
	}

}
