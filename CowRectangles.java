import java.util.*;
public class CowRectangles {
	static class Cow implements Comparable<Cow>{
		int x;
		int y;
		boolean h;
		public Cow(int x, int y, boolean h) {
			this.x = x;
			this.y = y;
			this.h = h;		
		}
		@Override
		public int compareTo(Cow o) {
			return x - o.x;
		}
	}
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		Cow[] cow = new Cow[N];
		for(int i = 0; i < N; i++) {
			cow[i] = new Cow(in.nextInt(),in.nextInt(),in.next().equals("H")?true:false);
		}
		in.close();
		Arrays.sort(cow);
		int maxH = 0;
		int maxA = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			if(!cow[i].h)continue;
			for(int j = i+1; j < N; j++) {
				if(!cow[j].h)continue;
				int maxL = 0;
				int maxR = 0;
				int lBound = j-1;
				int rBound = j;
				for(int k = j-1;k >= 0; k--) {
					if((Math.min(cow[i].y,cow[j].y) <= cow[k].y && cow[k].y <= Math.max(cow[i].y,cow[j].y))) {
						if(!cow[k].h) {
							for(int l = k+1; l < j-1; l++) {
								if(cow[k].x == cow[l].x 
										&& (Math.min(cow[i].y,cow[j].y) <= cow[l].y && cow[l].y <= Math.max(cow[i].y,cow[j].y))) {
									lBound = l+1;
									maxL--;
								}else break;
							}
							break;
						}
						maxL++;
						lBound = k;
					}
				}
				for(int k = j; k < N; k++) {
					if((Math.min(cow[i].y,cow[j].y) <= cow[k].y && cow[k].y <= Math.max(cow[i].y,cow[j].y))) {
						if(!cow[k].h) {
							for(int l = k-1; l > j; l--) {
								if(cow[k].x == cow[l].x
										&& (Math.min(cow[i].y,cow[j].y) <= cow[l].y && cow[l].y <= Math.max(cow[i].y,cow[j].y))) {
									rBound = l-1;
									maxR--;
								}else break;
							}
							break;
						}maxR++;
						rBound = k;
					}
				}
				if(maxR+maxL > maxH) {
					maxH = maxR+maxL;
					maxA = (cow[rBound].x-cow[lBound].x)*(Math.abs(cow[i].y-cow[j].y));
				}else if(maxR+maxL == maxH) {
					maxA = Math.min(maxA, (cow[rBound].x-cow[lBound].x)*(Math.abs(cow[i].y-cow[j].y)));
				}
				if(maxH == 3 && maxA == 4) {
					System.out.print("");
				}
			}
		}
		System.out.println(maxH);
		System.out.println(maxA);
	}
}
