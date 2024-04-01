package rokLights;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LinearAlgebraSolver linSolver = new LinearAlgebraSolver();
        Mod2Algebra mod2Algebra = new Mod2Algebra();

        int[][] non = {{}};
        int[][] S4 = {{1, 1, 0, 0}, {0, 0, 0, 1}, {1, 1, 1, 1}, {1, 0, 0, 1}};
        int[][] notS4 = {{0, 1, 1, 0}, {1, 1, 1, 1}, {1, 0, 1, 1}, {0, 0, 0, 0}};
        int[][] S5 = {{0, 1, 1, 0, 1}, {0, 0, 1, 0, 0}, {1, 0, 0, 0, 1}, {1, 1, 0, 0, 0}, {0, 0, 1, 1, 1}};
        int[][] notS5 = {{1, 0, 1, 1, 1}, {1, 1, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 0}, {1, 1, 1, 1, 1}};

        Game game = new Game();
        game.setMatrix(S4);
        linSolver.solve(game);

        int[][] test1 = {{1, 1, 1, 0}, {1, 1, 0, 1}, {1, 0, 1, 1}, {0, 1, 1, 1}};
        int[] test2 = {1, 0, 0, 1};
        int[] test4 = {0, 1, 1, 0};
        int[] test = mod2Algebra.multiplyMatrixAndVectorModN(test1, test2, 2);
        int[] test3 = mod2Algebra.addVectorsMod2(test, test4);
        System.out.println(Arrays.toString(test));

        //for (int[] row : change) System.out.println(Arrays.toString(row));

    }
}