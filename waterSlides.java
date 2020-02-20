import java.util.*;
public class waterSlides {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), M = in.nextInt();
		ArrayList<Platform> plat = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			plat.add(new Platform(in.nextInt(),0,0));
		}
		for(int i = 0; i < M; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			plat.set(u-1,new Platform(plat.get(u-1).x,plat.get(u-1).in,plat.get(u-1).out+1));
			plat.set(v-1,new Platform(plat.get(v-1).x,plat.get(v-1).in+1,plat.get(v-1).out));
		}
		in.close();
		TreeMap<Integer,Integer> inSlide = new TreeMap<Integer,Integer>();
		TreeMap<Integer,Integer> outSlide = new TreeMap<Integer,Integer>();
		for(int i = 0; i < N; i++) {
			if(plat.get(i).in > plat.get(i).out) {
				inSlide.put(plat.get(i).x, plat.get(i).in-plat.get(i).out);
			}else if(plat.get(i).in < plat.get(i).out) {
				outSlide.put(plat.get(i).x, plat.get(i).out-plat.get(i).in);
			}
		}
		int walk = 0;
		while(inSlide.size() != 0) {
			if(inSlide.get(inSlide.firstKey()) == 0) {
				inSlide.remove(inSlide.firstKey());
				continue;
			}
			if(outSlide.get(outSlide.firstKey()) == 0) {
				outSlide.remove(outSlide.firstKey());
			}
			walk += Math.abs(inSlide.firstKey()-outSlide.firstKey());
			inSlide.put(inSlide.firstKey(),inSlide.get(inSlide.firstKey())-1);
			outSlide.put(outSlide.firstKey(),outSlide.get(outSlide.firstKey())-1);
		}
		System.out.println(walk);
	
	}
}

class Platform {
	int x;
	int in;
	int out;
	public Platform(int x, int in, int out) {
		this.x = x;
		this.in = in;
		this.out = out;
	}
}