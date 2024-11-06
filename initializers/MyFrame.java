package initializers;

import java.awt.Color;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
    protected MyPanel myPanel;

    public MyFrame(int x, int y, int width, int height) {
        this.setTitle("Cell");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.myPanel = new MyPanel(width, height);
        this.add(myPanel);
        this.setUndecorated(true);
        this.pack();
        this.setLayout(null);
        this.setVisible(false);// Makes init simpler
        this.setLocation(x, y);
    }
}
