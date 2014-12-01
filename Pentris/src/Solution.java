import java.util.ArrayList;


public class Solution {
	
	char[][] grid;
	
	public Solution(char[][] grid) {
		this.grid = grid;
	}
	
	public Solution(FinalBoard board,ArrayList<int[]> matrix, ArrayList<Integer> rows){
		
		int width = board.getWidth();
		
		grid = new char[board.getHeight()][board.getWidth()];
		
		for(int row : rows){
			char type = 0;
			int[] rw = matrix.get(row);
			for(int i = 0; i < 12; i++)
				if(rw[i] == 1)
					type = Pentomino.intToType(i);
					
			for (int col = 0; col < matrix.get(row).length - 12; col++) {

				int x = col % width;
				int y = col / width;

				if (matrix.get(row)[col + width] == 1)
					grid[y][x] = Character.toUpperCase(type);
			}
		}
		print();
	}
	
	public void print(){
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid[row].length; col++)
				System.out.print(grid[row][col]);
			System.out.println();
		}
		System.out.println();
		
	}

}
