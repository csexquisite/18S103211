package P3;

//各种功能的实现

public class Action {

	// 统计玩家可用的棋子数
	// 输入：name(玩家名)，game(棋局)
	// 输出：(int)玩家可用棋子数
	public int countPieces(String name, Game game) {
		if (game.player1.name.equals(name))
			return game.player1.pieces.size();
		else if (game.player2.name.equals(name)) 
			return game.player2.pieces.size();
		else
			return -1;
	}

	// 查询棋盘上某一位置的棋子情况
	// 输入：Position(棋子的位置)，game(棋局)
	// 打印信息:如果这个位置没有棋子，将会打印“It is empty!”，如果有棋子，会打印是xx(用户名)的xx类型的棋子
	public Piece search(Position at, Game game) {
		if (at.getx() < 0 || at.getx() >= game.map.getSize() || at.gety() < 0 || at.gety() >= game.map.getSize()) {
			System.out.println("Out of the map!");
			return null;
		}
		if (game.map.getOnePiece(at.getx(), at.gety()) == null) {
			System.out.println("It is empty!");
			return null;
		}
		else {
			if (game.map.getOnePiece(at.getx(), at.gety()).tag() == 1)
				System.out.println("It is conquered by " + game.player1.name + "'s "
						+ game.map.getOnePiece(at.getx(), at.gety()).type() + " piece!");
			else
				System.out.println("It is conquered by " + game.player2.name + "'s "
						+ game.map.getOnePiece(at.getx(), at.gety()).type() + " piece!");
			return game.map.getOnePiece(at.getx(), at.gety());
		}
	} 

	// 将某颗棋子放上棋盘 
	// 输入:name(玩家的名字),piece(棋子信息),x(放置位置的横坐标),y(放置位置的纵坐标),game(棋局)
	// 输出：如果name是两个玩家中的任意一个名字,则返回在相应玩家中添加这个棋子的信息;如果name不是两个玩家的名字，返回false
	public boolean putPieceToBoard(String name, Piece piece, int x, int y, Game game) {
		if (game.player1.name.equals(name))
			return game.player1.addPiece(piece, x, y, game.map);
		else if (game.player2.name.equals(name))
			return game.player2.addPiece(piece, x, y, game.map);
		else
			return false;
	}

	// 移动棋盘上位置为(x1,y1)的棋子到(x2,y2)
	// 输入:game(棋局),player(玩家),piece(棋子),x1(原横坐标),x2(现横坐标),y1(原纵坐标),y2(现纵坐标)
	// 输出:如果(x1,y1)和(x2,y2)是同一位置，输出false;如果(x1,y1)和(x2,y2)中任一个点不在棋盘中，输出false;如果(x1,y1)处没有棋子，或者(x2,y2)有棋子，返回false;
	// 如果(x1,y1)处的棋子不是piece，返回false.否则，在map中删除(x1,y1)处的棋子，并在相应的玩家的顶点处将棋子的位置更新为(x2,y2)
	public boolean move(Game game, Player player, Piece piece, int x1, int y1, int x2, int y2) {
		if (x1 == x2 && y1 == y2)
			return false;
		if (x1 < 0 || x1 >= game.map.getSize() || y1 < 0 || y1 >= game.map.getSize()
				|| game.map.getOnePiece(x1, y1) == null || !game.map.getOnePiece(x1, y1).type().equals(piece.type)||game.map.getOnePiece(x1, y1).tag()!=(piece.tag())) {
			return false;
		}
		if (x2 < 0 || x2 >= game.map.getSize() || y2 < 0 || y2 >= game.map.getSize()
				|| game.map.getOnePiece(x2, y2) != null) {
			return false;
		}
		game.map.remove(x1, y1, piece);
		player.addPiece(piece, x2, y2, game.map);
		return true;
	}

	// 将某一个棋子从棋盘中移除
	// 输入:game(棋局),player(玩家),x(移除棋子的横坐标),y(移除棋子的纵坐标)
	// 输出:如果(x,y)处没有棋子或者棋子不是player的对手的而是player的，返回false;否则获取(x,y)处的棋子，将这个棋子从玩家player的对手的可用棋子列表删除
	public boolean remove(Game game, Player player, int x, int y) {
		if (x < 0 || x > game.map.getSize() || y < 0 || y >= game.map.getSize() || game.map.getOnePiece(x, y) == null
				|| game.map.getOnePiece(x, y).tag == player.playerNum) {
			return false;
		}

		Piece piece = game.map.getOnePiece(x, y);
		if (game.player1.name.equals(player.name)) {
			game.player2.removePiece(piece, x, y, game.map);

		} else {
			game.player1.removePiece(piece, x, y, game.map);

		}

		return true;
	}

	// 吃子
	// 问题由删除被吃棋子和移动去吃子的棋子合成
	public boolean changePiece(Game game, Player player, Piece piece, int x1, int y1, int x2, int y2) {
		return remove(game, player, x2, y2) && move(game, player, piece, x1, y1, x2, y2);
	}
}
