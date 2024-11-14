package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
    private JLabel scoreboard;
    public MyPanel(int width, int height, Color color, Shared shared, String type) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(color);
        this.setFocusable(true);
        this.setLayout(null);
        if (type == "head") {
            this.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP && shared.direction() != 2) {
                        shared.setDirection(1);
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN && shared.direction() != 1) {
                        shared.setDirection(2);
                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT && shared.direction() != 4) {
                        shared.setDirection(3);
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && shared.direction() != 3) {
                        shared.setDirection(4);
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }

            });
        }
    }
}
