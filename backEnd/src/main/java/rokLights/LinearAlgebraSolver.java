package rokLights;

import java.util.Arrays;

import static java.lang.Math.sqrt;

public class LinearAlgebraSolver {
    Mod2Algebra mod2Algebra = new Mod2Algebra();
    public int[] unrollIntMatrix(int[][] matrix) {
        int[] vector = new int[matrix.length*matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, vector, i*matrix[i].length, matrix[i].length);
        }
        return vector;
    }
    public int[][] rollIntToSquareMatrix(int[] vector) {
        int[][] matrix = new int[(int) sqrt(vector.length)][(int) sqrt(vector.length)];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(vector, i*matrix[i].length, matrix[i], 0, matrix[i].length);
        }
        return matrix;
    }
    public int[][] generateTransformationMatrix(int size) {
        int[][] changeMatrix  = new int[size*size][size*size];
        for(int i = 0; i < (size*size); i++) {
            // Diagonal
            changeMatrix[i][i] = 1;
            // Diagonal adjacent
            if ((i+1 < size*size) && ((i+1) % size != 0)) {
                changeMatrix[i+1][i] = 1;
                changeMatrix[i][i+1] = 1;
            }
            // Further away
            if (i+size < (size*size)) {
                changeMatrix[i+size][i] = 1;
                changeMatrix[i][i+size] = 1;
            }
        }
        return changeMatrix;
    }
    public boolean testStrategy(int[][] transformMatrix, int[] difference,  int[] strategy) {
        int[] tempVector = mod2Algebra.multiplyMatrixAndVectorMod2(transformMatrix, strategy);
        return Arrays.equals(tempVector, difference);
    }
    public void solve(Game game) {
        // Setup
        int[][] matrix = game.getMatrix();
        if (matrix.length < 2) {
            return;
        }
        int[] initialSetup = unrollIntMatrix(matrix);
        int[] desiredEnding = new int[matrix.length * matrix[0].length];
        Arrays.fill(desiredEnding, 1); // 1 for all lights on, 0 for all off
        // Algorithm
        int[] difference = mod2Algebra.addVectorsMod2(desiredEnding, initialSetup);
        int[][] transformMatrix = generateTransformationMatrix(matrix.length);
        int[][] invertedMatrix = mod2Algebra.inverse(transformMatrix);
        int[] strategy = mod2Algebra.multiplyMatrixAndVectorMod2(invertedMatrix, difference);
        // Test strategy
        boolean solvable = testStrategy(transformMatrix, difference, strategy);
        game.setSolvable(solvable);
        if (solvable) {
            int[][] strategySquare = rollIntToSquareMatrix(strategy);
            game.setSolution(strategySquare);
        }
    }
}
