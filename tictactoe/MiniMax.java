package tictactoe;

public class MiniMax {
    int[] board;

    public MiniMax(int[] board) {
        this.board = board;
    }

    private boolean checkWinner(int[] board, int playerToCheck) {
        if ((board[0] == board[1] && board[1] == board[2] && board[2] == playerToCheck)
                || (board[3] == board[4] && board[4] == board[5] && board[5] == playerToCheck)
                || (board[6] == board[7] && board[7] == board[8] && board[8] == playerToCheck)
                || (board[0] == board[3] && board[3] == board[6] && board[6] == playerToCheck)
                || (board[1] == board[4] && board[4] == board[7] && board[7] == playerToCheck)
                || (board[2] == board[5] && board[5] == board[8] && board[8] == playerToCheck)
                || (board[0] == board[4] && board[4] == board[8] && board[8] == playerToCheck)
                || (board[2] == board[4] && board[4] == board[6] && board[6] == playerToCheck)) {
            return true;
        }
        return false;
    }

    private boolean isDraw(int[] board) {
        for (int i : board) {
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
            for (int i = 0; i < board.length; i++) {
                if (board[i] == 0) {
                    board[i] = maxPlayer;
                    double score = miniMax(board, depth + 1, false, maxPlayer, minPlayer);
                    board[i] = 0;
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        } else {
            double bestScore = 1;
            for (int i = 0; i < board.length; i++) {
                if (board[i] == 0) {
                    board[i] = minPlayer;
                    double score = miniMax(board, depth + 1, true, maxPlayer, minPlayer);
                    board[i] = 0;
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        }
    }
}
