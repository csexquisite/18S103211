package P3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;


public class ActionTest {
	//计算可用棋子数，国际象棋
	@Test
	public void testCountPieces1() {
		Game game = new Game();
		game.map = new Board(8);
		game.player1 = new Player("playername1", 1, 0, game.map);
		game.player2 = new Player("playername2", 2, 0, game.map);
		assertEquals(16, game.countPieces("playername1", game));
		assertEquals(16, game.countPieces("playername2", game));
	}
	
	//计算可用棋子数，围棋
	@Test
	public void testCountPieces2() {
		Game game = new Game();
		game.map = new Board(17);
		game.player1 = new Player("playername1", 1, 1, game.map);
		game.player2 = new Player("playername2", 2, 1, game.map);
		assertEquals(180, game.countPieces("playername1", game));
		assertEquals(180, game.countPieces("playername2", game));
	}
	
	//查询棋盘上的一个棋子，国际象棋
	@Test
	public void testSearch1() { 
		Game game = new Game();
		game.map = new Board(8);
		game.player1 = new Player("playername1", 1, 0, game.map);
		game.player2 = new Player("playername2", 2, 0, game.map);
		//棋盘上存在的棋子，成功查到，且能分辨是哪个玩家的棋子
		assertEquals("K", game.search(new Position(0, 4), game).type());
		assertEquals("Q", game.search(new Position(0, 3), game).type());
		assertEquals(1, game.search(new Position(0, 4), game).tag());
		assertEquals(2, game.search(new Position(6, 4), game).tag());
		//棋盘上没有的棋子查不到，返回null
		assertEquals(null, game.search(new Position(6, 8), game));
	}
	
	//查询棋盘上的一个棋子，围棋
	@Test
	public void testSearch2() {
		Game game = new Game();
		game.map = new Board(17);
		game.player1 = new Player("playername1", 1, 1, game.map);
		game.player2 = new Player("playername2", 2, 1, game.map);
		//棋盘上不存在的棋子
		assertEquals(null, game.search(new Position(0, 4), game));
		assertEquals(null, game.search(new Position(-1, 4), game));
	}
	
	//放子，只测围棋
	@Test
	public void testPutPieceToBoard() {
		Game game = new Game();
		game.map = new Board(17);
		game.player1 = new Player("playername1", 1, 1, game.map);
		game.player2 = new Player("playername2", 2, 1, game.map);
		game.putPieceToBoard("playername1", new Piece("w", 0, 0, 1), 0, 0, game);
		game.putPieceToBoard("playername2", new Piece("b", 0, 0, 1), 2, 2, game);
		//放入原本没有棋子的位置，成功
		assertNotNull(game.search(new Position(0, 0), game));
		assertNotNull(game.search(new Position(2, 2), game));
		//放入原本有棋子的位置，失败
		assertFalse(game.putPieceToBoard("playername1", new Piece("w", 0, 0, 1), 0, 0, game));
	}
	
	//移动棋子，只测国际象棋
	@Test
	public void testMove() {
		Game game = new Game();
		game.map = new Board(8);
		game.player1 = new Player("playername1", 1, 0, game.map);
		game.player2 = new Player("playername2", 2, 0, game.map);
		//移动别人的棋子，移不动
		assertFalse(game.move(game, game.player1, new Piece("P", 1, 2, 2), 1,2, 2, 2));
		
		//移动到原本有棋子的地方，移不动
		assertFalse(game.move(game, game.player1, new Piece("P", 1, 2, 1), 1,2, 0, 2));
		
		//移动的位置原本没有棋子，移不动
		assertFalse(game.move(game, game.player1, new Piece("P", 3, 2, 1), 3,2, 2, 2));
		
		//移动棋盘外的棋子，移不动
		assertFalse(game.move(game, game.player1, new Piece("P", -1, 2, 1), -1,2, 2, 2));
		
		//前后两次位置相同，移不动
		assertFalse(game.move(game, game.player1, new Piece("P", 1, 2, 1), 1,2, 1, 2));
		
		//满足条件，可以移动
		System.out.println("1111");
		assertNull(game.search(new Position(2, 2), game));
		assertNotNull(game.search(new Position(1, 2), game));
		assertFalse(!game.move(game, game.player1, new Piece("P", 1, 2, 1), 1,2, 2, 2));
		assertNull(game.search(new Position(1, 2), game));
		assertNotNull(game.search(new Position(2, 2), game));
	}
	
	//提子，围棋
	@Test
	public void testRemove() {
		Game game = new Game();
		game.map = new Board(17);
		game.player1 = new Player("playername1", 1, 1, game.map);
		game.player2 = new Player("playername2", 2, 1, game.map);
		//提子的位置为空，提子失败
		assertFalse(game.remove(game, game.player1, 0, 1));
		assertFalse(game.remove(game, game.player1, -1, 1));
		
		game.putPieceToBoard("playername1", new Piece("w", 0, 1, 1), 0, 1, game);
		game.putPieceToBoard("playername2", new Piece("b", 2, 3, 2), 2, 3, game);
		
		//提走自己的棋子，失败
		assertFalse(game.remove(game, game.player1, 0, 1));
		
		//提走对方的棋子，成功
		assertNotNull(game.search(new Position(2, 3), game));
		assertFalse(!game.remove(game, game.player1, 2, 3));
		assertNull(game.search(new Position(2, 3), game));
	}
	
	//吃子，只测国际象棋
	@Test
	public void testEat() {
		Game game = new Game();
		game.map = new Board(8);
		game.player1 = new Player("playername1", 1, 0, game.map);
		game.player2 = new Player("playername2", 2, 0, game.map);
		
		//吃和被吃的位置任意一个为空，失败
		assertFalse(game.changePiece(game, game.player1, new Piece("P", -1, 1, 1), -1, 1, 6, 5));
		assertFalse(game.changePiece(game, game.player1, new Piece("P", 1, 1, 1), 1, 1, 5, 5));
		
		//吃自己的棋子，失败
		assertFalse(game.changePiece(game, game.player1, new Piece("P", 1, 1, 1), 1, 1, 1, 2));
		
		//用别人的棋子吃棋子，失败
		assertFalse(game.changePiece(game, game.player1, new Piece("P", 6, 1, 2), 6, 1, 6, 5));
		
		//自己的棋子吃别人的棋子，成功
		assertNotNull(game.search(new Position(1, 1), game));
		assertNotNull(game.search(new Position(6, 3), game));
		assertEquals(2, game.search(new Position(6, 3), game).tag());
		assertFalse(!game.changePiece(game, game.player1, new Piece("P", 1, 1, 1), 1, 1, 6, 3));
		assertNull(game.search(new Position(1, 1), game));
		assertEquals(1, game.search(new Position(6, 3), game).tag());
	}
	
}
