package rokLights;

import java.util.Dictionary;
import java.util.Hashtable;

public class LightChaseSolver {
    Dictionary<String, int[]> lookupDict= new Hashtable<>();
    public LightChaseSolver() {
        // 5x5 lookup, lights -> off
        lookupDict.put("00111", new int[]{3});
        lookupDict.put("01010", new int[]{1, 4});
        lookupDict.put("01101", new int[]{0});
        lookupDict.put("10001", new int[]{3, 4});
        lookupDict.put("10110", new int[]{4});
        lookupDict.put("11011", new int[]{2});
        lookupDict.put("11100", new int[]{1});
        // 5x5 lookup, lights -> on
        lookupDict.put("11000", new int[]{3});
        lookupDict.put("10101", new int[]{1, 4});
        lookupDict.put("10010", new int[]{0});
        lookupDict.put("01110", new int[]{3, 4});
        lookupDict.put("01001", new int[]{4});
        lookupDict.put("00100", new int[]{2});
        lookupDict.put("00011", new int[]{1});
    }
    public void flipAtLocation(int[][] matrix, int y, int x) {
        int size = matrix.length;
        if (y >= size || x >= size || x < 0 || y < 0) {
            throw new IllegalArgumentException("Index out of bounds");
        }
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
    public boolean contains(int[] array, int symbol) {
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
    private void clickMultipleAtTop(int[][] matrix, int[] locations) {
        for (int location : locations) {
            flipAtLocation(matrix, 0, location);
        }
    }
    private int[][] copyMatrix(int[][] matrix) {
        int[][] newMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix[0].length);
        }
        return newMatrix;
    }
    // Only works for 4x4 and 5x5 as other lookup dictionaries are not readily available
    public void solve(Game game) {
        int[][] originalMatrix = game.getProblem();
        int[][] matrix = copyMatrix(originalMatrix);
        if (matrix.length < 4 || matrix.length > 5) {
            throw new IllegalArgumentException("Only 4x4 and 5x5 matrices supported at this time");
        }
        int[][] solveMatrix = new int[matrix.length][matrix[0].length];
        for (int y = 0; y < matrix.length; y++) {
            if (y != matrix.length - 1) { // Normal rows
                for (int x = 0; x < matrix.length; x++) {
                    if (matrix[y][x] == 0) {
                        flipAtLocation(matrix, y + 1, x);
                        solveMatrix[y + 1][x] = 1;
                    }
                }
            } else { // Last row
                if (!contains(matrix[y], 0)) {
                    game.setSolvable(true);
                    game.setSolution(solveMatrix);
                } else {
                    String arrayString = convertToString(matrix[y]);
                    int[] toBeClicked = lookupDict.get(arrayString);
                    if (toBeClicked == null) {
                        game.setSolvable(false);
                    } else {
                        clickMultipleAtTop(originalMatrix, toBeClicked);
                        game.setProblem(originalMatrix);
                        solve(game);
                    }
                }
            }
        }
    }
}
