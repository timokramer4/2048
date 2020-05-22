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
import javax.swing.border.Border;

public class Game extends JFrame implements KeyListener {

	private JLabel fields[];

	/* Scoreboard JLabel erstellen und positionieren */
	private JPanel scoreboard;
	private JLabel scoreLabel = new JLabel("Score: ", JLabel.CENTER);
	private JLabel scoreValue = new JLabel("0", JLabel.CENTER);

	private JPanel game;
	private Logic logic;

	/* Konstruktor - Initialisieren aller Variablen */
	public Game() {
		fields = new JLabel[16];
		scoreLabel = new JLabel("Score: ", JLabel.CENTER);
		scoreValue = new JLabel("0", JLabel.CENTER);

		// Spielfeldbenennung und BoyLayout an der Y-Achse anlegen.
		this.setTitle("2048");
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.addKeyListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// erstellen des scoreboards
		scoreboard = new JPanel();
		scoreboard.add(scoreLabel);
		scoreboard.add(scoreValue);

		// Spielfeldgröße festlegen und Übergabeparameter an Klasse Logic festlegen
		game = new JPanel(new GridLayout(4, 4));
		logic = new Logic(fields, scoreValue);
	}

	/*
	 * Farbe für das Spielfeldlayout festlegen und Schriftart und Schriftgröße des
	 * Scores hinzufügen
	 */
	private void createAndShow() {
		Border defaultBorder = BorderFactory.createLineBorder(Color.GRAY);

		scoreLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
		scoreValue.setFont(new Font("Open Sans", Font.PLAIN, 20));

		/* hinzufügen vom scoreboard zum Spielfeld */
		add(scoreboard);

		/* Style der Kästchen des Spielfeldes festlegen */
		for (int i = 0; i < 16; i++) {
			fields[i] = new JLabel("", JLabel.CENTER);
			fields[i].setBorder(defaultBorder);
			fields[i].setFont(new Font("Open Sans", Font.PLAIN, 30));
//			fields[i].setOpaque(true);
			game.add(fields[i]);
		}

		logic.draw();

		/*
		 * Spiel zum Spielfeld hinzufügen und eine preferierte Größe des Spielfeldes
		 * beim Starten festlegen
		 */
		game.setPreferredSize(new Dimension(480, 400));
		this.add(game);

		// Ausführen der Methode um random eine neue 2 im Spielfeld zu schreiben
		logic.createNewField();

		this.pack();
		this.setVisible(true);
	}

	/* Main - startet neue Spiel Instanz */
	public static void main(String[] args) {
		Game game = new Game();
		game.createAndShow();
	}

	/*
	 * KeyEvent Listener
	 */

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			logic.moveUp();
			break;
		case KeyEvent.VK_DOWN:
			logic.moveDown();
			break;
		case KeyEvent.VK_LEFT:
			logic.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
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
