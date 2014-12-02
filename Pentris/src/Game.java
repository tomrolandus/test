import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends Observable {

    private Board board;
    private FinalBoard fboard;
    private ArrayList<Pentomino> pentominoes;
    private int score;
    private long dropSpeed;
    private Timer timer;
    private int level;
    private Pentomino currentPent;

    private static final double LEVEL_INCREASE = 0.8;
    private static final int LINE_SCORE = 1;
    private static final double BONUS_SCORE = 5;
    private static final long LEVEL_INTERVAL = 40000; // twenty seconds
    private static final long INITIAL_DROP_SPEED = 500; // 2 seconds

    public Game() {
        this(new Board(15, 5), Pentomino.getAllPentominoes(), 0);
    }

    public Game(Board board, ArrayList<Pentomino> pentominoes, int level) {
        this.board = board;
        this.pentominoes = pentominoes;
        this.score = 0;
        this.dropSpeed = (long) (INITIAL_DROP_SPEED * Math.pow(LEVEL_INCREASE,
                level));
        this.level = level;
        this.fboard = new FinalBoard(board.getWidth(), board.getHeight());
    }

    public int getLevel() {
        return this.level;
    }

    public int getScore() {
        return score;
    }

    public double getDropSpeed() {
        return dropSpeed;
    }

    public FinalBoard getFinalBoard() {
        return fboard;
    }

    public Board getBoard() {
        return board;
    }

    private void deleteRows(ArrayList<Integer> rows) {
        for (int row : rows) {
            fboard.removeLine(row);
        }
        countScore(rows.size());
    }

    private Pentomino chooseNextPentomino() {
        Random r = new Random();
        return (Pentomino) pentominoes.get(r.nextInt(pentominoes.size()))
                .clone();
    }

    private void initiatePentomino(Pentomino pent) {
        int[] startLocation = {0, 0};
        board.putPentomino(pent, startLocation);
        setChanged();
        notifyObservers();
    }

    public static int calculateScore(int amountOfRows){
    	return (int) (LINE_SCORE * amountOfRows + BONUS_SCORE
                * (amountOfRows - 1));
    }
    
    private void countScore(int amountOfRows) {
        int addScore = (int) (LINE_SCORE * amountOfRows + BONUS_SCORE
                * (amountOfRows - 1));
        score += addScore;
    }

    private void nextLevel() {
        // dropSpeed *= LEVEL_INCREASE;
        level++;
        System.out.println("Level Up");
    }

    public void rotateCurrentPent() {
        int outOfBound = Math.min(0, 5 - (board.getLocation()[1] + currentPent.getHeight()));
        //if (board.getLocation()[1] + currentPent.getHeight() < 5) {
        board.movePentomino(new int[]{0, outOfBound});
        currentPent.rotate();
        setChanged();
        notifyObservers();
        //}

    }

    public void moveCurrentPentLeft() {
        int[] oneLeft = {0, -1};
        boolean allowed = false;
        int[][] x = currentPent.getShape();
        for (int row = 0; row < x.length; row++) {
            for (int col = 0; col < x[row].length; col++) {
                if (x[row][col] != 0
                        && ((row + oneLeft[0] + board.getLocation()[0]) < 15)
                        && ((col + oneLeft[1] + board.getLocation()[1]) != -1)
                        && ((col + oneLeft[1] + board.getLocation()[1]) < 5)) {
                    if (fboard.getFinalBoard()
                            [row + oneLeft[0] + board.getLocation()[0]]
                            [col + oneLeft[1] + board.getLocation()[1]] 
                            == 0) {
                        allowed = true;
                    }
                }
            }
        
            if (allowed){
            board.movePentomino(oneLeft);
            setChanged();
            notifyObservers();
        }
    }
    }

    public void moveCurrentPentRight() {
        int[] oneRight = {0, 1};
        boolean allowed = false;
        int[][] x = currentPent.getShape();
        for (int col = 0; col < x.length; col++) {
            for (int row = 0; row < x[col].length; row++) {
                if (x[col][row] != 0
                        && ((col + oneRight[0] + board.getLocation()[1]) != -1)
                        && ((col + oneRight[0] + board.getLocation()[1]) < 15)
                        && ((row + oneRight[1] + board.getLocation()[0]) != -1)
                        && ((row + oneRight[1] + board.getLocation()[0]) < 5)) {
                    if (fboard.getFinalBoard()
                            [col + oneRight[0] + board.getLocation()[1]]
                            [row + oneRight[1] + board.getLocation()[0]] 
                            == 0) {
                        allowed = true;
                    }
                }
            }
        }
        if (allowed){
            board.movePentomino(oneRight);
            setChanged();
            notifyObservers();
        }
                
    }

    public void moveCurrentPentDown() {
        final int[] oneDown = {1, 0};
         boolean allowed = false;
        int[][] x = currentPent.getShape();
    
        for (int col = 0; col < x.length; col++) {
            for (int row = 0; row < x[col].length; row++) {
                if (x[col][row] != 0
                        && ((col + oneDown[0] + board.getLocation()[1]) != -1)
                        && ((col + oneDown[0] + board.getLocation()[1]) < 15)
                        && ((row + oneDown[1] + board.getLocation()[0]) != -1)
                        && ((row + oneDown[1] + board.getLocation()[0]) < 5)) {
                    if (fboard.getFinalBoard()
                            [col + oneDown[0] + board.getLocation()[1]]
                            [row + oneDown[1] + board.getLocation()[0]] 
                            == 0) {
                        allowed = true;
                    }
                }
            }
        while (!fboard.checkFloorCollision(currentPent, board.getLocation())) {
            if (allowed){
            board.movePentomino(oneDown);
            board.setExtraTurn(1);
        }}
        setChanged();
        board.setExtraTurn(0);
        notifyObservers();
        
    }
    }
    public void start() {

        Timer levelTimer = new Timer();

        class LevelUp extends TimerTask {

            public LevelUp() {
                super();
            }

            public void run() {
                nextLevel();
            }
        }

        LevelUp levelUp = new LevelUp();
        levelTimer.scheduleAtFixedRate(levelUp, LEVEL_INTERVAL, LEVEL_INTERVAL);

        timer = new Timer();
        boolean firstMove = true;
        boolean newPent = true;
        game:
        while (!this.checkGameOver()) {

            if (newPent) {
                // Initialize pentomino in the top middle of the screen
                this.currentPent = this.chooseNextPentomino();
                initiatePentomino(currentPent);
                newPent = false;
                
            }

            if (!fboard.checkPlacement(currentPent, board.getLocation())) {
                break;
            }

            class MoveDown extends TimerTask {

                public MoveDown() {
                    super();
                }

                public void run() {
                    int[] oneDown = {1, 0};
                    board.movePentomino(oneDown);
                    
                    setChanged();
                    notifyObservers();

                    if (!fboard.checkFloorCollision(currentPent,
                            board.getLocation())) {
                        Game.this.timer.schedule(new MoveDown(), dropSpeed);
                    }
                }
            }

            if (!fboard.checkFloorCollision(currentPent, board.getLocation())
                    && firstMove) {
                

                timer.schedule(new MoveDown(), dropSpeed);
                firstMove = false;
            }

            if (fboard.checkFloorCollision(currentPent, board.getLocation())) {
                // put pentomino on the final board
                if (board.getExtraTurn() == 0) {
                    try {
                        Thread.sleep(500);
                        if (!fboard.checkFloorCollision(currentPent, board.getLocation())
                                && firstMove) {
                            timer.schedule(new MoveDown(), dropSpeed);
                            firstMove = false;
                        }
                        moveCurrentPentDown();
                        
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                board.setExtraTurn(1);
                fboard.putPentomino(currentPent, board.getLocation());
                board.setExtraTurn(0);

                firstMove = true;
                newPent = true;
                timer.cancel();
                timer.purge();
                timer = new Timer();

            }
            // Delete rows and count score
            ArrayList<Integer> rowsToRemove = new ArrayList<Integer>();
            for (int row = 0; row < board.getHeight(); row++) {
                if (fboard.checkFullRow(row)) {
                    rowsToRemove.add(row);
                }
            }
            if (!rowsToRemove.isEmpty()) {
                deleteRows(rowsToRemove);
            }

        }
    }

    private boolean checkGameOver() {
        return fboard.checkHitCeiling();
    }
}
