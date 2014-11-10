public class Board {
	private int width, height;
	private Pentomino pent;
	private int[] location;
	final private static int DEFAULT_WIDTH = 5;
	final private static int DEFAULT_HEIGHT = 15;

	public Board(int height, int width) {
		this.width = width;
		this.height = height;
		pent = null;
		this.location = null;
	}

	public Board() {
		this(DEFAULT_HEIGHT, DEFAULT_WIDTH);
	}

	public int getWidth() {
		return width;
	}

	public Pentomino getPentomino() {
		return pent;
	}

	public void putPentomino(Pentomino pent, int[] location) {
		System.out.println("put");
		this.pent = pent;
		this.location = location;
	}

	public int getHeight() {
		return height;
	}

	public void movePentomino(int[] relativeLocation) {
		System.out.println("move");
		location[0] = location[0] + relativeLocation[0];
		location[1] = location[1] + relativeLocation[1];
	}

	public int[] getLocation() {
		return location;
	}

	public char[][] getGrid() {
		char[][] grid = new char[height][width];
		if (pent == null)
			return grid;
		
		int[][] shape = pent.getShape();

		for (int row = 0; row < shape.length; row++)
			for (int col = 0; col < shape[row].length; col++)
				if (shape[row][col] != 0)
					grid[row + location[0]][col + location[1]] = pent.getType();
		
		return grid;
	}

	public String toString() {
		String result = "";

		char[][] grid = getGrid();

		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++)
				if (grid[row][col] == 0)
					result += "0";
				else
					result += grid[row][col];
			result += "\n";
		}

		return result;
	}
}
