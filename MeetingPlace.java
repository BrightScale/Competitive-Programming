import java.util.*;
public class MeetingPlace {
	private static int LCA(int a, int b) {
		if(rank[a] < rank[b]) {
			return LCA(b,a);
		}
		if(b == root) {
			return root;
		}
		//move a up x spots
		while(rank[a]!=rank[b]){
			a = ancest[a].get(0);
		}
		
		if(a == b) {
			return a;
		}
		while(a!=b){
			a = ancest[a].get(0);
			b = ancest[b].get(0);
		}
		return a;

	}
	static ArrayList<Integer>[] ancest;
	static int rank[];
	static int root;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), M = in.nextInt();
		ancest = new ArrayList[N];
		ArrayList<Integer> child[] = new ArrayList[N];
		rank = new int[N];
		for(int i = 0; i < N; i++) {
			child[i] = new ArrayList<>();
			ancest[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < N; i++) {
			int a = in.nextInt()-1;
			ancest[i].add(a);
			child[a].add(i);
		}
		boolean visited[] = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		visited[0] = true;
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
			for(int j = 0; j < N; j++) {
				if(ancest[j].size() >= i && ancest[ancest[j].get(i-1)].size() >= i) {
					ancest[j].add(ancest[ancest[j].get(i-1)].get(i-1));
				}
			}
		}
		ancest[root].add(root);
		for(int i = 0; i < M; i++) {
			int a = in.nextInt()-1, b = in.nextInt()-1;
			System.out.println(LCA(a,b)+1);
		}
		in.close();
	}

}
