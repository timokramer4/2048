package _2048;

import java.awt.Color;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Logic {
	private JLabel[] patterns;
	private boolean running = true;
	private int score = 0;
	private JLabel scoreValue;
	private Game frame;

	/* Default constructor */
	public Logic(JLabel[] patterns, JLabel score, Game game) {
		this.patterns = patterns;
		this.scoreValue = score;
		this.frame = game;
	}

	/* Draw the game field */
	public void draw() {
		for (int n = 0; n < 16; n++) {
			patterns[n].setBackground(getFieldColor(n));
		}
	}

	/* Get specific color of block with index n */
	public Color getFieldColor(int n) {

		Color tempColor;

		/* Empty field are white and filled patterns are colored */
		if (patterns[n].getText().equals("")) {
			tempColor = Color.WHITE;
		} else {
			int num = Integer.parseInt(patterns[n].getText());

			switch (num) {
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

	/* Create new random field after movement */
	public void createNewField() {
		if (running == true) {
			int key = getEmptyRandomField();
			patterns[key].setText(Integer.toString((int) 2));

			// Set background color.
			patterns[key].setBackground(getFieldColor(key));
		}
	}

	/* Get empty field random */
	public int getEmptyRandomField() {
		Random gen = new Random();
		int key;

		// Until a free field was found
		do {
			key = gen.nextInt(16);
		} while (!(patterns[key].getText().equals("")));

		// Return the index of the random field
		return key;
	}

	/* Move all available patterns left */
	public void moveLeft() {
		// Only in running mode
		if (running == true) {
			for (int i = 0; i < 4; i++) {
				for (int k = 4 * i + 1; k < 4 * i + 4; k++) {
					for (int n = k - 1; n >= 4 * i; n--) {
						
						// Check whether the next field is empty or has the same value
						if (patterns[n].getText().equals("")) {
							patterns[n].setText(patterns[n + 1].getText());
							patterns[n + 1].setText("");

							// Set background color
							patterns[n].setBackground(getFieldColor(n));
							patterns[n + 1].setBackground(getFieldColor(n + 1));
						} else if (patterns[n].getText().equals(patterns[n + 1].
								getText())) {
							// Duplicate the value and remove the second field
							patterns[n].setText(Integer.toString((Integer.parseInt
									(patterns[n].getText()) * 2)));
							patterns[n + 1].setText("");

							// Set background color
							patterns[n].setBackground(getFieldColor(n));
							patterns[n + 1].setBackground(getFieldColor(n + 1));

							// Increase score
							score += Integer.parseInt(patterns[n].getText());
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
		if (running == true) {
			for (int i = 0; i < 4; i++) {
				for (int k = 4 * i + 3; k >= 4 * i; k--) {
					for (int n = k + 1; n <= 4 * i + 3; n++) {

						// Check whether the next field is empty or has the same value
						if (patterns[n].getText().equals("")) {
							patterns[n].setText(patterns[n - 1].getText());
							patterns[n - 1].setText("");

							// Set background color
							patterns[n].setBackground(getFieldColor(n));
							patterns[n - 1].setBackground(getFieldColor(n - 1));
						} else if (patterns[n].getText().equals(patterns[n - 1].
								getText())) {
							// Duplicate the value and remove the second field
							patterns[n].setText(Integer.toString((Integer.
									parseInt(patterns[n].getText()) * 2)));
							patterns[n - 1].setText("");

							// Set background color
							patterns[n].setBackground(getFieldColor(n));
							patterns[n - 1].setBackground(getFieldColor(n - 1));

							// Increase score
							score += Integer.parseInt(patterns[n].getText());
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
		if (running == true) {
			for (int i = 12; i < 16; i++) {
				for (int k = i - 8; k <= i; k += 4) {
					for (int n = k - 4; n >= i - (4 * 3); n -= 4) {

						// Check whether the next field is empty or has the same value
						if (patterns[n].getText().equals("")) {
							patterns[n].setText(patterns[n + 4].getText());
							patterns[n + 4].setText("");

							// Set background color
							patterns[n].setBackground(getFieldColor(n));
							patterns[n + 4].setBackground(getFieldColor(n + 4));

						} else if (patterns[n].getText().equals(patterns[n + 4].
								getText())) {
							// Duplicate the value and remove the second field
							patterns[n].setText(Integer.toString((Integer.parseInt
									(patterns[n].getText()) * 2)));
							patterns[n + 4].setText("");

							// Set background color
							patterns[n].setBackground(getFieldColor(n));
							patterns[n + 4].setBackground(getFieldColor(n + 4));

							// Increase score
							score += Integer.parseInt(patterns[n].getText());
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
		if (running == true) {
			for (int i = 0; i < 4; i++) {
				for (int k = i + 8; k >= i; k -= 4) {
					for (int n = (k + 4); n <= i + 12; n += 4) {

						// Check whether the next field is empty or has the same value
						if (patterns[n].getText().equals("")) {
							patterns[n].setText(patterns[n - 4].getText());
							patterns[n - 4].setText("");

							// Set background color
							patterns[n].setBackground(getFieldColor(n));
							patterns[n - 4].setBackground(getFieldColor(n - 4));

						} else if (patterns[n].getText().equals(patterns[n - 4].
								getText())) {
							// Duplicate the value and remove the second field
							patterns[n].setText(Integer.toString((Integer.parseInt
									(patterns[n].getText()) * 2)));
							patterns[n - 4].setText("");

							// Set background color
							patterns[n].setBackground(getFieldColor(n));
							patterns[n - 4].setBackground(getFieldColor(n - 4));

							// Increase score
							score += Integer.parseInt(patterns[n].getText());
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

		// Update Score
		scoreValue.setText(Integer.toString(score));
	}

	/* Check Game Over state */
	private void gameOver() {
		int counter = 0;

		/* Check how many blocks are currently empty. */
		for (int i = 0; i < 16; i++) {
			if (patterns[i].getText().equals("")) {
				counter++;
			}
		}

		// If there is no more empty block.
		if (counter == 0) {
			running = false;

			// Show Game Over message
			JOptionPane.showMessageDialog(null, "Game Over\nScore: " + score);

			// Game exit
			frame.setVisible(false);
			frame.dispose();
		}
	}
}
