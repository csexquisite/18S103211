package P3;

public class Board {
	private final int n;
	Piece[][] map;  //棋盘

	Board(int n) {
		this.n = n;
		this.map = new Piece[n][n];
	}

	public int getSize() {
		return n;
	}

	//在棋盘上的(x,y)位置放置棋子piece
	public boolean set(int x, int y, Piece piece) {
		if (x < 0 || x >= n || y < 0 || y >= n)
			return false;
		if (map[x][y] == null) {
			map[x][y] = piece;
			return true;
		}
		return false;
	}
	
	//将位于(x,y)的piece从棋盘中移除
	public boolean remove(int x,int y,Piece piece) {
		if (x < 0 || x >= n || y < 0 || y >= n)
			return false;
		if(map[x][y]==null)
			return false;
		map[x][y] = null;
		return true;
	}
	
	//获取(x,y)处的棋子
	public Piece getOnePiece(int x,int y) {
		if (x < 0 ||x >= n ||y < 0 || y >= n)
			return null;
		return map[x][y];
	}

}
