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
        for (int[] row : game.getSolution()) System.out.println(Arrays.toString(row));

        int[][] test1 = {{1, 1, 1, 0}, {1, 1, 0, 1}, {1, 0, 1, 1}, {0, 1, 1, 1}};
        int[] test2 = {1, 0, 0, 1};
        int[] test4 = {0, 1, 1, 0};
        int[] test = mod2Algebra.multiplyMatrixAndVectorMod2(test1, test2);
        int[] test3 = mod2Algebra.addVectorsMod2(test, test4);

        mod2Algebra.swapRows(test1, 1, 3);

        System.out.println("........Gauss..........");
        int[][] transformMatrix = linSolver.generateTransformationMatrix(3);
        mod2Algebra.gaussJordanEliminationMod2(transformMatrix);
        for (int[] row : transformMatrix) System.out.println(Arrays.toString(row));

        System.out.println("........Extend..........");
        int[][] testExtended = mod2Algebra.extendMatrix(S4, notS4);
        for (int[] row : testExtended) System.out.println(Arrays.toString(row));

    }
}