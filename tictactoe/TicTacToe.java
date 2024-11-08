package tictactoe;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;

import initializers.Shared;

public class TicTacToe {
    private static HashMap<Integer, MyFrame> board = new HashMap<>();
    private static int spotSize = 0;
    private static Shared shared = new Shared();

    public static void main(String[] args) {
        initBoard();
        while (shared.isSimulationOn()) {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 10)
                ;
        }
        // Clean up
        for (MyFrame frame : board.values()) {
            frame.dispose(); // Close all windows
        }
    }

    static void initBoard() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();// Get screen resolution

        // width will store the width of the screen
        int width = (int) size.getWidth();
        // height will store the height of the screen
        int height = (int) size.getHeight();
        spotSize = (height - (int) (height * 0.2)) / 3;

        int startingX = (int) ((width - (spotSize * 3)) / 2);
        int startingY = (int) (height * 0.1);

        int i = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board.put(i, new MyFrame(startingX + col * spotSize, startingY + row * spotSize, spotSize, spotSize,
                        shared));
                i++;
            }
        }
    }


}
