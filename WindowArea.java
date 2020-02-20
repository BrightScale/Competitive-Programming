import java.util.*;
public class WindowArea {

	public static void main(String[] args) {
		ArrayList<String> cmds = new ArrayList<>();
		Scanner in = new Scanner(System.in);
        try {
            while(true) {
	            String s = in.next();
	            cmds.add(s);
            }
        }
        catch(NoSuchElementException e) {}
        in.close();
		//got input
        
	}

}
