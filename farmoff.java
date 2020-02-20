import java.util.*;

class Cow implements Comparable<Cow>{
	long w;
	long u;
	public Cow(long w, long u) {
		this.w = w;
		this.u = u;
	}
	@Override
	public int compareTo(Cow o) {
		// TODO Auto-generated method stub
				if(u>o.u || (u==o.u && w>o.w)) return -1;
				return 1;
	}
}

public class farmoff {

	public static long power(long  x,long y,long  z) {
	    long p=1;
	    for (int i = 1; i <= y; i++)
	        p = (p*x) % z;
	    return p;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long N = in.nextInt(), a = in.nextInt(), b = in.nextInt(), c = in.nextInt(),
				d = in.nextInt(), e = in.nextInt(), f = in.nextInt(), g = in.nextInt(),
				h = in.nextInt(), M = in.nextInt();
		in.close();
		ArrayList<Cow> cow = new ArrayList<Cow>();
		for(long i = 0; i < 3*N; i++) {
			cow.add(new Cow((long)(a*Math.pow(i,5) + b*Math.pow(i,2) + c) % d,
					(long)(e*Math.pow(i,5) + f*Math.pow(i,3) + g) % h));
		}
		Collections.sort(cow);
		long sum = 0;
		for (int i = 0; i < N; i++)
	        sum += cow.get(i).w;
		System.out.println(sum%M);
	}

}
