package _2048;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class MainWindow extends JFrame implements KeyListener {

	private GameField gameField;
	private static MainWindow singleton;

	/* Konstruktor - Initialisieren aller Variablen */
	private MainWindow() {
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
		MainWindow.createInstance();
	}

	/* Erstellung einer neuen Singleton-Instanz */
	public static MainWindow createInstance() {
		if (singleton != null) {
			singleton.dispose();
		}
		singleton = new MainWindow();
		return singleton;
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
/*
 * Statt wie in unserem Minesweeper das Spielfeld zu zeichnen habe ich mich
 * informiert ob es eine Möglichkeit gibt einafche Felder in einem Spielfeld
 * anzulegen. Dann bin ich druch ein paar Forem im Internet auf das sogennante
 * BoxLayout gestoßen. Mir war am Anfang meiner Recherche nicht klar ob ich es
 * Sinn macht ein Boxlayout zu verwenden. Also habe ich mich erstmal daran
 * gemacht die Logik des Spieles auf einem Blatt Papier zu erstellen und die
 * Bedingungen festzulegen. Schnell stellte sich die Frage, wie ich nach dem
 * "Verschieben" der Zahlen in eine bestimmte Richtung, die Felder wieder in die
 * weiße Ursprungsfarbe bekomme und der Inhalt des Feldes entfernt wird. Da ich
 * durch das BoyLayout sehr einfach auf die einzelnen Felder in dem Spielfeld
 * verweisen kann, konnte ich mir sparen mit komplizierteren draw Funktionen zu
 * arbeiten.
 */
