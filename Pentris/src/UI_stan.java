import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UI_stan extends JFrame implements Observer {
	private static Game game;
	int width = 5;
	private static UI_stan ex;
	JPanel[][] panels = new JPanel[15][width];
	JLabel score;

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
		JPanel scorePanel = new JPanel();
		JPanel panel = new JPanel(new GridLayout(0, width));
		
		if (game != null) {
			for (int row = 0; row < panels.length; row++)
				for (int col = 0; col < panels[row].length; col++)
					panels[row][col] = new JPanel();

			for (int row = 0; row < panels.length; row++)
				for (int col = 0; col < panels[row].length; col++)
					panels[row][col].setBackground(new Color(game
							.getFinalBoard().getFinalBoard()[row][col] % 255));

			for (int row = 0; row < panels.length; row++)
				for (int col = 0; col < panels[row].length; col++)
					panel.add(panels[row][col]);
		}
		
		score = new JLabel("Score: 0");
		
		main.add(score);
		main.add(panel);
		contentPane.add(main);

		setTitle("Pentris");
		setSize(200, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		game = new Game();
		ex = new UI_stan();
		game.addObserver(ex);
		ex.setVisible(true);
		game.start();
		while(true){
			game = new Game();
			game.addObserver(ex);
			game.start();
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

			for (int row = 0; row < panels.length; row++)
				for (int col = 0; col < panels[row].length; col++) {
					if (finalBoard[row][col] != 0)
						panels[row][col]
								.setBackground(getColor(finalBoard[row][col]));
					else if (board[row][col] != 0)
						panels[row][col]
								.setBackground(getColor(board[row][col]));
					else
						panels[row][col].setBackground(getColor((char) 0));

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
