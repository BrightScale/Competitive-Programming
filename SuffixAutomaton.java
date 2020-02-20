import java.io.*;
import java.util.*;
public class SuffixAutomaton {
	
	public static void main(String[] args) {
		String s = "appsakdlae";
		SAM sam = new SAM();
		for(int i = 0; i < s.length(); ++i) {
			sam.insert(s.charAt(i));
		}
		System.out.println();
	}
	private static class SAM{
		private static class State{
			int len = 0, link = 0;
			HashMap<Character,Integer> next;
			public State(int len, int link) {
				next = new HashMap<>();
				this.len = len;
				this.link = link;
			}
		}
		final static int MAXLEN = 1000000;
		static State st[];
		static int sz,last;
		public SAM() {
			st = new State[MAXLEN*2];
			st[0] = new State(0,-1);
			++sz;
			last = 0;
		}
		public void insert(char c) {
			int cur = sz; ++sz;
			int le = 0, li = 0;
			le = st[last].len+1;
			int p = last;
			while(p!=-1 && !st[p].next.containsKey(c)) {
				st[p].next.put(c, cur);
				p = st[p].link;
			}
			if(p == -1) li = 0;
			else {
				int q = st[p].next.get(c);
				if(st[p].len + 1 == st[q].len) le = q;
				else {
					int clone = sz; ++sz;
					st[clone] = new State(st[p].len + 1,st[q].link);
					st[clone].next = st[q].next;
					while (p != -1 && st[p].next.get(c) == q) {
						st[p].next.put(c, clone);
						p = st[p].link;
					}
					st[q].link = li = clone;
				}
			}
			st[cur] = new State(le,li);
			last = cur;
		}
	}
}
