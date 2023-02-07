
public class Main {

	public static void main(String[] args) {
		
		BoardState board = BoardState.defaultBoardI();
		
		// Solving
		BoardState result = Solver.solve(board, 3);
		// Backtracking and printing
		BoardState current = result;
		while (current != null) {
			System.out.println("Solution!");
			System.out.println(current);
			current = current.prev;
		}
				
	}
}
