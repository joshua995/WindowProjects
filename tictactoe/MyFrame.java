package tictactoe;

import javax.swing.JFrame;

import initializers.MyPanel;
import initializers.Shared;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyFrame extends JFrame {
    protected MyPanel myPanel;
    private int moveSize;
    private int startingPos;
    private Shared shared;
    private int whichPlayer;

    public MyFrame(int x, int y, int width, int height, Shared shared) {
        this.setTitle("Cell");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.myPanel = new MyPanel(width, height);
        this.add(myPanel);
        this.setUndecorated(true);
        this.pack();
        this.setLayout(null);
        this.setVisible(true);
        this.setLocation(x, y);
        this.moveSize = (int) (width * 0.75);
        this.startingPos = (width - this.moveSize) / 2;
        this.shared = shared;
        this.whichPlayer = 0;
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!shared.isSimulationOn()) {
                    removeMouseListener(this);
                    return;
                }
                if (shared.isPlayer1()) {
                    whichPlayer = 1;
                    paint(getGraphics(), true);
                } else {
                    whichPlayer = 2;
                    paint(getGraphics(), false);
                }
                shared.setIsPlayer1(!shared.isPlayer1());
                removeMouseListener(this);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
    }

    void drawCircle(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.drawOval(startingPos, startingPos, this.moveSize, this.moveSize);
    }

    void drawRectangle(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.drawRect(startingPos, startingPos, this.moveSize, this.moveSize);
    }

    public void paint(Graphics g, boolean isCircle) {
        super.paint(g);
        if (isCircle)
            drawCircle(g);
        else
            drawRectangle(g);
    }

    public int whichPlayer() {
        return this.whichPlayer;
    }

}
