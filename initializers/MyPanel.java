package initializers;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	public MyPanel(int width, int height) {
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.green);
		this.setFocusable(true);
		this.setLayout(null);
	}
}
