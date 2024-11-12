package snake;

import javax.swing.JFrame;

import java.awt.Color;

public class MyFrame extends JFrame {
    protected MyPanel myPanel;
    private String type;

    public MyFrame(int x, int y, int width, int height, Color color, Shared shared, String type) {
        this.setTitle(type);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.myPanel = new MyPanel(width, height, color, shared, type);
        this.add(myPanel);
        this.setUndecorated(true);
        this.pack();
        this.setLayout(null);
        this.setVisible(true);// Makes init simpler
        this.setLocation(x, y);
        this.type = type;
        if (type == "head") {
           // this.setAlwaysOnTop(true);
        } else {
            this.setFocusableWindowState(false);
        }
    }

    public String type() {
        return this.type;
    }

}
