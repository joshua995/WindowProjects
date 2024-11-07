package gameOfLife;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyFrame extends JFrame implements Runnable {
    protected MyPanel myPanel;
    private Shared shared;
    private int[] xywh = new int[4];

    private int[][] grid;
    private int gridWidth, gridHeight;
    private int cellSize;
    private List<int[]> rectangles = new ArrayList<>();

    public MyFrame(int x, int y, int width, int height, Shared shared) {
        this.setTitle("Cell");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.myPanel = new MyPanel(width, height);
        this.add(myPanel);
        this.setUndecorated(true);
        this.pack();
        this.setLayout(null);
        this.setVisible(false);// Makes init simpler
        this.setLocation(x, y);
        this.shared = shared;
        this.cellSize = width / 10;
        this.xywh[0] = 0;
        this.xywh[1] = 0;
        this.xywh[2] = this.cellSize;
        this.xywh[3] = this.cellSize;
        this.initGrid();
        this.generateRandomInitialState(this.grid);
    }

    private void initGrid() {
        // Get grid dimensions based on the predefined cell size
        this.gridWidth = this.getWidth() / cellSize;
        this.gridHeight = this.getHeight() / cellSize;

        grid = new int[gridHeight][gridWidth];
    }

    private void generateRandomInitialState(int[][] grid) {
        Random random = new Random();
        int iterations = random.nextInt((gridWidth / 2) * (gridHeight / 2));
        for (int i = 0; i < iterations; i++) {
            int randRow = random.nextInt(gridHeight);
            int randCol = random.nextInt(gridWidth);
            grid[randRow][randCol] = 1;
        }
    }

    private boolean runGameOfLifeSimulation() {
        boolean changed = false;
        int[][] gridCopy = deepCopyGrid(grid);// Use this to prep next generation
        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                if (grid[row][col] == 1) {
                    if (aliveNeighbourCount(row, col) < 2 || aliveNeighbourCount(row, col) > 3) {// Cell dies
                        gridCopy[row][col] = 0;
                        changed = true;
                    } // (aliveNeighbourCount(row, col) == 2 || aliveNeighbourCount(row, col) == 3) {
                      // Cell stays alive}
                } else if (grid[row][col] == 0 && aliveNeighbourCount(row, col) == 3) {
                    gridCopy[row][col] = 1;
                    changed = true;
                }
            }
        }
        grid = deepCopyGrid(gridCopy);
        return changed;
    }

    private int aliveNeighbourCount(int row, int col) {
        int aliveCounter = 0;
        boolean rz = false, rh = false, cz = false, cw = false; // row zero, row height, col zero, col width
        if (rz = row > 0) {
            if (grid[row - 1][col] == 1) {
                aliveCounter++;
            }
        }
        if (rh = row < gridHeight - 1) {
            if (grid[row + 1][col] == 1) {
                aliveCounter++;
            }
        }
        if (cz = col > 0) {
            if (grid[row][col - 1] == 1) {
                aliveCounter++;
            }
        }
        if (cw = col < gridWidth - 1) {
            if (grid[row][col + 1] == 1) {
                aliveCounter++;
            }
        }
        if (rz && cz) {
            if (grid[row - 1][col - 1] == 1) {
                aliveCounter++;
            }
        }
        if (rz && cw) {
            if (grid[row - 1][col + 1] == 1) {
                aliveCounter++;
            }
        }
        if (rh && cw) {
            if (grid[row + 1][col + 1] == 1) {
                aliveCounter++;
            }
        }
        if (rh && cz) {
            if (grid[row + 1][col - 1] == 1) {
                aliveCounter++;
            }
        }
        return aliveCounter;
    }

    private int[][] deepCopyGrid(int[][] grid) {
        int[][] gridCopy = new int[gridHeight][gridWidth];
        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                gridCopy[row][col] = grid[row][col];
            }
        }
        return gridCopy;
    }

    void displayGameOfLife() {
        rectangles.clear();
        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                if (grid[row][col] == 1) {
                    int[] array = { col * this.cellSize, row * this.cellSize };
                    rectangles.add(array);
                }
            }
        }
        this.paint(getGraphics());
    }

    void drawRectangles(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        for (int[] r : rectangles) {
            g2d.fillRect(r[0], r[1], this.cellSize, this.cellSize);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        drawRectangles(g);
    }

    @Override
    public void run() {// Run another game of life simulation inside here
        while (this.shared.isSimulationOn()) {
            if (this.isVisible()) {
                System.out.println(" Running" + Thread.currentThread().getName());
                this.displayGameOfLife();
                if (this.runGameOfLifeSimulation()) {// Automatic reset
                    // this.initGrid();
                    // this.generateRandomInitialState(grid);
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // e.printStackTrace();
            }
        }
    }
}
