import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;

class Maze {
	
	public enum Tile {
		Start, End, Empty, Blocked
	};

	private final Tile[] cells;
	private final int width;
	private final int height;

	public Maze(int width, int height) {
		this.width = width;
		this.height = height;
		this.cells = new Tile[width * height];
		Arrays.fill(this.cells, Tile.Empty);
	}

	public int height() {
		return height;
	}

	public int width() {
		return width;
	}

	public Tile get(int x, int y) {
		return cells[index(x, y)];
	}

		
	
	
	public void set(int x, int y, Tile tile) {
		cells[index(x, y)] = tile;
	}

	private int index(int x, int y) {
		return y * width + x;
	}



}