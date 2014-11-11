import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UI_stan extends JFrame implements Observer {
	private static Game game;
	int width = 5;
	private static UI_stan ex;
	JPanel[][] panels = new JPanel[15][width];

	public UI_stan() {
		game = new Game();
		initUI();
		game.addObserver(this);

		KeyListener listener = new MyKeyListener();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(listener);
		setFocusable(true);
	}

	private void initUI() {

		Container contentPane = getContentPane();

		JPanel panel = new JPanel(new GridLayout(0, width));

		//initiate panels
		for (int row = 0; row < panels.length; row++)
			for (int col = 0; col < panels[row].length; col++)
				panels[row][col] = new JPanel();

		//initiate colors
		for (int row = 0; row < panels.length; row++)
			for (int col = 0; col < panels[row].length; col++)
				panels[row][col].setBackground(new Color(game.getFinalBoard()
						.getGrid()[row][col] % 255));

		//add panels to bigger panel
		for (int row = 0; row < panels.length; row++)
			for (int col = 0; col < panels[row].length; col++)
				panel.add(panels[row][col]);

		//add bigger panel to pane
		contentPane.add(panel);

		setTitle("Pentris");
		setSize(100, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		ex = new UI_stan();
		ex.setVisible(true);
		game.start();
	}

	public Color getColor(char type) {
		return new Color(type % 255, type % 255, type % 255);
	}

	public void update(Observable obs, Object obj) {
		if (obs == game) {

			char[][] finalBoard = game.getFinalBoard().getGrid();
			char[][] board = game.getBoard().getGrid();

			for (int row = 0; row < panels.length; row++)
				for (int col = 0; col < panels[row].length; col++) {
					if (finalBoard[row][col] != 0)
						panels[row][col].setBackground(getColor(finalBoard[row][col]));
					else if (board[row][col] != 0)
						panels[row][col].setBackground(getColor(board[row][col]));
					else
						panels[row][col].setBackground(getColor((char)0));

				}
			System.out.println("update");
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
			System.out.println("keyReleased="
					+ KeyEvent.getKeyText(e.getKeyCode()));
		}
	}

}
