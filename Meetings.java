import java.io.*;
import java.util.*;
public class Meetings {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("meetings.in")));
		int N = nextInt(), L = nextInt();
		Status[] s = new Status[N];
		TreeSet<Integer> left = new TreeSet<>(), right = new TreeSet<>();
		for(int i = 0; i < N; ++i) {
			s[i] = new Status(nextInt(),nextInt(),nextInt());
			if(s[i].d==1)right.add(L-s[i].x);
			else left.add(s[i].x);
		}
		Arrays.sort(s);
		//find t
		int t = 0, sum = 0, l = 0, r = N-1;
		for(int i = 0; i < N; ++i) sum += s[i].w;
		while(!left.isEmpty()||!right.isEmpty()) {
			if(right.isEmpty() || left.first()<right.first()) {
				sum-=s[l].w*2; ++l;
				t = left.pollFirst();
			}else {
				sum-=s[r].w*2; --r;
				t = right.pollFirst();
			}
			if(sum<=0)break;
		}
		int ans = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i = 0; i < N; ++i) {
			if(s[i].d == -1) {
				while(!q.isEmpty()&&q.peek()+2*t<s[i].x)q.poll();
				ans += q.size();
			}else q.add(s[i].x);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
		pw.println(ans);
		pw.close();
	}
	private static class Status implements Comparable<Status>{
		int w, x, d;
		public Status(int w, int x, int d) {
			this.w = w;
			this.x = x;
			this.d = d;
		}
		@Override
		public int compareTo(Status o) {
			return x-o.x;
		}
	}
}
