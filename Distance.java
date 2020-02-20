import java.util.*;
public class Distance {
	private static int LCA(int a, int b) {
		if(rank[a] < rank[b]) {
			return LCA(b,a);
		}
		if(b == root) {
			return root;
		}
		int x = rank[a] - rank[b];
		//move a up x spots
		for(int i = 0; i < 17; i++) {
			if ((x & (1 << i)) == 1) {
				a = ancest[a].get(i);
				break;
			}
		}
		
		if(a == b) {
			return a;
		}
		while(a!=b){
			for(int i = 17; i>= 0;i--){
				if(ancest[a].size() > i && ancest[b].size() > i) {
					if(ancest[a].get(i) != ancest[b].get(i)){
						a = ancest[a].get(i+1);
						b = ancest[b].get(i+1);
						break;
					}
				}
			}
			a = ancest[a].get(0);
			b = ancest[b].get(0);
		}
		return a;

	}
	static ArrayList<Integer> ancest[];
	static int rank[];
	static int root;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt()-1, Q = in.nextInt();
		ancest = new ArrayList[N+1];
		ArrayList<Integer> child[] = new ArrayList[N+1];
		for(int i = 0; i < N+1; i++) {
			ancest[i] = new ArrayList<>();
			child[i] = new ArrayList<>();
		}
		for(int i = 0; i < N; i++) {
			int a = in.nextInt()-1;
			int b = in.nextInt()-1;
			ancest[b].add(a);
			child[a].add(b);
		}
		root = 0;
		rank = new int[N+1];
		for(int i = 0; i < N+1; i++) {
			if(ancest[i].isEmpty()) {
				root = i;
				rank[i] = 1;
				break;
			}
		}
		boolean visited[] = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(root);
		visited[root] = true;
		while(!q.isEmpty()) {
			int e = q.poll();
			for(int i = 0; i < child[e].size(); i++) {
				if(!visited[child[e].get(i)]) {
					q.add(child[e].get(i));
					visited[child[e].get(i)] = true;
					rank[child[e].get(i)] = rank[e]+1;
				}
			}
		}
		for(int i = 1; i < 17;i++) {
			for(int j = 1; j < N+1; j++) {
				if(ancest[j].size() >= i && ancest[ancest[j].get(i-1)].size() >= i) {
					ancest[j].add(ancest[ancest[j].get(i-1)].get(i-1));
				}
			}
		}
		ancest[root].add(root);
		for(int i = 0; i < Q; i++) {
			int a = in.nextInt()-1, b = in.nextInt()-1;
			System.out.println(rank[a]+rank[b]-2*rank[LCA(a,b)]);
		}
		in.close();
	}

}
