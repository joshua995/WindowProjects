package snake;

public class Shared {
    private boolean isGameOver = false;
    private int direction = 0; // 1 up, 2 down, 3 left, 4 right

    public int direction() {
        return this.direction;
    }

    public void setDirection(int dir) {
        this.direction = dir;
    }

    public boolean isGameOver() {
        return this.isGameOver;
    }

    public void setIsGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }
}
