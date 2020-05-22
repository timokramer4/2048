package _2048;

import java.awt.Color;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Logic {
	private JLabel[] fields;
	private boolean gameRunning = true;
	private int score = 0;
	private Scoreboard scoreboard;

	/* Konstruktor - Initialisieren aller Variablen in der Klasse */
	public Logic(JLabel[] fields, Scoreboard scoreboard) {
		this.fields = fields;
		this.scoreboard = scoreboard;
	}

	/*
	 * Methode um das Spielfeld zu zeichnen und um der in einem Feld stehenden Zahl
	 * die zugeh�rige Farbe zuzuteilen.
	 */
	public void draw() {
		for (int n = 0; n < 16; n++) {
			fields[n].setBackground(getFieldColor(n));
		}
	}

	/*
	 * Definieren der spezifischen Farbe für den Inhalt der einzelnen FElder im
	 * Spielfeld
	 */
	public Color getFieldColor(int n) {

		Color tempColor;

		/* Leere Felder bleiben weiß */
		if (fields[n].getText().equals("")) {
			tempColor = Color.WHITE;
			/* Felder mit einer Zahl werden farblich dargestellt */
		} else {
			int fieldnumber = Integer.parseInt(fields[n].getText());

			switch (fieldnumber) {
			case 2:
				tempColor = new Color(141, 90, 151);
				break;
			case 4:
				tempColor = new Color(128, 155, 206);
				break;
			case 8:
				tempColor = new Color(254, 147, 140);
				break;
			case 16:
				tempColor = new Color(127, 229, 203);
				break;
			case 32:
				tempColor = new Color(226, 207, 234);
				break;
			case 64:
				tempColor = new Color(244, 97, 151);
				break;
			case 128:
				tempColor = new Color(78, 205, 196);
				break;
			case 256:
				tempColor = new Color(215, 255, 171);
				break;
			case 512:
				tempColor = new Color(239, 123, 69);
				break;
			case 1024:
				tempColor = new Color(197, 174, 135);
				break;
			case 2048:
				tempColor = new Color(183, 110, 121);
				break;
			default:
				tempColor = new Color(216, 226, 220);
				break;
			}
		}
		return tempColor;
	}

	/* Erstellen einer 2 nach jeder Bewegung random in einem leeren Feld */
	public void createNewField() {
		if (gameRunning == true) {
			Random rnd = new Random();
			int index;

			// es wird solange nach einem freien Feld gesucht, bis eins gefunden wurde.
			do {
				index = rnd.nextInt(16);
			} while (!(fields[index].getText().equals("")));
			// eine 2 wird in das leere Feld geschrieben
			fields[index].setText(Integer.toString((int) 2));

			// Hintergrundfarbe wird gesetzt.
			fields[index].setBackground(getFieldColor(index));
		}
	}

	/* Methode um alle Zahlen in den Kästchen nach links zu bewegen */
	public void moveLeft() {
		// Das Spiel muss laufen um die folgenden Bedingungen auszuführen
		if (gameRunning == true) {
			for (int i = 0; i < 4; i++) {
				for (int k = 4 * i + 1; k < 4 * i + 4; k++) {
					for (int n = k - 1; n >= 4 * i; n--) {

						/*
						 * Checken ob das nächste Feld leer ist oder die gleiche Zahl im folgenden Feld
						 * steht
						 */
						if (fields[n].getText().equals("")) {
							fields[n].setText(fields[n + 1].getText());
							fields[n + 1].setText("");

							/*
							 * Hintergrundfarbe in dem neues Feld der Zahl angepasst setzen und in dem
							 * leeren Feld wieder die weiße Farbe setzen
							 */
							fields[n].setBackground(getFieldColor(n));
							fields[n + 1].setBackground(getFieldColor(n + 1));
						} else if (fields[n].getText().equals(fields[n + 1].getText())) {
							// Duplicate the value and remove the second field
							fields[n].setText(Integer.toString((Integer.parseInt(fields[n].getText()) * 2)));
							fields[n + 1].setText("");

							// Set background color
							fields[n].setBackground(getFieldColor(n));
							fields[n + 1].setBackground(getFieldColor(n + 1));

							// Score um die neu dazugekommene Zahl erhöhen
							score += Integer.parseInt(fields[n].getText());
						}
					}
				}
			}

			refresh();
		}
	}

	/* Move all available patterns right */
	public void moveRight() {
		// Only in running mode
		if (gameRunning == true) {
			for (int i = 0; i < 4; i++) {
				for (int k = 4 * i + 3; k >= 4 * i; k--) {
					for (int n = k + 1; n <= 4 * i + 3; n++) {

						// Check whether the next field is empty or has the same value
						if (fields[n].getText().equals("")) {
							fields[n].setText(fields[n - 1].getText());
							fields[n - 1].setText("");

							// Set background color
							fields[n].setBackground(getFieldColor(n));
							fields[n - 1].setBackground(getFieldColor(n - 1));
						} else if (fields[n].getText().equals(fields[n - 1].getText())) {
							// Duplicate the value and remove the second field
							fields[n].setText(Integer.toString((Integer.parseInt(fields[n].getText()) * 2)));
							fields[n - 1].setText("");

							// Set background color
							fields[n].setBackground(getFieldColor(n));
							fields[n - 1].setBackground(getFieldColor(n - 1));

							// Score um die neu dazugekommene Zahl erhöhen
							score += Integer.parseInt(fields[n].getText());
						}
					}
				}
			}

			refresh();
		}
	}

	/* Move all available patterns up */
	public void moveUp() {
		// Only in running mode
		if (gameRunning == true) {
			for (int i = 12; i < 16; i++) {
				for (int k = i - 8; k <= i; k += 4) {
					for (int n = k - 4; n >= i - (4 * 3); n -= 4) {

						// Check whether the next field is empty or has the same value
						if (fields[n].getText().equals("")) {
							fields[n].setText(fields[n + 4].getText());
							fields[n + 4].setText("");

							// Set background color
							fields[n].setBackground(getFieldColor(n));
							fields[n + 4].setBackground(getFieldColor(n + 4));

						} else if (fields[n].getText().equals(fields[n + 4].getText())) {
							// Duplicate the value and remove the second field
							fields[n].setText(Integer.toString((Integer.parseInt(fields[n].getText()) * 2)));
							fields[n + 4].setText("");

							// Set background color
							fields[n].setBackground(getFieldColor(n));
							fields[n + 4].setBackground(getFieldColor(n + 4));

							// Score um die neu dazugekommene Zahl erhöhen
							score += Integer.parseInt(fields[n].getText());
						}
					}
				}
			}

			refresh();
		}
	}

	/* Move all available patterns down */
	public void moveDown() {
		// Only in running mode
		if (gameRunning == true) {
			for (int i = 0; i < 4; i++) {
				for (int k = i + 8; k >= i; k -= 4) {
					for (int n = (k + 4); n <= i + 12; n += 4) {

						// Check whether the next field is empty or has the same value
						if (fields[n].getText().equals("")) {
							fields[n].setText(fields[n - 4].getText());
							fields[n - 4].setText("");

							// Set background color
							fields[n].setBackground(getFieldColor(n));
							fields[n - 4].setBackground(getFieldColor(n - 4));

						} else if (fields[n].getText().equals(fields[n - 4].getText())) {
							// Duplicate the value and remove the second field
							fields[n].setText(Integer.toString((Integer.parseInt(fields[n].getText()) * 2)));
							fields[n - 4].setText("");

							// Set background color
							fields[n].setBackground(getFieldColor(n));
							fields[n - 4].setBackground(getFieldColor(n - 4));

							// Score um die neu dazugekommene Zahl erhöhen
							score += Integer.parseInt(fields[n].getText());
						}
					}
				}
			}

			refresh();
		}
	}

	/* Refresh the UI and check running state */
	private void refresh() {
		gameOver();
		createNewField();

		// Score updaten
		scoreboard.setScore(score);
	}

	/* Checken ob das Spiel vorbei ist */
	private void gameOver() {
		// Zähler für die freien Felder
		int counter = 0;

		// Die Anzahl an freien Feldern checken und zählen
		for (int i = 0; i < 16; i++) {
			if (fields[i].getText().equals("")) {
				counter++;
			}
		}

		/*
		 * Wenn der counter 0 ist, es also keine freien Felder mehr gibt, dann wird der
		 * boolean für daslaufende Spiel auf false gesetzt und der erreichte Score und
		 * Game Over auf dem Bildschirm ausgegeben.
		 */
		if (counter == 0) {
			gameRunning = false;

			// Message Game Over
			JOptionPane.showMessageDialog(null, "Game Over\nScore: " + score);

			// Game beenden
			System.exit(0);
		}
	}
}
