import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TileTest {

	@Test
	void cornerTestI() {
		Tile t = defaultTile();
		int[][] corners = new int[4][];
		corners[0] = new int[] {0, 0};
		corners[1] = new int[] {0, 3};
		corners[2] = new int[] {5, 0};
		corners[3] = new int[] {5, 3};
		
		Assertions.assertArrayEquals(corners, t.getCorners());
	}
	
	@Test
	void cornerTestII() {
		Tile t = defaultTile();
		t.i += 2;
		t.j += 3;
		int[][] corners = new int[4][];
		corners[0] = new int[] {2, 3};
		corners[1] = new int[] {2, 6};
		corners[2] = new int[] {7, 3};
		corners[3] = new int[] {7, 6};
		
		Assertions.assertArrayEquals(corners, t.getCorners());
	}
	
	@Test
	void isInsideTestI() {
		Tile t = defaultTile();
		// check that all corner are in the tile itself
		int[] point1 = new int[] {0, 0};
		int[] point2 = new int[] {0, 3};
		int[] point3 = new int[] {5, 0};
		int[] point4 = new int[] {5, 3};
		
		assertTrue(t.isInside(point1));
		assertTrue(t.isInside(point2));
		assertTrue(t.isInside(point3));
		assertTrue(t.isInside(point4));
		
		// check some other points that are int the tile
		int[] point5 = new int[] {2, 3};
		int[] point6 = new int[] {1, 1};
		int[] point7 = new int[] {5, 2};
		
		assertTrue(t.isInside(point5));
		assertTrue(t.isInside(point6));
		assertTrue(t.isInside(point7));
		
		int[] point8 = new int[] {-1, 0};
		int[] point9 = new int[] {-2, -3};
		int[] point10 = new int[] {6, 3};
		int[] point11 = new int[] {6, 0};
		
		assertFalse(t.isInside(point8));
		assertFalse(t.isInside(point9));
		assertFalse(t.isInside(point10));
		assertFalse(t.isInside(point11));
	}
	
	Tile defaultTile() {
		return new Tile(0, 0, 4, 6);
	}

}
