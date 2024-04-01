package rokLights;

public class Mod2Algebra {
    public int[] multiplyMatrixAndVectorModN (int[][] matrix, int[] vector,  int mod) {
        int[] sum  = new int[vector.length];
        if (matrix[0].length != vector.length) {
            throw new RuntimeException("Wrong sizes.");
        }
        for (int i = 0; i < vector.length; i++) {
            int partialSum = 0;
            for (int j = 0; j < vector.length; j++) {
                partialSum = (partialSum + (matrix[i][j] * vector[j])) % mod;
            }
            sum[i] = partialSum;
        }
        return sum;
    }
    public int[] addVectorsMod2 (int[] vector1, int[] vector2) {
        int[] sum  = new int[vector1.length];
        if (vector1.length != vector2.length) {
            throw new RuntimeException("Non equal vector sizes.");
        }
        for(int i = 0; i < vector1.length; i++) {
            sum[i] = (vector1[i] + vector2[i]) % 2;
        }
        return sum;
    }
    public int[][] GaussJordanElimination(int[][] matrix, int mod) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int pivotLoc = 0;
        for (int i = 0; i < columns && pivotLoc < rows; i++) {
            int pivotTemp = pivotLoc;
            for (int k = i; k < rows; k++) {

            }
        }


        return matrix;
    }
    public int[][] inverse(int[][] matrix, int mod) {
        return matrix;
    }
}
