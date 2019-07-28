package P3;

public class Piece {
	String type; //棋子类型
	int x; //棋子横坐标
	int y; //棋子纵坐标
	int tag; //棋子的标记

	Piece(String type, int x, int y, int tag) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.tag = tag;
	}
	
	public String type() {
		return type;
	}
	public int x() {
		return x;
	}
	public int y() {
		return y;
	}
	public int tag() {
		return tag;
	}

	//设置type值
	public void setType(String type) {
		this.type = type;
	}

	//设置棋子位置信息
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//判断棋子是否为同一颗棋子
	public boolean equals(Piece piece) {
		return this.type.equals(piece.type) && y == piece.y && x == piece.x && tag == piece.tag;
	}
}
