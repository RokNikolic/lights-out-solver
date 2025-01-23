package com.github.roknikolic;

public class Game {
    private int[][] problem;
    private boolean solvable;
    private int[][] solution;
    public Game() {
        this.problem = new int[][]{};
        this.solvable = false;
        this.solution = new int[][]{};
    }
    public void setProblem(int[][] problem) {
        this.problem = problem;
    }
    public int[][] getProblem() {
        return problem;
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
