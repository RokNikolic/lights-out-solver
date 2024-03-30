package rokLights;

public class LinearAlgebraSolver {
    private int[][] addMatrixMod2 (int[][] matrix1, int[][] matrix2) {
        int[][] sum  = new int[matrix1.length][matrix1[0].length];
        if ((matrix1.length != matrix2.length) || (matrix1[0].length != matrix2[0].length)) {
            throw new RuntimeException("Non  equal matrix sizes.");
        }
        for(int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                sum[i][j] = (matrix1[i][j] + matrix2[i][j]) % 2;
            }
        }
        return sum;
    }
    public int[] unrollIntMatrix (int[][] matrix) {
        int[] vector = new int[matrix.length*matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, vector, i*matrix[i].length, matrix[i].length);
        }
        return vector;
    }
    private int[] addVectorMod2 (int[] vector1, int[] vector2) {
        int[] sum  = new int[vector1.length];
        if (vector1.length != vector2.length) {
            throw new RuntimeException("Non  equal vector sizes.");
        }
        for(int i = 0; i < vector1.length; i++) {
            sum[i] = (vector1[i] + vector2[i]) % 2;

        }
        return sum;
    }
    public int[][] generateChangeMatrix(int size) {
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
    public void solve(Game game) {

    }
}
