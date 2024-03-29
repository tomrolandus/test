import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UI_stan extends JFrame implements Observer {
    private static String userName;
    private static Game game;
    int width = 5;
    private static UI_stan ex;
    JPanel[][] panels = new JPanel[15][width];
    JLabel score;
    static JLabel[] highScores = new JLabel[10];

    public static Game getGame() {
        return game;
    }

    public static UI_stan getEx() {
        return ex;
    }

    public static JLabel[] getHighScores() {
        return highScores;
    }

    public static void setEx(UI_stan ex) {
        UI_stan.ex = ex;
    }

    public static void setGame(Game game) {
        UI_stan.game = game;
    }

    public static void setHighScores(JLabel[] highScores) {
        UI_stan.highScores = highScores;
    }
    
    

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        UI_stan.userName = userName;
    }
    
    public UI_stan() {
        KeyListener listener = new MyKeyListener();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(listener);
        setFocusable(true);
        initUI();
    }

    private void initUI() {
        
        Container contentPane = getContentPane();
        
        
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel(new GridLayout(0, width));

        if (game != null) {
            for (int row = 0; row < panels.length; row++) {
                for (int col = 0; col < panels[row].length; col++) {
                    panels[row][col] = new JPanel();
                }
            }

            for (int row = 0; row < panels.length; row++) {
                for (int col = 0; col < panels[row].length; col++) {
                    panels[row][col].setBackground(new Color(game
                            .getFinalBoard().getFinalBoard()[row][col] % 255));
                }
            }

            for (int row = 0; row < panels.length; row++) {
                for (int col = 0; col < panels[row].length; col++) {
                    panel.add(panels[row][col]);
                }
            }
        }

        score = new JLabel("Score: 0");

        JPanel scorePanel = new JPanel(new GridLayout(0, 1));
        for (int i = 0; i < 10; i++) {
            highScores[i] = new JLabel(i + ". ");
            scorePanel.add(highScores[i]);
        }
        JPanel gameScore = new JPanel(new GridLayout(0, 2));

        main.add(score);
        gameScore.add(panel);
        gameScore.add(scorePanel);
        main.add(gameScore);
        contentPane.add(main);
        
        setTitle("Pentris");
        setSize(500, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        if (args.length !=0 && !args[0].isEmpty()){
            userName = args[0];
        }else{
            userName = "Default User";
        }
        HighScore h = new HighScore();
        game = new Game();
        ex = new UI_stan();
        game.addObserver(ex);
        ex.setVisible(true);
        System.out.println("size = " + h.getSize());
        for (int i = 0; i < Math.min(10, h.getSize()); i++) {
            highScores[i].setText(h.getScore(i)[0] + h.getScore(i)[1]);
        }
        game.start();
        while (true) {
            h.addScore(userName, game.getScore());
            h.saveToFile();
            //System.out.println(game.getScore() + " = score");
            
            System.out.println("size = " + h.getSize());
            for (int i = 0; i < Math.min(10, h.getSize()); i++) {
                highScores[i].setText(h.getScore(i)[0] + " " + h.getScore(i)[1]);
            }
            game = new Game();
            game.addObserver(ex);
            game.start();
            //System.out.println("lkdsalkdsalksj");

            ex.repaint();

        }
    }

    public Color getColor(char type) {
        switch (type) {
            default:
                return Color.white;
            case 'f':
                return Color.red;
            case 'p':
                return Color.blue;
            case 'x':
                return Color.green;
            case 'v':
                return Color.gray;
            case 'y':
                return Color.magenta;
            case 'i':
                return Color.orange;
            case 't':
                return Color.pink;
            case 'z':
                return Color.yellow;
            case 'u':
                return Color.black;
            case 'n':
                return Color.darkGray;
            case 'l':
                return Color.lightGray;
            case 'w':
                return Color.cyan;
        }

    }

    public void update(Observable obs, Object obj) {
        if (obs == game) {

            char[][] finalBoard = game.getFinalBoard().getFinalBoard();
            char[][] board = game.getBoard().getGrid();

            for (int row = 0; row < panels.length; row++) {
                for (int col = 0; col < panels[row].length; col++) {
                    if (finalBoard[row][col] != 0) {
                        panels[row][col]
                                .setBackground(getColor(finalBoard[row][col]));
                    } else if (board[row][col] != 0) {
                        panels[row][col]
                                .setBackground(getColor(board[row][col]));
                    } else {
                        panels[row][col].setBackground(getColor((char) 0));
                    }

                }
            }
            score.setText("Score: " + Integer.toString(game.getScore()));
            ex.repaint();
        }
    }

    public class MyKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    game.rotateCurrentPent();
                    break;
                case 13: //enter
                    ex.requestFocus();
                    System.out.println(ex.hasFocus());
                    break;
                case 32: //space bar
                    game.moveCurrentPentDown();
                    break;
                case 8: //space bar
                    game.moveCurrentPentDown();
                    break;
                case KeyEvent.VK_DOWN:
                    game.moveCurrentPentDown();
                    break;
                case KeyEvent.VK_LEFT:
                    game.moveCurrentPentLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    game.moveCurrentPentRight();
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

}
