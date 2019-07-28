package P3;

public class Position {

	private final int x;
	private final int y;
	
	Position(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	int getx() {
		return x;
	}
	
	int gety() {
		return y;
	}
	
	//判断是否为同一位置
	boolean equals(Position p) {
		return p.x == x && p.y == y;
	}
	
}
