public class FinalBoard {

	private char[][] grid;

	public FinalBoard(int height, int width) {
		grid = new char[height][width];
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				grid[row][col] = 0;
			}
		}
	}

	public void removeLine(int row) {
		for (int rw = row; rw >= 0; rw--)
			for (int cl = 0; cl < grid[rw].length; cl++)
				if (rw == 0)
					grid[rw][cl] = 0;
				else
					grid[rw][cl] = grid[rw - 1][cl];

	}

	public boolean checkFullRow(int row) {
		for(int c = 0; c < grid[row].length; c++)
			if(grid[row][c]==0)
				return false;
	return true;
	}

	public boolean checkFloorCollision(Pentomino pent, int[] location) {
		
		return false;
	}

	public void putPentomino(Pentomino pent, int[] location) {
		// TODO Auto-generated method stub

	}

	public boolean checkHitCeiling() {
		// TODO Auto-generated method stub
		return false;
	}

}
