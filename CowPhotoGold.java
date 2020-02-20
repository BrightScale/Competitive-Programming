import java.util.*;

public class CowPhotoGold {
	@SuppressWarnings("unchecked")
	static HashMap<Integer, Integer> map[] = new HashMap[5];
	static boolean cmp(int a, int b) {
		  int f = 0;
		  for(int i = 0; i < 5; i++) {
			  
		  }
		  return f > 2;
		}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		for(int i = 0; i < 5; i++) {
			map[i] = new HashMap<Integer,Integer>();
			for(int j = 0; j < N; j++) {
				map[i].put(j, in.nextInt());
			}
			if(i > 0) {
				
			}
		}
		in.close();
	}

}
