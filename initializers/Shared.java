package initializers;

public class Shared {
    private boolean isSimulationOn = true;
    private boolean gameStarted = false;
    private boolean isPlayer1 = true;

    public boolean isSimulationOn() {
        return this.isSimulationOn;
    }

    public synchronized void setIsSimulationOn(boolean isSimulationOn) {
        this.isSimulationOn = isSimulationOn;
    }

    public boolean gameStarted() {
        return this.gameStarted;
    }

    public synchronized void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public boolean isPlayer1() {
        return this.isPlayer1;
    }

    public synchronized void setIsPlayer1(boolean isPlayer1) {
        this.isPlayer1 = isPlayer1;
    }
}
