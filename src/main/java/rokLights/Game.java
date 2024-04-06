package rokLights;

public class Game {
    private int[][] matrix;
    private boolean solvable;
    private int[][] solution;
    public Game() {
        this.matrix = new int[][]{};
        this.solvable = false;
        this.solution = new int[][]{};
    }
    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
    public int[][] getMatrix() {
        return matrix;
    }
    public void setSolvable(boolean solvable) {
        this.solvable = solvable;
    }
    public boolean getSolvable() {
        return solvable;
    }
    public void setSolution(int[][] solution) {
        this.solution = solution;
    }
    public int[][] getSolution() {
        return solution;
    }
}
