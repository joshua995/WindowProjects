package initializers;

public class Shared {
    private boolean isSimulationOn = true; // For game of life simulation
    private boolean isPlayer1 = true; // For tictactoe
    private boolean isPlayerVPlayer = true;// For tictactoe

    public boolean isSimulationOn() {
        return this.isSimulationOn;
    }

    public synchronized void setIsSimulationOn(boolean isSimulationOn) {
        this.isSimulationOn = isSimulationOn;
    }

    public boolean isPlayer1() {
        return this.isPlayer1;
    }

    public synchronized void setIsPlayer1(boolean isPlayer1) {
        this.isPlayer1 = isPlayer1;
    }

    public boolean isPlayerVPlayer() {
        return this.isPlayerVPlayer;
    }

    public synchronized void setIsPlayerVPlayer(boolean isPlayerVPlayer) {
        this.isPlayerVPlayer = isPlayerVPlayer;
    }
}
