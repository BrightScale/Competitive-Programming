import java.util.*;

public class BestParen {

	public static final long mod = 12345678910l;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		long [] st = new long [N / 2 + 1]; 
        int idx = 0;
        for (int i = 0; i < N; i++) {
            int c = in.nextInt ();
            switch (c) {
                case 0 : 
                    idx++; 
                    break;
                case 1 : 
                    if (st [idx] == 0)
                        st [idx - 1]++;
                    else
                        st [idx - 1] += st [idx] * 2;
                    st [idx - 1] %= mod;
                    st [idx--] = 0;
                    break;
            }
        }
        System.out.println(st[0]);
        in.close();
	}

}
