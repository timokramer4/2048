package _2048;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Scoreboard extends JPanel {
	private JLabel scoreLabel;
	private JLabel scoreValue;
	
	//Setzen vom Score und dem Wert von Score.
	Scoreboard(){
		scoreLabel = new JLabel("Score:");
		scoreLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
		this.add(scoreLabel);
		scoreValue = new JLabel("0");
		scoreValue.setFont(new Font("Open Sans", Font.PLAIN, 20));
		this.add(scoreValue);
	}
	//get Methode für den Score
	public int getScore() {
		return Integer.parseInt(this.scoreValue.getText());
	}
	//set Methode für den Score
	public void setScore(int score) {
		this.scoreValue.setText(Integer.toString(score));
	}
}
