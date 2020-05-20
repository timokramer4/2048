package _2048;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Game extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel patterns[];

	private JPanel scoreboard;
	private JLabel scoreLabel = new JLabel("Score: ", SwingConstants.CENTER);
	private JLabel scoreValue = new JLabel("0", SwingConstants.CENTER);

	private JPanel game;
	private Logic logic;

	/* Default constructor - Initialize all required variables */
	public Game() {
		patterns = new JLabel[16];
		scoreLabel = new JLabel("Score: ", SwingConstants.CENTER);
		scoreValue = new JLabel("0", SwingConstants.CENTER);
		
		// Set Frame properties
		this.setTitle("2048");
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.addKeyListener(this);
		
		// Create scoreboard panel
		scoreboard = new JPanel();
		scoreboard.add(scoreLabel);
		scoreboard.add(scoreValue);
		
		// Create game panel
		game = new JPanel(new GridLayout(4, 4));
		logic = new Logic(patterns, scoreValue, this);
	}

	/* Create and render UI */
	private void createAndShowGUI() {
		Border defaultBorder = BorderFactory.createLineBorder(Color.GRAY, 1);

		scoreLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
		scoreValue.setFont(new Font("Open Sans", Font.PLAIN, 20));

		/* Add scoreboard to frame */
		add(scoreboard);

		/* Set styles for patterns */
		for (int i = 0; i < 16; i++) {
			patterns[i] = new JLabel("", SwingConstants.CENTER);
			patterns[i].setBorder(defaultBorder);
			patterns[i].setFont(new Font("Open Sans", Font.PLAIN, 30));
			patterns[i].setOpaque(true);
			game.add(patterns[i]);
		}

		logic.draw();

		/* Add game to frame */
		game.setPreferredSize(new Dimension(480, 400));
		add(game);

		// Generate a new number at random location.
		logic.createNewField();
		
		this.pack();
		this.setVisible(true);
	}

	/* Main functionality - start new game instance */
	public static void main(String[] args) {
		Game game = new Game();
		game.createAndShowGUI();
	}

	
	/*
	 * KeyEvent Listener
	 */
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Hoch");
			logic.moveUp();
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("Runter");
			logic.moveDown();
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("Links");
			logic.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("Rechts");
			logic.moveRight();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

}
