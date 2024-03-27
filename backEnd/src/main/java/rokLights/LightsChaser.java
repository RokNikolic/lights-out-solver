package rokLights;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;

public class LightsChaser {
    Dictionary<String, int[]> lookupDict= new Hashtable<>();
    public LightsChaser() {
        // No solution states
        lookupDict.put("1000", new int[]{});
        lookupDict.put("11000", new int[]{});
        // 5x5 lookup
        lookupDict.put("00111", new int[]{3});
        lookupDict.put("01010", new int[]{1, 4});
        lookupDict.put("01101", new int[]{0});
        lookupDict.put("10001", new int[]{3, 4});
        lookupDict.put("10110", new int[]{4});
        lookupDict.put("11011", new int[]{2});
        lookupDict.put("11100", new int[]{1});
    }
    private void flipAtLocation(int[][] matrix, int y, int x) {
        int size = matrix.length;
        matrix[y][x] = matrix[y][x] ^ 1;
        if (y-1 >= 0) {
            matrix[y-1][x] = matrix[y-1][x] ^ 1;
        }
        if (y+1 < size) {
            matrix[y+1][x] = matrix[y+1][x] ^ 1;
        }
        if (x-1 >= 0) {
            matrix[y][x-1] = matrix[y][x-1] ^ 1;
        }
        if (x+1 < size) {
            matrix[y][x+1] = matrix[y][x+1] ^ 1;
        }
    }
    private boolean contains(int[] array, int symbol) {
        for (int element : array) {
            if (element == symbol) {
                return true;
            }
        }
        return false;
    }
    private String convertToString(int[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int element : array) {
            stringBuilder.append(element);
        }
        return stringBuilder.toString();
    }
    private void clickMultiple(int[][] matrix, int[] locations, int row) {
        for (int location : locations) {
            flipAtLocation(matrix, row, location);
        }
    }
    public int[][] chaseLights(int[][] matrix) {
        int[][] solveMatrix = new int[matrix.length][matrix[0].length];
        for (int y = 0; y < matrix.length; y++) {
            if (y != matrix.length - 1) { // Normal row
                for (int x = 0; x < matrix.length; x++) {
                    if (matrix[y][x] == 1) {
                        flipAtLocation(matrix, y + 1, x);
                        solveMatrix[y + 1][x] = 1;
                    }
                }
            } else { // Last row
                if (!(contains(matrix[y], 1))) {
                    System.out.println("Solved!");
                } else {
                    String arrayString = convertToString(matrix[y]);
                    int[] toBeClicked = lookupDict.get(arrayString);
                    if (toBeClicked == null) {
                        System.out.println("Unsolvable");
                    } else {
                        clickMultiple(matrix, toBeClicked, 0);
                    }
                }
            }
        }
        return solveMatrix;
    }
}
