import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BoardStateTest {

	@Test 
	void validBoardTestI() {
		assertThrows(IllegalArgumentException.class, () -> new BoardState(-1, 0, 1, 1));
		assertThrows(IllegalArgumentException.class, () -> new BoardState(-1, -1, 1, 1));
		assertThrows(IllegalArgumentException.class, () -> new BoardState(0, -1, 1, 1));
		assertThrows(IllegalArgumentException.class, () -> new BoardState(0, 5, 1, 1));
		assertThrows(IllegalArgumentException.class, () -> new BoardState(5, 0, 1, 1));
		assertDoesNotThrow(() -> new BoardState(1, 1, 0, 0));
		assertDoesNotThrow(() -> new BoardState(1, 2, 1, 1));
		assertDoesNotThrow(() -> new BoardState(2, 1, 0, 0));
		assertDoesNotThrow(() -> new BoardState(420, 69, 1, 1));
	}
	
	@Test
	void addTilesTestI() {
		BoardState board = defaultBoard();
		Tile t1 = new Tile(0, 0, 4, 6);
		Tile t2 = new Tile(0, 6, 4, 4);
		
		board.addTile(t1);
		board.addTile(t2);
		
		assertTrue(board.tiles.contains(t1));
		assertTrue(board.tiles.contains(t2));
	}
	
	@Test
	void addTilesTestII() {
		BoardState board = defaultBoard();
		
		Tile t1 = new Tile(0, 0, 4, 6);
		board.addTile(t1);
		assertTrue(board.tiles.contains(t1));
		
		Tile t2 = new Tile(0, 3, 4, 4);
		assertThrows(IllegalArgumentException.class, () -> board.addTile(t2));
		assertFalse(board.tiles.contains(t2));
		
		Tile t3 = new Tile(0, 0, 1, 2);
		assertThrows(IllegalArgumentException.class, () -> board.addTile(t3));
		assertFalse(board.tiles.contains(t3));
		
		Tile t4 = new Tile(5, 3, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> board.addTile(t4));
		assertFalse(board.tiles.contains(t4));
		
		Tile t5 = new Tile(5, 3, 10, 10);
		assertThrows(IllegalArgumentException.class, () -> board.addTile(t5));
		assertFalse(board.tiles.contains(t5));
		
		Tile t6 = new Tile(7, 1, 2, 2);
		board.addTile(t6);
		assertTrue(board.tiles.contains(t6));
		
	}
	
	BoardState defaultBoard() {
		return new BoardState(20, 10, 3, 6);
	}

}
