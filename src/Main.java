
public class Main {

	public static void main(String[] args) {
		BoardState board = new BoardState(5, 4, 1, 2);
		
		Tile t1 = new Tile(1, 3, 2, 2);
		board.addTile(t1);
		
		Tile t2 = new Tile(0, 3, 2, 1);
		board.addTile(t2);

		Tile t3 = new Tile(3, 3, 2, 1);
		board.addTile(t3);
		
		Tile t4 = new Tile(0, 1, 2, 1);
		board.addTile(t4);
		
		Tile t5 = new Tile(3, 1, 2, 1);
		board.addTile(t5);
		
		Tile t6 = new Tile(1, 2, 1, 2);
		board.addTile(t6);
		
		Tile t7 = new Tile(1, 1, 1, 1);
		board.addTile(t7);
		
		Tile t8 = new Tile(2, 1, 1, 1);
		board.addTile(t8);
		
		Tile t9 = new Tile(1, 0, 1, 1);
		board.addTile(t9);
		
		Tile t10 = new Tile(2, 0, 1, 1);
		board.addTile(t10);
		
		System.out.println(board);

		Solver.solve(board);
	}
}
