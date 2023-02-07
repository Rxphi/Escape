
public class Tile {
	public int i;
	public int j;
	public final int W;
	public final int H;
	
	// for later:
	// color
	// boolean is-main-tile
	
	Tile (int i, int j, int w, int h) {
		if (w < 1 || h < 1) {
			throw new IllegalArgumentException("The width and height of the tile must be greater than 0.");
		}
		
		this.i = i;
		this.j = j;
		this.W = w;
		this.H = h;
	}
	
	public Tile copy() {
		return new Tile(i, j, W, H);
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Tile)) {
			return false;
		}
		Tile o = (Tile) other;
		return o.i == i && o.j == j && o.W == W && o.H == H;
	}
	
	public static boolean overlap(Tile t1, Tile t2) {
		// check if any corner of t2 is inside of t1
		for (int[] corner : t2.getCorners()) {
			if (t1.isInside(corner)) {
				return true;
			}
		}
		// check if t1 is in t2
		int[] t1MainCorner = new int[] {t1.i, t1.j};
		if (t2.isInside(t1MainCorner)) {
			return true;
		}
		return false;
	}
	
	// returns true if the point is inside of the tile
	public boolean isInside(int[] point) {
		int pi = point[0];
		int pj = point[1];
		
		if (pi >= i && pi < i+H && pj >= j && pj < j+W) {
			return true;
		}
		return false;
	}
	
	public int[][] getCorners() {
		int[][] out = new int[4][];
		out[0] = new int[] {i, j};
		out[1] = new int[] {i, j+W-1};
		out[2] = new int[] {i+H-1, j};
		out[3] = new int[] {i+H-1, j+W-1};
		return out;
	}
	
	public String toString() {
		return "(Pos=(" + i + "," + j + "), W=" + W + ", H=" + H + ")";
	}
}
