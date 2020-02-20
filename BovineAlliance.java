import java.io.*;
import java.util.*;
public class BovineAlliance {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt();
		Node5[] farms = new Node5[N];
		for(int i = 0; i < N; i++) {
			farms[i] = new Node5(i);
		}
		for(int i = 0; i < M; i++) {
			int u = nextInt();
			int v = nextInt();
			farms[Math.min(u, v)-1].addToArr(farms[Math.max(u, v)-1]);
		}
		Queue<Integer> BFS = new LinkedList<>();
		BFS.add(0);
		//int visitedNum = 0;
		boolean visited[] = new boolean[N];
		long pos = 1;
		int connectedLength = 0;
		int arrlength = 0;
		visited[0] = true;
		//visitedNum++;
		while(true) {
			if(BFS.size() == 0) {
				if(connectedLength == arrlength) {
					pos *= 2;
					pos %= 1000000007;
				}else {
					pos *= connectedLength;
					pos %= 1000000007;
				}
				int cont = 0;
				for(int i = 0; i < N;i++) {
					if(!visited[i]) {
						cont = i;
						break;
					}
				}
				if(cont == 0) break;
				arrlength = 0;
				connectedLength = 0;
				BFS.add(cont);
				visited[farms[BFS.peek()].num] = true;
			}
			connectedLength++;
			arrlength += farms[BFS.peek()].to.size();
			for(int i = 0; i < farms[BFS.peek()].to.size(); i++) {
				if(!visited[farms[BFS.peek()].to.get(i).num]) {
					BFS.add(farms[BFS.peek()].to.get(i).num);
					visited[farms[BFS.peek()].to.get(i).num] = true;
					//visitedNum++;
				}
			}
			BFS.remove();
		}
		System.out.println(pos);
	}
	private static class Node5{
		int num;
		public Node5(int num) {
			this.num = num;
		}
		ArrayList<Node5> to = new ArrayList<Node5>();
		public void addToArr(Node5 dest) {
			this.to.add(dest);
		}
	}
}
