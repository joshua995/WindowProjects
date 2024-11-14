package snake;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

public class MyFrame extends JFrame {
    private MyPanel myPanel;
    private String type;
    private JLabel scoreboard;

    public MyFrame(int x, int y, int width, int height, Color color, Shared shared, String type) {
        this.setTitle(type);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.myPanel = new MyPanel(width, height, color, shared, type);
        this.add(myPanel);
        this.setUndecorated(true);
        if (type == "scoreboard") {
            scoreboard = new JLabel("Score: 0");
            scoreboard.setFont(new Font("Comic Sans", Font.PLAIN, (int) (width * .25)));
            scoreboard.setBackground(color);
            this.add(scoreboard);
        }
        this.pack();
        this.setLayout(null);
        this.setVisible(true);// Makes init simpler
        this.setLocation(x, y);
        this.type = type;
        if (type == "head") {
            this.setAlwaysOnTop(true);
        } else {
            this.setFocusableWindowState(false);
        }
    }

    public String type() {
        return this.type;
    }

    public JLabel scoreboard() {
        return this.scoreboard;
    }

}
