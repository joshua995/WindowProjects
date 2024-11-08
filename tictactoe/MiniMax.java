package tictactoe;

import java.util.HashMap;

public class MiniMax {
    int[] boardInt;
    HashMap<Integer, MyFrame> board;

    public MiniMax(int[] boardInt, HashMap<Integer, MyFrame> board) {
        this.boardInt = boardInt;
        this.board = board;
    }

    private boolean checkWinner(int[] boardInt, int playerToCheck) {
        if ((boardInt[0] == boardInt[1] && boardInt[1] == boardInt[2] && boardInt[2] == playerToCheck)
                || (boardInt[3] == boardInt[4] && boardInt[4] == boardInt[5] && boardInt[5] == playerToCheck)
                || (boardInt[6] == boardInt[7] && boardInt[7] == boardInt[8] && boardInt[8] == playerToCheck)
                || (boardInt[0] == boardInt[3] && boardInt[3] == boardInt[6] && boardInt[6] == playerToCheck)
                || (boardInt[1] == boardInt[4] && boardInt[4] == boardInt[7] && boardInt[7] == playerToCheck)
                || (boardInt[2] == boardInt[5] && boardInt[5] == boardInt[8] && boardInt[8] == playerToCheck)
                || (boardInt[0] == boardInt[4] && boardInt[4] == boardInt[8] && boardInt[8] == playerToCheck)
                || (boardInt[2] == boardInt[4] && boardInt[4] == boardInt[6] && boardInt[6] == playerToCheck)) {
            return true;
        }
        return false;
    }

    private boolean isDraw(int[] boardInt) {
        for (int i : boardInt) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }

    public double miniMax(int[] boardInt, int depth, boolean isMaximizing, int maxPlayer, int minPlayer) {
        if (checkWinner(boardInt, maxPlayer))
            return 2;
        else if (checkWinner(boardInt, minPlayer))
            return -2;
        else if (isDraw(boardInt))
            return 0;

        if (isMaximizing) {
            double bestScore = -1;
            for (int i = 0; i < boardInt.length; i++) {
                if (boardInt[i] == 0) {
                    boardInt[i] = maxPlayer;
                    double score = miniMax(boardInt, depth + 1, false, maxPlayer, minPlayer);
                    boardInt[i] = 0;
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        } else {
            double bestScore = 1;
            for (int i = 0; i < boardInt.length; i++) {
                if (boardInt[i] == 0) {
                    boardInt[i] = minPlayer;
                    double score = miniMax(boardInt, depth + 1, true, maxPlayer, minPlayer);
                    boardInt[i] = 0;
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        }
    }

    public void makeBestMove(int maxPlayer, int minPlayer) {
        int[] boardInt = new int[9];
        for (int i = 0; i < 9; i++) {
            boardInt[i] = board.get(i).whichPlayer();
        }
        int moveToMake = -1;
        double bestScore = -1;
        for (int i = 0; i < boardInt.length; i++) {
            if (boardInt[i] == 0) {
                boardInt[i] = maxPlayer;
                double score = miniMax(boardInt, 0, false, maxPlayer, minPlayer);
                boardInt[i] = 0;
                if (score > bestScore) {
                    bestScore = score;
                    moveToMake = i;
                }
            }
        }
        if (moveToMake != -1) {
            MyFrame mf = board.get(moveToMake);
            mf.setWhichPlayer(maxPlayer);
            mf.paint(mf.getGraphics(), false);
            mf.removeMouseListener(mf.getMouseListeners()[0]);
        }
    }

}
