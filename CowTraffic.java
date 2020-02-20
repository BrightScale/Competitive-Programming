import java.util.*;

public class CowTraffic {
	static class Node{
		LinkedList<Integer> parent;;
		LinkedList<Integer> child;;
		public Node() {
			parent = new LinkedList<Integer>();
			child = new LinkedList<Integer>();
			
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), M = in.nextInt();
		Node[] node = new Node[N];
		for(int i = 0; i < N; i++) {
			node[i] = new Node();
		}
		for(int i = 0; i < M; i++) {
			int u = in.nextInt()-1;
			int v = in.nextInt()-1;
			node[u].child.add(v);
			node[v].parent.add(u);
		}
		in.close();
		int fromStart[] = new int[N];
		for(int i = 0; i < N; i++) {
			if(node[i].parent.isEmpty()) {
				fromStart[i] = 1;
				continue;
			}
			for(int j = 0; j < node[i].parent.size(); j++) {
				fromStart[i] += fromStart[node[i].parent.get(j)];
			}
		}
		int fromBarn[] = new int[N];
		for(int i = N-1; i >= 0; i--) {
			if(node[i].child.isEmpty()) {
				fromBarn[i] = 1;
				continue;
			}
			for(int j = 0; j < node[i].child.size(); j++) {
				fromBarn[i] += fromBarn[node[i].child.get(j)];
			}
		}
		int max = 0;
		for(int i = 0; i < N-1; i++) {
			for(int j = 0; j < node[i].child.size();j++) {
				max = Math.max(fromStart[i]*fromBarn[node[i].child.get(j)], max);
			}
		}
		System.out.println(max);
	}

}
