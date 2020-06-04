package _2048;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameField extends JPanel {

	private JLabel fields[];
	public Logic logic;

	// Kontruktor - Aufbau des Spielfeldes
	GameField(Scoreboard scoreboard) {
		this.setLayout(new GridLayout(4, 4));
		fields = new JLabel[16];
		logic = new Logic(fields, scoreboard);

		/* Style der Kaestchen des Spielfeldes festlegen */
		for (int i = 0; i < 16; i++) {
			fields[i] = new JLabel("", JLabel.CENTER);
			fields[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			fields[i].setFont(new Font("Open Sans", Font.PLAIN, 30));
			//Farben sichtbar
			fields[i].setOpaque(true);
			this.add(fields[i]);
		}
		// Ausführen der Draw Methode
		logic.draw();

		/*
		 * Spiel zum Spielfeld hinzufuegen und eine preferierte Groesse des Spielfeldes
		 * beim Starten festlegen
		 */
		this.setPreferredSize(new Dimension(480, 400));

		// Ausfuehren der Methode um random eine neue 2 im Spielfeld zu schreiben
		logic.createNewField();
	}
}
