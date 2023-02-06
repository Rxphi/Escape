
public class Main {

	public static void main(String[] args) {
		BoardState board = new BoardState(10, 5);
		
		Tile t1 = new Tile(3, 3, 2, 2);
		board.addTile(t1);
		
		Tile t2 = new Tile(3 ,8, 1, 1);
		board.addTile(t2);

		Tile t3 = new Tile(0, 0, 1, 1);
		board.addTile(t3);
		

		System.out.println(board);
		Tile selected = board.selectTile(new int[] {3, 3});
		board.moveUp(selected);
		System.out.println(board);
		selected = board.selectTile(new int[] {0, 0});
		board.moveRight(selected);
		System.out.println(board);
		selected = board.selectTile(new int[] {3, 8});
		board.moveLeft(selected);
		System.out.println(board);
	}

}
