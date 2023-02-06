import java.util.*;

public class Solver {
	public static void solve(BoardState state) {
		Queue<BoardState> queue = new LinkedList<BoardState>();
		queue.add(state);
		
		while (!queue.isEmpty()) {
			BoardState currentState = queue.poll();
			if (currentState.escaped()) {
				return;
			}
			
		}
	}
}
