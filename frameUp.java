import java.util.*;
public class frameUp {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int H = in.nextInt(), W = in.nextInt();
		char[][] map = new char[H][W];
		HashMap<Character,Corners> frames= new HashMap<>();
		HashMap<Character, Integer> number = new HashMap<>();
		ArrayList<Node3> linked = new ArrayList<>();
		for(int i = 0; i < H; i++) {
			map[i] = in.next().toCharArray();
			for(int j = 0; j < W; j++) {
				if(map[i][j] != '.') {
					if(!frames.containsKey(map[i][j])) {
						frames.put(map[i][j],new Corners(j,i,j,i));
						number.put(map[i][j],frames.size()-1);
						linked.add(new Node3(map[i][j]));
					}else {
						frames.put(map[i][j], new Corners(Math.min(frames.get(map[i][j]).x1, j),
								Math.min(frames.get(map[i][j]).y1, i),Math.max(frames.get(map[i][j]).x2, j),
								Math.max(frames.get(map[i][j]).y2, i)));
					}
				}
			}
		}
		in.close();
		for(char i : frames.keySet()) {
			int x = frames.get(i).x2;
			int y = frames.get(i).y2;
			for(int j = 0; j < frames.get(i).x2 - frames.get(i).x1; j++) {
				if(map[frames.get(i).y1][x-j] != (char)linked.get(number.get(i)).c) {
					if(!linked.get(number.get(i)).below.contains(linked.get(number.get(map[frames.get(i).y1][x-j])))) {
						linked.get(number.get(i)).below.add(linked.get(number.get(map[frames.get(i).y1][x-j])));
						continue;
					}
				}
				if(map[frames.get(i).y2][x-j] != (char)linked.get(number.get(i)).c) {
					if(!linked.get(number.get(i)).below.contains(linked.get(number.get(map[frames.get(i).y2][x-j])))) {
						linked.get(number.get(i)).below.add(linked.get(number.get(map[frames.get(i).y2][x-j])));
						continue;
					}
				}
			}
			for(int j = 0; j < frames.get(i).y2 - frames.get(i).y1; j++) {
				if(map[y-j][frames.get(i).x1] != (char)linked.get(number.get(i)).c) {
					if(!linked.get(number.get(i)).below.contains(linked.get(number.get(map[y-j][frames.get(i).x1])))) {
						linked.get(number.get(i)).below.add(linked.get(number.get(map[y-j][frames.get(i).x1])));
						continue;
					}
				}
				if(map[y-j][frames.get(i).x2] != (char)linked.get(number.get(i)).c) {
					if(!linked.get(number.get(i)).below.contains(linked.get(number.get(map[y-j][frames.get(i).x2])))) {
						linked.get(number.get(i)).below.add(linked.get(number.get(map[y-j][frames.get(i).x2])));
						continue;
					}
				}
			}
		}
		//HashMap<Character,Integer> values = new HashMap<>();
		for(int i = 0; i < linked.size(); i++) {
			duplicatedValues.put(linked.get(i).c,linked.get(i).below.size());
		}
		ArrayList<Character> visited = new ArrayList<>();
		topSort(linked,visited,duplicatedValues);
		Collections.sort(answer);
		for(int i = 0; i < answer.size(); i++) {
			System.out.println(answer.get(i));
		}
	}
	public static HashMap<Character,Integer> duplicatedValues = new HashMap<>();
	public static ArrayList<String> answer = new ArrayList<>();
	public static void topSort(ArrayList<Node3> linked, ArrayList<Character> visited, HashMap<Character,Integer>values) {
		int z = 0;
		duplicatedValues = new HashMap<>(values);
		Collections.sort(linked);
		if(visited.size() == linked.size()) {
			StringBuilder sb = new StringBuilder();
			for(int i = linked.size()-1; i >= 0; i--) {
				 sb.append(visited.get(i));
			}
			answer.add(sb.toString());
			return;
		}
		while(true) {
			if(z == linked.size() || values.get(linked.get(z).c) != 0) break;
			if(visited.contains(linked.get(z).c)) {
				z++;
				continue;
			}
			@SuppressWarnings("unchecked")
			HashMap<Character,Integer> duplicate = (HashMap<Character, Integer>)values.clone();
			for(int i = z+1; i < linked.size(); i++) {
				if(linked.get(i).below.contains(linked.get(z))) {
					duplicate.put(linked.get(i).c, values.get(linked.get(i).c)-1);
				}
			}
			visited.add(linked.get(z).c);
			topSort(linked,visited,duplicate);
			visited.remove(visited.size()-1);
			z++;
		}
	}
}

class Node3 implements Comparable<Node3>{
	char c;
	ArrayList<Node3> below = new ArrayList<>();
	public Node3(char c) {
		this.c = c;
	}
	@Override
	public int compareTo(Node3 o) {
		return frameUp.duplicatedValues.get(this.c)-frameUp.duplicatedValues.get(o.c);
	}
}

class Corners{
	int x1;
	int y1;
	int x2;
	int y2;
	public Corners(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
}