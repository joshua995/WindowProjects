package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Snake {
    private static List<MyFrame> snake = new ArrayList<>();
    private static MyFrame apple;
    private static MyFrame scoreboard;
    private static Shared shared = new Shared();
    private static int cellSize = 50;
    private static int width, height;
    private static int score = 0;

    public static void main(String[] args) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();// Get screen resolution
        width = (int) size.getWidth() / cellSize;
        height = (int) size.getHeight() / cellSize;

        scoreboard = new MyFrame((int) size.getWidth() - cellSize * 4, 0, cellSize * 3, cellSize, Color.blue, shared,
                "scoreboard");

        apple = new MyFrame(new Random().nextInt(0, width) * cellSize, new Random().nextInt(0, height) * cellSize,
                cellSize, cellSize, Color.red, shared, "apple");
        snake.add(new MyFrame(new Random().nextInt(0, width) * cellSize, new Random().nextInt(0, height) * cellSize,
                cellSize, cellSize, Color.blue, shared, "head"));
        while (!shared.isGameOver()) {
            moveSnake();
            checkCollision();
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 150)
                ;
        }
        snake.get(0).removeKeyListener(snake.get(0).getKeyListeners()[0]);
        for (Window win : Window.getWindows()) {
            win.dispose();
        }
    }

    private static void moveSnake() {
        for (int i = snake.size() - 1; i >= 0; i--) {
            if (i == 0) {
                if (shared.direction() == 1) {
                    snake.get(i).setLocation((int) (snake.get(i).getX()), (int) (snake.get(i).getY() - cellSize));
                } else if (shared.direction() == 2) {
                    snake.get(i).setLocation((int) (snake.get(i).getX()), (int) (snake.get(i).getY() + cellSize));
                } else if (shared.direction() == 3) {
                    snake.get(i).setLocation((int) (snake.get(i).getX() - cellSize), (int) (snake.get(i).getY()));
                } else if (shared.direction() == 4) {
                    snake.get(i).setLocation((int) (snake.get(i).getX() + cellSize), (int) (snake.get(i).getY()));
                }
            } else
                snake.get(i).setLocation(snake.get(i - 1).getLocation());
        }
    }

    private static void checkCollision() {
        if (snake.get(0).getLocation().equals(apple.getLocation())) {
            addBody();
        } else {
            for (MyFrame frame : snake) {
                if (frame.type() == "body") {
                    if (frame.getLocation().equals(snake.get(0).getLocation())) {
                        System.out.println("Game over");
                        shared.setIsGameOver(true);
                    }
                }
            }
        }

    }

    private static void addBody() {
        snake.add(
                new MyFrame(snake.get(snake.size() - 1).getX(), snake.get(snake.size() - 1).getY(), cellSize, cellSize,
                        Color.green, shared, "body"));
        apple.setLocation(new Random().nextInt(0, width) * cellSize, new Random().nextInt(0, height) * cellSize);
        score++;
        scoreboard.scoreboard().setText("Score: " + score);
    }
}
