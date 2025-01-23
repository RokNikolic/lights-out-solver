package com.github.roknikolic;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LinearAlgebraSolver linSolver = new LinearAlgebraSolver();
        Game game = new Game();
        // Example usage
        int[][] positionToSolve = {{1, 1, 0}, {1, 0, 0}, {0, 1, 0}};
        game.setProblem(positionToSolve);
        linSolver.solve(game);
        System.out.println(Arrays.deepToString(game.getSolution()));
    }
}