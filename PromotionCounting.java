import java.io.*;
import java.util.*;
public class PromotionCounting {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static ArrayList<Integer> child[];
	private static int[] subsize, indToBIT;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		Rating rating[] = new Rating[N];
		for(int i = 0; i < N; ++i) rating[i] = new Rating(i+1,nextInt());
		Arrays.sort(rating);
		child = new ArrayList[N+1];
		for(int i = 0; i <= N; ++i)child[i] = new ArrayList<>();
		for(int i = 2; i <= N; ++i) {
			child[nextInt()].add(i);
		}
		subsize = new int[N+1];
		indToBIT = new int[N+1];
		DFS(1);
		BIT = new int[N+1];
		int ans[] = new int[N+1];
		for(int i = 0; i < N; ++i) {
			update(indToBIT[rating[i].id]+1,1);
			ans[rating[i].id] = query(indToBIT[rating[i].id]+subsize[rating[i].id]+1)-query(indToBIT[rating[i].id]+1);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 1; i <= N; ++i) {
			pw.println(ans[i]);
		}
		pw.close();
	}
	private static int ind = 0;
	private static int DFS(int id) {
		indToBIT[id] = ind;
		++ind;
		for(int i : child[id]) {
			subsize[id] += DFS(i);
		}
		return subsize[id]+1;
	}
	private static class Rating implements Comparable<Rating>{
		int id, rating;
		public Rating(int id, int rating) {
			this.id = id;
			this.rating = rating;
		}
		@Override
		public int compareTo(Rating o) {
			return o.rating-rating;
		}
	}
	private static int BIT[];
	private static void update(int i, int x) {
		while(i <= BIT.length) {
			BIT[i-1] = BIT[i-1] + x;
			i += i & -i;
		}
	}
	private static int query(int i) {
		int ret = 0;
		while(i > 0) {
			ret = ret + BIT[i-1];
			i -= i & -i;
		}
		return ret;
	}
}
