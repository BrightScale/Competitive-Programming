import java.io.*;
import java.util.*;
public class CoveringTheCorral {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static class Segment implements Comparable<Segment>{
		int start;
		int len;
		public Segment(int start, int len) {
			this.start = start;
			this.len = len;
		}
		@Override
		public int compareTo(Segment o) {
			if(start == o.start) return len-o.len;
			return start - o.start;
		}
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int C = nextInt(), M = nextInt();
		Segment seg[] = new Segment[M];
		int startMax = 0,lenMax = 0;
		for(int i = 0; i < M; i++) {
			int s = nextInt(), l = nextInt();
			seg[i] = new Segment(s,l);
			if(l > lenMax) {
				lenMax = l;
				startMax = s;
			}else if(l == lenMax)startMax = Math.min(startMax,s);
		}
		Arrays.sort(seg);
		int index = 0;
		for(int i = 0; i < M; i++) {
			if(seg[i].start == startMax && seg[i].len == lenMax) {
				index = i;
				break;
			}
		}
		ArrayList<Segment> order = new ArrayList<>();
		HashSet<Integer> used = new HashSet<>();
		order.add(new Segment(startMax,lenMax));
		while(true) {
			if(used.contains(order.get(order.size()-1).start)) {
				order.remove(order.size()-1);
				break;
			}
			used.add(order.get(order.size()-1).start);
			int sm = order.get(order.size()-1).start, lm = order.get(order.size()-1).len, ind = index;
			if(order.get(order.size()-1).start + order.get(order.size()-1).len >= C) {
				for(int i = index+1; i < M; i++) {
					if(used.contains(seg[i].start))break;
					if(sm+lm < seg[i].start+seg[i].len) {
						sm = seg[i].start;
						lm = seg[i].len;
						ind = i;
					}
				}
				int lBound = order.get(order.size()-1).start + order.get(order.size()-1).len - C;
				if(sm + lm  <= C && seg[0].start <= lBound) {
					sm = 0;
					lm = 0;
				}
				for(int i = 0; i < index; i++) {
					if(seg[i].start > lBound) break;
					if(used.contains(seg[i].start))break;
					if(((sm + lm > C)?sm+lm - C : sm+lm) <= seg[i].start + seg[i].len) {
						sm = seg[i].start;
						lm = seg[i].len;
						ind = i;
					}
				}
			}else {
				for(int i = index+1; i < M; i++) {
					if(seg[i].start > order.get(order.size()-1).start + order.get(order.size()-1).len)break;
					if(used.contains(seg[i].start))break;
					if(sm+lm < seg[i].start+seg[i].len) {
						sm = seg[i].start;
						lm = seg[i].len;
						ind = i;
					}
				}
			}
			index = ind;
			order.add(new Segment(sm,lm));
		}
		int size = order.size();
		for(int i = 1; i < size;i++) {
			if(order.get(i).start < startMax)order.set(i, new Segment(order.get(i).start+C,order.get(i).len));
		}
		for(int i = 0; i < size; i++) {
			order.add(new Segment(order.get(i).start+C,order.get(i).len));
		}
		//line sweep
		index = 0;
		int i = 1;
		int ans = Integer.MAX_VALUE;
		while(index < size && i < order.size()) {
			if(order.get(i).start+order.get(i).len - order.get(index).start < C) {
				i++;
			}
			if(order.get(i).start+order.get(i).len - order.get(index).start >= C) {
				ans = Math.min(ans, i-index+1);
				index++;
			}
		}
		System.out.println(ans);
	}

}
