import java.util.*;

public class Solver {
	public static BoardState solve(BoardState state, int maxDepth) {
		Queue<BoardState> queue = new LinkedList<BoardState>();
		queue.add(state);
		
		BoardState best = state;
		
		Set<BoardState> visited = new HashSet<BoardState>();
		visited.add(state);
		
		while (!queue.isEmpty()) {
			BoardState currentState = queue.poll();
			
			// check if we escaped
			if (currentState.escaped()) {
				return currentState;
			}
			// check if we got further left with the main tile
			if (currentState.mainTile.i <= best.mainTile.i) {
				best = currentState;
			}
			// Don't exceed max depth
			if (currentState.depth >= maxDepth-1) {
				continue;
			}
			
			int depth = currentState.depth;
			
			// go through all tiles, try to move them and add them to the queue if we have never visited that state
			for (int i = 0; i < currentState.tiles.size(); i++) {
				// move left
				BoardState copyI = currentState.copy();
				try {
					copyI.moveLeft(copyI.tiles.get(i));
					if (!visited.contains(copyI)) {
						copyI.prev = currentState;
						copyI.depth = depth + 1;
						queue.add(copyI);
						visited.add(copyI);
					}
				} catch (IllegalArgumentException e) {
					
				}
				// move right
				BoardState copyII = currentState.copy();
				try {
					copyII.moveRight(copyII.tiles.get(i));
					if (!visited.contains(copyII)) {
						copyII.prev = currentState;
						copyII.depth = depth + 1;
						queue.add(copyII);
						visited.add(copyII);
					}
				} catch (IllegalArgumentException e) {
					
				}
				// move up
				BoardState copyIII = currentState.copy();
				try {
					copyIII.moveUp(copyIII.tiles.get(i));
					if (!visited.contains(copyIII)) {
						copyIII.prev = currentState;
						copyIII.depth = depth + 1;
						queue.add(copyIII);
						visited.add(copyIII);
					}
				} catch (IllegalArgumentException e) {
					
				}
				// move down
				BoardState copyIV = currentState.copy();
				try {
					copyIV.moveDown(copyIV.tiles.get(i));
					if (!visited.contains(copyIV)) {
						copyIV.prev = currentState;
						copyIV.depth = depth + 1;
						queue.add(copyIV);
						visited.add(copyIV);
					}
				} catch (IllegalArgumentException e) {
					
				}
			}
			
		}
		
		return best;
	}
}
