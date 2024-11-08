package gameOfLife;

/*
 * Joshua Liu
 * November 5, 2024
 * Game of Life simulation shown using JFrame windows
 */
import java.awt.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import initializers.Shared;

public class GameOfLife {

    private static int gridWidth, gridHeight;
    private static int[][] grid; //
    private static int cellSize = 50;
    private static MyFrame[][] windows;
    private static ExecutorService executor = Executors.newCachedThreadPool();
    private static Shared shared = new Shared();

    public static void main(String[] args) {
        initGrid();
        initWindows();
        generateRandomInitialState();
        while (true) {
            // debugPrintGrid();
            displayGameOfLife();
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 1000) // Allows simulation to be seen on screen (update rate)
                ;
            if (!runGameOfLifeSimulation()) {
                break;
            }
        }
        shared.setIsSimulationOn(false);
        // Clean up
        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                windows[row][col].dispose(); // Close all windows
            }
        }
        executor.shutdownNow();
    }

    private static void initGrid() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();// Get screen resolution

        // width will store the width of the screen
        int width = (int) size.getWidth();
        // height will store the height of the screen
        int height = (int) size.getHeight();

        // Get grid dimensions based on the predefined cell size
        gridWidth = width / cellSize;
        gridHeight = height / cellSize;

        grid = new int[gridHeight][gridWidth];
    }

    private static void initWindows() {
        windows = new MyFrame[gridHeight][gridWidth];
        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                windows[row][col] = new MyFrame(col * cellSize, row * cellSize, cellSize, cellSize, shared,
                        Color.green);
                executor.submit(windows[row][col]);
                // Thread t = new Thread(windows[row][col], row + " ," + col);
                // t.start();
            }
        }
    }

    private static void generateRandomInitialState() {
        Random random = new Random();
        int iterations = random.nextInt((gridWidth / 2) * (gridHeight / 2));
        for (int i = 0; i < iterations; i++) {
            int randRow = random.nextInt(gridHeight);
            int randCol = random.nextInt(gridWidth);
            grid[randRow][randCol] = 1;
        }
    }

    private static void debugPrintGrid() {
        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                System.out.print(grid[row][col]);
            }
            System.out.println();
        }
    }

    private static boolean runGameOfLifeSimulation() {
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

    private static int aliveNeighbourCount(int row, int col) {
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

    private static int[][] deepCopyGrid(int[][] grid) {
        int[][] gridCopy = new int[gridHeight][gridWidth];
        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                gridCopy[row][col] = grid[row][col];
            }
        }
        return gridCopy;
    }

    private static void displayGameOfLife() {
        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                if (grid[row][col] == 1) {
                    windows[row][col].setVisible(true);
                } else {
                    windows[row][col].setVisible(false);
                }
            }
        }
    }
}
