import java.util.*;
public class RobotPlow2 {
	static class Row implements Comparable<Row>{
		boolean type;
		int x1;
		int x2;
		int y;
		public Row(boolean type, int x1, int x2, int y) {
			this.type = type;
			this.x1 = x1;
			this.x2 = x2;
			this.y = y;
		}
		@Override
		public int compareTo(Row o) {
			return y- o.y;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int X = in.nextInt();
		in.nextInt();
		int I = in.nextInt();
		Row[] row = new Row[2*I];
		for(int i = 0; i < I; i++){
			int x1 = in.nextInt()-1;
			int y1 = in.nextInt()-1;
			int x2 = in.nextInt()-1;
			int y2 = in.nextInt()-1;
			//top
			row[i] = new Row(true,x1,x2,y1);
			//bottom
			row[I+i] = new Row(false,x1,x2,y2+1);
		}
		in.close();
		Arrays.sort(row);
		int dp[] = new int[X];
		int ans = 0;
		for(int i = 0; i < 2*I-1; i++) {
			if(row[i].type) {
				for(int j = row[i].x1; j <= row[i].x2; j++) {
					dp[j]++;
				}
			}else {
				for(int j = row[i].x1; j <= row[i].x2; j++) {
					dp[j]--;
				}
			}
			if(row[i+1].y != row[i].y) {
				int addAmount = 0;
				for(int j = 0; j < X; j++) {
					if(dp[j] != 0) addAmount++;
				}
				
				ans += addAmount * (row[i+1].y - row[i].y);
			}
		}
		System.out.println(ans);
	}

}
