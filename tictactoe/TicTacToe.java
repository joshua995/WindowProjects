/*
 * Joshua Liu
 * November 7, 2024
 * Tic tac toe using JFrame windows
 * Next steps: implement PvP & PvC modes
 */

package tictactoe;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;

import initializers.Shared;

public class TicTacToe {
    private static HashMap<Integer, MyFrame> board = new HashMap<>();
    private static int spotSize = 0;
    private static Shared shared = new Shared();
    private static boolean isGameDrawn = false;

    public static void main(String[] args) {
        initBoard();
        while (shared.isSimulationOn()) {
            if (checkWinner()) {
                shared.setIsSimulationOn(false);
                break;
            } else if (isGameDrawn = isDraw()) {
                break;
            }
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 10)
                ;
        }
        if (!isGameDrawn) {
            System.out.println(!shared.isPlayer1() ? "Player 1 won" : "Player 2 won");
        } else {
            System.out.println("Game is drawn");
        }
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 3000)
            ;
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

    static boolean checkWinner() {
        if ((board.get(0).whichPlayer() == board.get(1).whichPlayer()
                && board.get(1).whichPlayer() == board.get(2).whichPlayer() && board.get(2).whichPlayer() != 0)
                || (board.get(3).whichPlayer() == board.get(4).whichPlayer()
                        && board.get(4).whichPlayer() == board.get(5).whichPlayer()
                        && board.get(5).whichPlayer() != 0)
                || (board.get(6).whichPlayer() == board.get(7).whichPlayer()
                        && board.get(7).whichPlayer() == board.get(8).whichPlayer()
                        && board.get(8).whichPlayer() != 0)
                || (board.get(0).whichPlayer() == board.get(3).whichPlayer()
                        && board.get(3).whichPlayer() == board.get(6).whichPlayer()
                        && board.get(6).whichPlayer() != 0)
                || (board.get(1).whichPlayer() == board.get(4).whichPlayer()
                        && board.get(4).whichPlayer() == board.get(7).whichPlayer()
                        && board.get(7).whichPlayer() != 0)
                || (board.get(2).whichPlayer() == board.get(5).whichPlayer()
                        && board.get(5).whichPlayer() == board.get(8).whichPlayer()
                        && board.get(8).whichPlayer() != 0)
                || (board.get(0).whichPlayer() == board.get(4).whichPlayer()
                        && board.get(4).whichPlayer() == board.get(8).whichPlayer()
                        && board.get(8).whichPlayer() != 0)
                || (board.get(2).whichPlayer() == board.get(4).whichPlayer()
                        && board.get(4).whichPlayer() == board.get(6).whichPlayer()
                        && board.get(6).whichPlayer() != 0)) {
            return true;
        }
        return false;
    }

    static boolean isDraw() {
        for (MyFrame frame : board.values()) {
            if (frame.whichPlayer() == 0) {
                return false;
            }
        }
        return true;
    }
}
