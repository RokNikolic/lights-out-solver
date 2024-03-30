package rokLights;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LinearAlgebraSolver linSolver = new LinearAlgebraSolver();

        int[][] non = {{}};
        int[][] S4 = {{1, 1, 0, 0}, {0, 0, 0, 1}, {1, 1, 1, 1}, {1, 0, 0, 1}};
        int[][] notS4 = {{0, 1, 1, 0}, {1, 1, 1, 1}, {1, 0, 1, 1}, {0, 0, 0, 0}};
        int[][] S5 = {{0, 1, 1, 0, 1}, {0, 0, 1, 0, 0}, {1, 0, 0, 0, 1}, {1, 1, 0, 0, 0}, {0, 0, 1, 1, 1}};
        int[][] notS5 = {{1, 0, 1, 1, 1}, {1, 1, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 0}, {1, 1, 1, 1, 1}};

        int[][] change = linSolver.generateChangeMatrix(5);

        for (int[] row : change) System.out.println(Arrays.toString(row));

    }
}