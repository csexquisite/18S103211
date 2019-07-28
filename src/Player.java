package P3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player  {
	String name;  //玩家的名字
	int playerNum; //玩家编号
	int chessType; //棋局类型编号
	List<String> history = new ArrayList<String>(); //游戏历史记录
	List<Piece> pieces = new ArrayList<>(); //玩家可用棋子列表

	Player(String name, int playerNum, int chessType,Board map) {
		this.name = name;
		this.playerNum = playerNum;
		this.chessType = chessType;
		main(map);  //初始化棋盘
	}

	//添加棋子，更新棋子在player中的位置信息以及在棋盘map上的信息
	public boolean addPiece(Piece piece, int x, int y,Board map) {	
		if (x < 0 || x >= map.getSize() || y < 0 || y >= map.getSize())
			return false;
		if (map.getOnePiece(x, y) == null) {
			map.set(x, y, piece);
			for (Piece it : pieces)
				if (it.equals(piece))
					it.setPosition(x, y);
			return true;
		}
		return false;
	}

	//删除棋子，将棋子从棋盘和玩家的可用列表删除
	public boolean removePiece(Piece piece, int x, int y,Board map) {
		if (map.getOnePiece(x, y) == null)
			return false;
		else {
			if (map.getOnePiece(x, y).equals(piece)) {
				map.remove(x, y, piece);
				Iterator<Piece> iterator = 	pieces.iterator();			
				while (iterator.hasNext()) {
					Piece itPiece = iterator.next();
					if (itPiece.equals(piece))
						iterator.remove();
				}
				return true;
			}
		}
		return false;
	}

	public void main(Board map) {
		
		//分情况生成棋盘
		
		//玩家1,围棋
		if (playerNum == 1 && chessType == 1) {
			for (int i = 0; i < 180; i++)
				pieces.add(new Piece("w", -1, -1, 1));
		} 
		//玩家2,围棋
		else if (playerNum == 2 && chessType == 1) {
			for (int i = 0; i < 180; i++)
				pieces.add(new Piece("b", -1, -1, 2));
		} 
		//玩家1,国际象棋
		else if (playerNum == 1 && chessType == 0) {
			Piece itPiece = new Piece("K", 0, 4, 1);
			pieces.add(itPiece);
			map.set(0, 4, itPiece);
			itPiece = new Piece("Q", 0, 3, 1);
			pieces.add(itPiece);
			map.set(0, 3, itPiece);
			itPiece = new Piece("B", 0, 2, 1);
			pieces.add(itPiece);
			map.set(0, 2, itPiece);
			itPiece = new Piece("B", 0, 5, 1);
			pieces.add(itPiece);
			map.set(0, 5, itPiece);
			itPiece = new Piece("K", 0, 1, 1);
			pieces.add(itPiece);
			map.set(0, 1, itPiece);
			itPiece = new Piece("K", 0, 6, 1);
			pieces.add(itPiece);
			map.set(0, 6, itPiece);
			itPiece = new Piece("R", 0, 0, 1);
			pieces.add(itPiece);
			map.set(0, 0, itPiece);
			itPiece = new Piece("R", 0, 7, 1);
			pieces.add(itPiece);
			map.set(0, 7, itPiece);

			for (int i = 0; i < 8; i++) {
				itPiece = new Piece("P", 1, i, 1);
				pieces.add(itPiece);
				map.set(1, i, itPiece);
			}
		}
		//玩家2,国际象棋
		else {
			Piece itPiece = new Piece("k", 7, 4, 2);
			pieces.add(itPiece);
			map.set(7, 4, itPiece);
			itPiece = new Piece("q", 7, 3, 2);
			pieces.add(itPiece);
			map.set(7, 3, itPiece);
			itPiece = new Piece("b", 7, 2, 2);
			pieces.add(itPiece);
			map.set(7, 2, itPiece);
			itPiece = new Piece("b", 7, 5, 2);
			pieces.add(itPiece);
			map.set(7, 5, itPiece);
			itPiece = new Piece("k", 7, 1, 2);
			pieces.add(itPiece);
			map.set(7, 1, itPiece);
			itPiece = new Piece("k", 7, 6, 2);
			pieces.add(itPiece);
			map.set(7, 6, itPiece);
			itPiece = new Piece("r", 7, 0, 2);
			pieces.add(itPiece);
			map.set(7, 0, itPiece);
			itPiece = new Piece("r", 7, 7, 2);
			pieces.add(itPiece);
			map.set(7, 7, itPiece);

			for (int i = 0; i < 8; i++) {
				itPiece = new Piece("p", 6, i, 2);
				pieces.add(itPiece);
				map.set(6, i, itPiece);
			}
		}
	}
}
