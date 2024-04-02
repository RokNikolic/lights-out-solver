package rokLights;

import java.util.Arrays;

public class Mod2Algebra {
    public int[][] makeIdentity(int size) {
        int[][] matrix = new int[size][size];
        for(int i = 0; i < size; i++) matrix[i][i] = 1;
        return matrix;
    }
    public int[] multiplyMatrixAndVectorMod2(int[][] matrix, int[] vector) {
        if (matrix[0].length != vector.length) {
            throw new RuntimeException("Wrong sizes.");
        }
        int[] sum  = new int[vector.length];
        for (int i = 0; i < vector.length; i++) {
            int partialSum = 0;
            for (int j = 0; j < vector.length; j++) {
                partialSum = (partialSum + (matrix[i][j] * vector[j])) % 2;
            }
            sum[i] = partialSum;
        }
        return sum;
    }
    public int[] addVectorsMod2(int[] vector1, int[] vector2) {
        if (vector1.length != vector2.length) {
            throw new RuntimeException("Non equal vector sizes.");
        }
        int[] sum  = new int[vector1.length];
        for(int i = 0; i < vector1.length; i++) {
            sum[i] = (vector1[i] + vector2[i]) % 2;
        }
        return sum;
    }
    public void swapRows(int[][] matrix, int row1, int row2) {
        int[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }
    public int[][] extendMatrix(int[][] originalMatrix, int[][] extendingMatrix) {
        if (originalMatrix.length != extendingMatrix.length) {
            throw new RuntimeException("Wrong  matrix heights.");
        }
        int[][] extended = new int[originalMatrix.length][originalMatrix.length+extendingMatrix.length];
        for (int i = 0; i < originalMatrix.length; i++) {
            System.arraycopy(originalMatrix[i], 0, extended[i], 0, originalMatrix.length);
            System.arraycopy(extendingMatrix[i], 0, extended[i], originalMatrix.length, extendingMatrix.length);
        }
        return extended;
    }
    public int[][][] splitMatrixInHalf(int[][] matrix) {
        int[][] left = new int[matrix.length][matrix.length];
        int[][] right = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, left[i], 0, matrix.length);
            System.arraycopy(matrix[i], matrix.length, right[i], 0, matrix.length);
        }
        return new int[][][]{left, right};
    }
    public void gaussJordanEliminationMod2(int[][] matrix) {
        int numOfRows = matrix.length;
        for (int i = 0; i < numOfRows; i++) {
            // Swap 0 on the diagonal with lowest 1
            if (matrix[i][i] == 0) {
                for (int j = numOfRows-1; j > i; j--) {
                    if (matrix[j][i] > matrix[i][i]) {
                        swapRows(matrix, j, i);
                        break;
                    }
                }
            }
            // More than 1 solution, we pick 1
            if (matrix[i][i] == 0) {
                matrix[i][i] = 1;
            }
            // Elimination of rows to 0
            for (int j = 0; j < numOfRows; j++) {
                if ((j == i) || matrix[j][i] == 0)
                    continue;
                matrix[j] = addVectorsMod2(matrix[j], matrix[i]);
            }
        }
    }
    public int[][] inverse(int[][] matrix) {
        if (matrix.length != matrix[0].length) {
            throw new RuntimeException("Matrix not square.");
        }
        int[][] identity = makeIdentity(matrix.length);
        int[][] combined = extendMatrix(matrix, identity);
        gaussJordanEliminationMod2(combined);
        int[][][] leftAndRight = splitMatrixInHalf(combined);
        if (Arrays.deepEquals(leftAndRight[0], identity)) {
            return leftAndRight[1];
        } else {
            return new int[][] {{}};
        }
    }
}
