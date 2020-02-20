import java.util.*;

public class ChocBuy {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long N = in.nextLong(), B = in.nextLong();
		ArrayList<Type> pair = new ArrayList<>();
		for(long i = 0; i < N; i++) {
			long P = in.nextLong(), C = in.nextLong();
			pair.add(new Type(P,C));
		}
		Collections.sort(pair);
		long cow = 0;
		for(int i = 0; i < N; i++) {
			long t = (long) Math.floor((double)(B/pair.get(i).P));
	        if (t >= pair.get(i).C) {
	            B -= pair.get(i).P * pair.get(i).C;
	            cow += pair.get(i).C;
	        } else {
	            B -= t * pair.get(i).P;
	            cow += t;
	        }
		}
		System.out.println(cow);
		in.close();
	}
	
}
class Type implements Comparable<Type>{
	long P, C;
	public Type(long P, long C) {
		this.P = P;
		this.C = C;
	}
	@Override
	public int compareTo(Type o) {
		if(this.P > o.P) return 1;
		return -1;
	}
}