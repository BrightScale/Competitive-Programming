import java.util.*;

public class TimeTravel {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		Node node[] = new Node[N+1];
		node[0] = new Node(null, -1);
		for(int i = 0; i < N; i++) {
			String c = in.next();
			if(c.equals("a")) {
				int id = in.nextInt();
				node[i+1] = new Node(node[i],id);
			}else if(c.equals("s")) {
				node[i+1] = node[i].parent;
			}else {
				int q = in.nextInt();
				node[i+1] = node[q-1];
			}
			System.out.println(node[i+1].id);
		}
		in.close();
	}

}
class Node{
	Node parent;
	int id;
	public Node(Node parent, int id) {
		this.parent = parent;
		this.id = id;
	}
}