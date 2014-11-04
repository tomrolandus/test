public class FinalBoard {

	private char[][] grid;
	/**
	 * Initialize the finalBoard and fill it with zeros.
	 * @param height the height of the board
	 * @param width the width of the board
	 */
	public FinalBoard(int height, int width) {
		grid = new char[height][width];
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				grid[row][col] = 0;
			}
		}
	}
	/**
	 * This method will remove a row and drop rows above it down accordingly.
	 * @param row the full row that has to be removed
	 */
	public void removeLine(int row) {
		for (int rw = row; rw >= 0; rw--)
			for (int cl = 0; cl < grid[rw].length; cl++)
				if (rw == 0)
					grid[rw][cl] = 0;
				else
					grid[rw][cl] = grid[rw - 1][cl];

	}
	/**
	 * Checks if a given row is full or not.
	 * @param row the row to check for fullness
	 * @return boolean false if the row is not full and true if the row is full.
	 */
	public boolean checkFullRow(int row) {
		for(int c = 0; c < grid[row].length; c++)
			if(grid[row][c]==0)
				return false;
	return true;
	}
	/**
	 * This method checks if the pentomino that is sliding down collides with something.
	 * @param pent
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean checkFloorCollision(Pentomino pent, int[] location) {
		//NOTE:	if you commit/push some unfinished work that still contains errors, please comment it
		//		out because otherwise all kinds of errors start showing up in our code.
		//		In other words: if you don't finish a method, make sure it works as a dummy before committing.
		
		/*
		int[][] pentom = pent.getShape();
		for(int i = 0; i < pentom.length; i++)
			for(int j = 0; j < pentom[i].length; j++)
				if(pentom[i][j] == 1 && grid[row-1][j] == ){
					//not done yet
				}
				
					
		*/		
				
		return false;
	}
	/**
	 * This method places the pentomino on the finalBoard
	 * @param pent The pentomino that has to be placed.
	 * @param row The row of the pentomino that has to be placed.
	 * @param col The column of the pentomino that has to be placed.
	 */
	public void putPentomino(Pentomino pent, int[] location)
	{
		int[][] pento = pent.getShape();
		for(int i = 0; i<pento.length; i++)
			for(int j = 0; i<pento[i].length; j++)
				if(pento[i][j] == 1)
				grid[location[0]+i][location[1]+j] = pent.getType();

	}
	/**
	 * Checks if the top of the board has been reached by a pentomino.
	 * @return TRUE if the top has been reached. False if the top has not yet been reached.
	 */
	public boolean checkHitCeiling() {
		for(int i = 0; i < grid.length; i++)
			if(grid[0][i] != 0) return true;
		return false;
	}

}
