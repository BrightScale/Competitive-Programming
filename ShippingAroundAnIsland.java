import java.io.*;
import java.util.*;
public class ShippingAroundAnIsland {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken()), W = Integer.parseInt(st.nextToken());
		char map[][] = new char[H][W];
		Queue<Integer> qX = new LinkedList<Integer>();
		Queue<Integer> qY = new LinkedList<Integer>();
		br.readLine().getChars(0, W, map[0], 0);
		for(int i = 1; i < H-1; ++i) {
			br.readLine().getChars(0, W, map[i], 0);
			for(int j = 1; j < W-1; ++j) {
				if(map[i][j]=='.') {
					qX.add(j);
					qY.add(i);
				}
			}
		}
		br.readLine().getChars(0, W, map[H-1], 0);
		int dx[] = {1,0,-1,0};
		int dy[] = {0,1,0,-1};
		boolean notCon[][] = new boolean[H][W]; //true: not contains in the queue
		while(!qX.isEmpty()) {
			int x = qX.poll();
			int y = qY.poll();
			notCon[y][x] = true;
			if(x == 0 || x== W-1 || y == 0 || y == H-1)continue;
			int count = 0;
			int u[] = new int[5];
			for(int i = 0; i < 4; ++i) {
				if(map[y+dy[i]][x+dx[i]] == 'A')u[++count]=i;	
			}
			if(count < 2)continue;
			else if(count == 2){
				if(u[2]-u[1]==2)continue;
				if(map[y-dy[u[1]]-dy[u[2]]][x-dx[u[1]]-dx[u[2]]] != '.' ) continue;
			}
			map[y][x] = 'A';
			for(int i = 0; i < 4; ++i) {
				if(x+dx[i] == 0 || x+dx[i] == W-1 || y+dy[i] == 0 || y+dy[i] == H-1)continue;
				if(map[y+dy[i]][x+dx[i]] == '.' && notCon[y+dy[i]][x+dx[i]]) {
					qX.add(x+dx[i]);
					qY.add(y+dy[i]);	
					notCon[y+dy[i]][x+dx[i]] = false;
				}
			}
		}
		//find path
		int sX = 0, sY = 0;
		for(int i = 1; i < H-1; ++i) {
			boolean found = false;
			for(int j = 1; j < W-1; ++j) {
				if(map[i][j]=='A') {
					sX = j;
					sY = i-1;
					found = true;
					break;
				}
			}
			if(found)break;
		}
		//always try to move right, else forward, else left
		int ans = 0;
		int x = sX, y = sY;
		int dir = 0; //0: right, 1: down, 2: left, 3: up
		while(true) {
			if(map[y+dy[dir+1 == 4 ? 0 : dir+1]][x+dx[dir+1 == 4 ? 0 : dir+1]] == '.') {
				//trying to turn right
				dir = dir+1 == 4 ? 0 : dir+1;
				x = x+dx[dir];
				y = y+dy[dir];
			}else if(map[y+dy[dir]][x+dx[dir]] == '.'){
				//trying to move forward
				x = x+dx[dir];
				y = y+dy[dir];
			}else {
				dir = dir-1 == -1 ? 3 : dir-1;
				x = x+dx[dir];
				y = y+dy[dir];
			}
			++ans;
			if(x == sX && y == sY) break;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}
}
