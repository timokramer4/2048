package _2048;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class MainWindow extends JFrame implements KeyListener {
	
	private GameField gameField;

	/* Konstruktor - Initialisieren aller Variablen */
	public MainWindow() {
		// Spielfeldbenennung und BoxLayout an der Y-Achse anlegen
		this.setTitle("2048");
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.addKeyListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Neue Instanz von Scoreboard erzeugen
		Scoreboard scoreboard = new Scoreboard();
		
		// Neues Spielfdeld erstellen
		gameField = new GameField(scoreboard);		
		
		/* Hinzufuegen der einzelnen Elemente */
		this.add(scoreboard);
		this.add(gameField);

		this.pack();
		this.setVisible(true);
	}

	/* Main - startet neue Spiel Instanz */
	public static void main(String[] args) {
		MainWindow game = new MainWindow();
	}

	/*
	 * KeyEvent Listener
	 */

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			gameField.logic.moveUp();
			break;
		case KeyEvent.VK_DOWN:
			gameField.logic.moveDown();
			break;
		case KeyEvent.VK_LEFT:
			gameField.logic.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			gameField.logic.moveRight();
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
