package _2048;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Scoreboard extends JPanel {
	private JLabel scoreLabel;
	private JLabel scoreValue;
	
	Scoreboard(){
		scoreLabel = new JLabel("Score:");
		scoreLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
		this.add(scoreLabel);
		scoreValue = new JLabel("0");
		scoreValue.setFont(new Font("Open Sans", Font.PLAIN, 20));
		this.add(scoreValue);
	}
	
	public int getScore() {
		return Integer.parseInt(this.scoreValue.getText());
	}
	
	public void setScore(int score) {
		this.scoreValue.setText(Integer.toString(score));
	}
}
