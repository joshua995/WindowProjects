package initializers;

import tictactoe.MiniMax;

public class Shared {
    private boolean isSimulationOn = true; // For game of life simulation
    private boolean isPlayer1 = true; // For tictactoe
    private boolean isPlayerVPlayer = true;// For tictactoe
    private MiniMax mm; // For tictactoe

    public Shared() {// For game of life

    }

    public Shared(MiniMax mm) {//for tictactoe
        this.mm = mm;
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
}
