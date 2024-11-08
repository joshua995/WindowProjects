package initializers;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	public MyPanel(int width, int height, Color color) {
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(color);
		this.setFocusable(true);
		this.setLayout(null);
	}
}
