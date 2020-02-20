import java.util.*;
public class Allowance {
	static class Coin implements Comparable<Coin>{
		long V;
		int C;
		public Coin(long V, int C) {
			this.V = V;
			this.C = C;
		}
		@Override
		public int compareTo(Coin o) {
			if(o.V > V) return -1;
			else return 1;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int C = in.nextInt();
		Coin V[] = new Coin[N];
		for(int i = 0; i < N; i++) {
			V[i] = new Coin(in.nextLong(),in.nextInt());
		}
		in.close();
		Arrays.sort(V);
		boolean contains =true;
	    int weeks = 0;
	    while (contains) {
	        contains = false;
	    	int allowance = C;
	        for (int i = V.length-1; i >= 0; i--) {
	        	while (V[i].C > 0 && allowance - V[i].V >= 0) {
	        		V[i].C--;
	        		allowance -= V[i].V;
	        	}
	        }
	        for (int i = 0; i < V.length && allowance > 0; i++) {
	              if (V[i].C > 0) {
	                 V[i].C--;
	                 allowance -= V[i].V;
	                 break;
	              }
	        }
	        if (allowance <= 0) contains = true;
	        if (contains) weeks++;
	    }
	    System.out.println(weeks);
	}
	
}
