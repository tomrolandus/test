
public class FinalBoard {

    private char[][] grid;

    /**
     * Initialize the finalBoard and fill it with zeros.
     *
     * @param height the height of the board
     * @param width the width of the board
     */
    public FinalBoard(int width, int height) {
        grid = new char[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = 0;
            }
        }
    }

    public int getWidth() {
        return grid[0].length;
    }

    public int getHeight() {
        return grid.length;
    }

    /**
     * This method will remove a row and drop rows above it down accordingly.
     *
     * @param row the full row that has to be removed
     */
    public void removeLine(int row) {
        for (int rw = row; rw >= 0; rw--) {
            for (int cl = 0; cl < grid[rw].length; cl++) {
                if (rw == 0) {
                    grid[rw][cl] = 0;
                } else {
                    grid[rw][cl] = grid[rw - 1][cl];
                }
            }
        }

    }

    /**
     * Checks if a given row is full or not.
     *
     * @param row the row to check for fullness
     * @return boolean false if the row is not full and true if the row is full.
     */
    public boolean checkFullRow(int row) {
        for (int c = 0; c < grid[row].length; c++) {
            if (grid[row][c] == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method checks if the pentomino that is sliding down collides with
     * something.
     *
     * @param pent Is the pent that is sliding down.
     * @param location is the location of the top left corner of the Pentomino.
     * The first number is the row and the second number is the column.
     * @return boolean True if it collides. False if it doesn't collide.
     */
    public boolean checkFloorCollision(Pentomino pent, int[] location) {

        int[][] shape = pent.getShape();
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (row + location[0] + 1 >= grid.length) {
                    return true;
                }
                if (shape[row][col] != 0
                        && grid[location[0] + row + 1][location[1] + col] != 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkFloorCollision(Pentomino pent, int row, int col) {
        int[] location = {row, col};
        return checkFloorCollision(pent, location);

    }

    /**
     * This method places the pentomino on the finalBoard
     *
     * @param pent The pentomino that has to be placed.
     * @param row The row of the pentomino that has to be placed.
     * @param col The column of the pentomino that has to be placed.
     */
    public void putPentomino(Pentomino pent, int[] location) {
        int[][] pento = pent.getShape();
        for (int i = 0; i < pento.length; i++) {
            for (int j = 0; j < pento[i].length; j++) {
                if (pento[i][j] == 1) {
                    grid[location[0] + i][location[1] + j] = pent.getType();
                }
            }
        }

    }

    public void putPentomino(char[][] pento) {
        for (int i = 0; i < pento.length; i++) {
            for (int j = 0; j < pento[i].length; j++) {
                if (pento[i][j] != 0) {
                    grid[i][j] = pento[i][j];
                }
            }
        }

    }

    /**
     * Checks if the top of the board has been reached by a pentomino.
     *
     * @return TRUE if the top has been reached. False if the top has not yet
     * been reached.
     */
    public boolean checkHitCeiling() {
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[0][i] != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkifLegal(Pentomino pent, int[] location, int[] movement) {
        int[][] shape = pent.getShape();//shape[row][col]

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0
                        && ((col + movement[1] + location[1]) > 0)
                        && ((col + movement[1] + location[1]) < 5)
                        && ((row + movement[0] + location[0]) > 0)
                        && ((row + movement[0] + location[0]) < 15)) {
                    if (getFinalBoard()[row + movement[0] + location[0]][col + movement[1] + location[1]]
                            == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkPlacement(Pentomino pent, int[] location) {
        int[][] shape = pent.getShape();

        if (grid[0].length < location[1] + pent.getWidth() || grid.length < location[0] + pent.getHeight()) {
            return false;
        }

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0 && grid[row + location[0]][col + location[1]] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void print() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 0) {
                    System.out.print("0");
                } else {
                    System.out.print(grid[row][col]);
                }
            }
            System.out.println();
        }
    }

    public String toString() {
        String result = "";

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 0) {
                    result += "0";
                } else {
                    result += grid[row][col];
                }
            }
            result += "\n";
        }

        return result;
    }

    public char[][] getFinalBoard() {
        return grid;
    }
}
