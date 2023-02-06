import java.util.*;
import java.util.stream.Collectors;

public class BoardState {
	public final int WIDTH;
	public final int HEIGHT;
	public Set<Tile> tiles;
	public Tile mainTile;
	// escape is always on the left side
	public final int escapeStart; // inclusive
	public final int escapeEnd; // inclusive
	public final int escapeSize;
	
	
	// for later
	// neighboring boardStates
	
	BoardState(int width, int height, int escapeStart, int escapeEnd) {
		if (width < 1 || height < 1) {
			throw new IllegalArgumentException("The width and the height of the board must be greater than 0.");
		} else if (escapeStart < 0) {
			throw new IllegalArgumentException("EscapeStart is out of bound.");
		} else if (escapeEnd >= height) {
			throw new IllegalArgumentException("EscapeEnd is out of bound.");
		}
		
		this.WIDTH = width;
		this.HEIGHT = height;
		this.escapeStart = escapeStart;
		this.escapeEnd = escapeEnd;
		this.escapeSize = escapeEnd - escapeStart + 1;
		tiles = new HashSet<Tile>();
	}
	
	// if true -> GAME OVER
	public boolean escaped() {
		return mainTile.j == 0 && mainTile.i >= escapeStart && mainTile.i + mainTile.H - 1<= escapeEnd;
	}
	
	// The first tile that gets added is the main tile
	public void addTile(Tile t) throws IllegalArgumentException {
		// The new tile should not overlap over the board
		for (int[] corner : t.getCorners()) {
			if (corner[0] < 0 || corner[0] >= HEIGHT || corner[1] < 0 || corner[1] >= WIDTH) {
				throw new IllegalArgumentException("Couldn't add tile " + t + " because of an overlap with the board.");
			}
		}
		
		// The new tile should not overlap with any existing tiles
		boolean overlaps = tiles.stream().anyMatch(check -> Tile.overlap(t, check));
		if (overlaps) {
			throw new IllegalArgumentException("Couldn't add tile " + t + " because of an overlap with an existing tile.");
		} else {
			tiles.add(t);
		}
		
		// main tile
		if (mainTile == null) {
			mainTile = t;
		}
	}
	
	public boolean overlap(int[] pos) throws IllegalArgumentException {
		if (pos == null || pos.length != 2) {
			throw new IllegalArgumentException("Point given, isn't valid. It must have exactly two elements (i, j).");
		}
		return tiles.stream().anyMatch(check -> check.isInside(pos));
		
	}
	
	public Tile selectTile(int[] pos) {
		if (pos == null || pos.length != 2) {
			throw new IllegalArgumentException("Point given, isn't valid. It must have exactly two elements (i, j).");
		}
		
		List<Tile> candidates = tiles.stream()
				.filter(t -> t.isInside(pos))
				.collect(Collectors.toList());
		
		if (candidates.size() == 0) {
			throw new IllegalArgumentException("No tile is currently at the specified position.");
		} else {
			return candidates.get(0);
		}
	}

	public void moveLeft(Tile t) throws IllegalArgumentException {
		if (t.j == 0) {
			throw new IllegalArgumentException("Tile " + t + " is already in the leftmost column, can't move it further to the left.");
		}
		
		int start = t.j;
		int[] checkpos = new int[] {t.i, t.j};
		
		while(checkpos[1] > 0) {
			checkpos[0] = t.i;
			checkpos[1]--;	
			for (int di = 0; di < t.H; di++) {
				if (overlap(checkpos)) {
					if (start == t.j) {
						throw new IllegalArgumentException("Tile " + t + " is being blocked by a tile on its left.");
					}
					return;
				}
				checkpos[0]++;
			}
			t.j--;
		}
	}
	
	public void moveRight(Tile t) throws IllegalArgumentException {
		if (t.j == WIDTH-1) {
			throw new IllegalArgumentException("Tile " + t + " is already in the rightmost column, can't move it further to the right.");
		}
		
		int start = t.j;
		int[] checkpos = new int[] {t.i, t.j+t.W-1};
		
		while(checkpos[1] < WIDTH-1) {
			checkpos[0] = t.i;
			checkpos[1]++;	
			for (int di = 0; di < t.H; di++) {
				if (overlap(checkpos)) {
					if (start == t.j) {
						throw new IllegalArgumentException("Tile " + t + " is being blocked by a tile on its right.");
					}
					return;
				}
				checkpos[0]++;
			}
			t.j++;
		}
	}
	
	public void moveUp(Tile t) throws IllegalArgumentException {
		if (t.i == 0) {
			throw new IllegalArgumentException("Tile " + t + " is already in the top row, can't move it further up.");
		}
		
		int start = t.i;
		int[] checkpos = new int[] {t.i, t.j};
		
		while(checkpos[0] > 0) {
			checkpos[0]--;
			checkpos[1] = t.j;
			for (int dj = 0; dj < t.W; dj++) {
				if (overlap(checkpos)) {
					if (start == t.i) {
						throw new IllegalArgumentException("Tile " + t + " is being blocked by a tile above it.");
					}
					return;
				}
				checkpos[1]++;
			}
			t.i--;
		}
	}
	
	public void moveDown(Tile t) throws IllegalArgumentException {
		if (t.i == HEIGHT-1) {
			throw new IllegalArgumentException("Tile " + t + " is already in the last row, can't move it further down.");
		}
		
		int start = t.i;
		int[] checkpos = new int[] {t.i+t.H-1, t.j};
		
		while(checkpos[0] < HEIGHT-1) {
			checkpos[0]++;
			checkpos[1] = t.j;
			for (int dj = 0; dj < t.W; dj++) {
				if (overlap(checkpos)) {
					if (start == t.i) {
						throw new IllegalArgumentException("Tile " + t + " is being blocked by a tile under it.");
					}
					return;
				}
				checkpos[1]++;
			}
			t.i++;
		}
	}
	
	public String toString() {
		boolean[][] hasTile = new boolean[HEIGHT][WIDTH];
		
		for (Tile t : tiles) {
			for (int di = 0; di < t.H; di++) {
				for (int dj = 0; dj < t.W; dj++) {
					hasTile[t.i+di][t.j+dj] = true;
				}
			}
		}
		
		String out = "Board:\n";
		
		for (int i = 0; i <= WIDTH; i++) {
			out += "--";
		}
		out += "\n";
		
		System.out.println(escapeStart + " " + escapeEnd);
		for (int i = 0; i < HEIGHT; i++) {
			boolean[] row = hasTile[i];
			
			out += escapeStart <= i && i <= escapeEnd ? "  " : "| ";
			for (boolean fill : row) {
				out += fill ? "# " : "0 ";
			}
			out += "|\n";
		}
		
		for (int i = 0; i <= WIDTH; i++) {
			out += "--";
		}
		
		out += "\n";
		
		return out;
	}
}