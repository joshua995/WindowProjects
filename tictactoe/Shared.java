package tictactoe;

import java.awt.Color;

public class Shared {
    private boolean isSimulationOn = true; // for gameOfLife
    private boolean isPlayer1 = true; // for tictactoe
    private boolean isPlayerVPlayer = true;// for tictactoe
    private MiniMax mm; // for tictactoe
    private boolean resetBoard = false; // for tictactoe
    private MyFrame currentPlayerDisplay;

    public Shared(MiniMax mm) {// for tictactoe
        this.mm = mm;
    }

    public void setCurrentPlayerDisplay(int x, int y, int width, int height, Color color, String text) {
        disposeCurrentPlayerDisplay();
        currentPlayerDisplay = new MyFrame(x, y, width, height, color, text);
    }

    public void disposeCurrentPlayerDisplay() {
        if (currentPlayerDisplay != null) {
            currentPlayerDisplay.dispose();
        }
    }

    public boolean isSimulationOn() {
        return this.isSimulationOn;
    }

    public void setIsSimulationOn(boolean isSimulationOn) {
        this.isSimulationOn = isSimulationOn;
    }

    public boolean isPlayer1() {
        return this.isPlayer1;
    }

    public void setIsPlayer1(boolean isPlayer1) {
        this.isPlayer1 = isPlayer1;
    }

    public boolean isPlayerVPlayer() {
        return this.isPlayerVPlayer;
    }

    public void setIsPlayerVPlayer(boolean isPlayerVPlayer) {
        this.isPlayerVPlayer = isPlayerVPlayer;
    }

    public void makeBestMove(int maxPlayer, int minPlayer) {
        this.mm.makeBestMove(maxPlayer, minPlayer);
    }

    public void setMiniMax(MiniMax mm) {
        this.mm = mm;
    }

    public boolean resetBoard() {
        return this.resetBoard;
    }

    public void setResetBoard(boolean resetBoard) {
        this.resetBoard = resetBoard;
    }
}
