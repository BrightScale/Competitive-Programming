import java.io.*;
public class SecretMessage {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int M = nextInt(), N = nextInt();
		Node root = new Node();
		for(int i = 0; i < M; ++i) {
			int c = nextInt();
			Node at = root;
			for(int j = 0; j < c; ++j) {
				int s = nextInt();
				if(at.child[s]==null)at.child[s] = new Node();
				++at.child[s].pass;
				at = at.child[s];
			}
			++at.ending;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < N; ++i) {
			int c = nextInt();
			int s[] = new int[c];
			for(int j = 0; j < c; ++j) s[j] = nextInt();
			Node at = root;
			int ans = 0;
			for(int j = 0; j <= c; ++j) {
				if(j==c) {
					ans += at.pass;
					break;
				}
				if(at.child[s[j]]==null) {
					ans += at.ending;
					break;
				};
				ans += at.ending;
				at = at.child[s[j]];
			}
			pw.println(ans);
		}
		pw.close();
	}
	private static class Node{
		Node child[];
		int pass,ending;
		public Node(){
			child = new Node[2];
		}
	}
}
