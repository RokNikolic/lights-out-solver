package rokLights;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LinearAlgebraSolver linSolver = new LinearAlgebraSolver();

        int[][] solvable3 = {{1, 1, 0}, {1, 0, 0}, {0, 1, 0}};
        int[][] solvable4 = {{1, 1, 0, 0}, {0, 0, 0, 1}, {1, 1, 1, 1}, {1, 0, 0, 1}};
        int[][] notSolvable4 = {{0, 1, 1, 0}, {1, 1, 1, 1}, {1, 0, 1, 1}, {0, 0, 0, 0}};
        int[][] solvable5 = {{0, 1, 1, 0, 1}, {0, 0, 1, 0, 0}, {1, 0, 0, 0, 1}, {1, 1, 0, 0, 0}, {0, 0, 1, 1, 1}};
        int[][] notSolvable5 = {{1, 0, 1, 1, 1}, {1, 1, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 0}, {1, 1, 1, 1, 1}};

        Game game = new Game();
        game.setMatrix(solvable4);
        linSolver.solve(game);
        System.out.println(game.getSolvable());
        for (int[] row : game.getSolution()) System.out.println(Arrays.toString(row));
    }
}