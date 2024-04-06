package rokLights;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinearAlgebraSolverTest {
    LinearAlgebraSolver linSolver = new LinearAlgebraSolver();
    @Test
    void unrollIntMatrixNormal() {
        int[][] matrix = {{1, 1, 0}, {1, 0, 0}, {0, 1, 0}};
        int[] generatedVector =  linSolver.unrollIntMatrix(matrix);
        int[] expectedVector = {1, 1, 0, 1, 0, 0, 0, 1, 0};
        assertArrayEquals(generatedVector, expectedVector);
    }
    @Test
    void unrollIntMatrixNotSquare() {
        int[][] matrix = {{1, 1}, {1, 0}, {0, 1}};
        int[] generatedVector =  linSolver.unrollIntMatrix(matrix);
        int[] expectedVector = {1, 1, 1, 0, 0, 1};
        assertArrayEquals(generatedVector, expectedVector);
    }
    @Test
    void unrollIntMatrixEmpty() {
        int[][] matrix = {{}};
        int[] generatedVector =  linSolver.unrollIntMatrix(matrix);
        int[] expectedVector = {};
        assertArrayEquals(generatedVector, expectedVector);
    }
    @Test
    void unrollIntMatrixNot2DError() {
        int[][] matrix = {};
        assertThrows(IllegalArgumentException.class,
                () -> linSolver.unrollIntMatrix(matrix));
    }
    @Test
    void rollToSquareMatrixNormal() {
        int[] matrix = {1, 1, 0, 1, 0, 0, 0, 1, 0};
        int[][] generatedVector =  linSolver.rollToSquareMatrix(matrix);
        int[][] expectedVector = {{1, 1, 0}, {1, 0, 0}, {0, 1, 0}};
        assertArrayEquals(generatedVector, expectedVector);
    }
    @Test
    void rollToSquareMatrixNotSquare() {
        int[] matrix = {1, 1, 0, 1, 0, 0, 0};
        assertThrows(IllegalArgumentException.class,
                () -> linSolver.rollToSquareMatrix(matrix));
    }
    @Test
    void rollToSquareMatrixEmpty() {
        int[] matrix = {};
        int[][] generatedVector =  linSolver.rollToSquareMatrix(matrix);
        int[][] expectedVector = {};
        assertArrayEquals(generatedVector, expectedVector);
    }
    @Test
    void generateTransformationMatrix3x3() {
        int[][] generatedVector =  linSolver.generateTransformationMatrix(3);
        int[][] expectedVector = {{1, 1, 0, 1, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 1, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 1, 0, 0, 0},
                {1, 0, 0, 1, 1, 0, 1, 0, 0},
                {0, 1, 0, 1, 1, 1, 0, 1, 0},
                {0, 0, 1, 0, 1, 1, 0, 0, 1},
                {0, 0, 0, 1, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 1, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 1, 0, 1, 1}};
        assertArrayEquals(generatedVector, expectedVector);
    }
    Game game = new Game();
    @Test
    void solve3x3Num1() {
        int[][] solvable3 = {{1, 1, 0}, {1, 0, 0}, {0, 1, 0}};
        game.setMatrix(solvable3);
        linSolver.solve(game);
        int[][] generatedSolution = game.getSolution();
        int[][] expectedSolution = {{1, 1, 0}, {0, 0, 0}, {1, 0, 1}};
        assertArrayEquals(generatedSolution, expectedSolution);
    }
    @Test
    void solve3x3Num2() {
        int[][] solvable = {{1, 1, 0}, {0, 1, 0}, {1, 1, 1}};
        game.setMatrix(solvable);
        linSolver.solve(game);
        int[][] generatedSolution = game.getSolution();
        int[][] expectedSolution = {{0, 0, 0}, {0, 0, 1}, {1, 1, 0}};
        assertArrayEquals(generatedSolution, expectedSolution);
    }
    @Test
    void solve3x3Num3() {
        int[][] solvable = {{1, 1, 0}, {0, 0, 0}, {0, 0, 0}};
        game.setMatrix(solvable);
        linSolver.solve(game);
        int[][] generatedSolution = game.getSolution();
        int[][] expectedSolution = {{0, 0, 0}, {0, 0, 1}, {1, 0, 0}};
        assertArrayEquals(generatedSolution, expectedSolution);
    }
    @Test
    void solve4x4Num1() {
        int[][] solvable = {{1, 1, 0, 0}, {0, 0, 0, 1}, {1, 1, 1, 1}, {1, 0, 0, 1}};
        game.setMatrix(solvable);
        linSolver.solve(game);
        int[][] generatedSolution = game.getSolution();
        int[][] expectedSolution = {{0, 1, 1, 1}, {1, 0, 0, 1}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        assertArrayEquals(generatedSolution, expectedSolution);
    }
    @Test
    void solve4x4Num2() {
        int[][] solvable = {{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 0}, {1, 0, 0, 1}};
        game.setMatrix(solvable);
        linSolver.solve(game);
        int[][] generatedSolution = game.getSolution();
        int[][] expectedSolution = {{1, 1, 1, 1}, {0, 0, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        assertArrayEquals(generatedSolution, expectedSolution);
    }
    @Test
    void solve4x4Num3() {
        int[][] solvable = {{1, 0, 1, 1}, {0, 0, 1, 1}, {0, 0, 1, 1}, {1, 0, 1, 1}};
        game.setMatrix(solvable);
        linSolver.solve(game);
        int[][] generatedSolution = game.getSolution();
        int[][] expectedSolution = {{1, 1, 1, 1}, {0, 0, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}};
        assertArrayEquals(generatedSolution, expectedSolution);
    }
    @Test
    void solve4x4NotSolvable1() {
        int[][] notSolvable = {{0, 1, 1, 0}, {1, 1, 1, 1}, {1, 0, 1, 1}, {0, 0, 0, 0}};
        game.setMatrix(notSolvable);
        linSolver.solve(game);
        assertFalse(game.getSolvable());
    }
    @Test
    void solve4x4NotSolvable2() {
        int[][] notSolvable = {{0, 0, 1, 1}, {0, 1, 1, 0}, {0, 0, 0, 0}, {1, 0, 0, 0}};
        game.setMatrix(notSolvable);
        linSolver.solve(game);
        assertFalse(game.getSolvable());
    }
    @Test
    void solve5x5Num1() {
        int[][] solvable = {{0, 1, 1, 0, 1}, {0, 0, 1, 0, 0}, {1, 0, 0, 0, 1}, {1, 1, 0, 0, 0}, {0, 0, 1, 1, 1}};
        game.setMatrix(solvable);
        linSolver.solve(game);
        int[][] generatedSolution = game.getSolution();
        int[][] expectedSolution = {{0, 0, 0, 0, 0}, {1, 0, 0, 1, 0}, {0, 0, 1, 0, 0}, {1, 0, 0, 1, 0}, {1, 1, 1, 0, 0}};
        assertArrayEquals(generatedSolution, expectedSolution);
    }
    @Test
    void solve5x5Num2() {
        int[][] solvable = {{0, 1, 1, 0, 1}, {0, 0, 1, 0, 0}, {1, 1, 1, 1, 1}, {1, 1, 0, 0, 0}, {0, 0, 1, 1, 1}};
        game.setMatrix(solvable);
        linSolver.solve(game);
        int[][] generatedSolution = game.getSolution();
        int[][] expectedSolution = {{1, 0, 0, 0, 1}, {0, 1, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 1, 1, 0, 0}, {1, 1, 0, 0, 0}};
        assertArrayEquals(generatedSolution, expectedSolution);
    }
    @Test
    void solve5x5Num3() {
        int[][] solvable = {{0, 1, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 1, 1, 1, 1}, {1, 1, 0, 0, 0}, {0, 0, 1, 1, 1}};
        game.setMatrix(solvable);
        linSolver.solve(game);
        int[][] generatedSolution = game.getSolution();
        int[][] expectedSolution = {{1, 1, 1, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 1, 0, 0}, {1, 0, 0, 1, 0}, {1, 1, 1, 0, 0}};
        assertArrayEquals(generatedSolution, expectedSolution);
    }
    @Test
    void solve5x5NotSolvable1() {
        int[][] notSolvable = {{1, 0, 1, 1, 1}, {1, 1, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 0}, {1, 1, 1, 1, 1}};
        game.setMatrix(notSolvable);
        linSolver.solve(game);
        assertFalse(game.getSolvable());
    }
    @Test
    void solve5x5NotSolvable2() {
        int[][] notSolvable = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {1, 1, 1, 1, 1}};
        game.setMatrix(notSolvable);
        linSolver.solve(game);
        assertFalse(game.getSolvable());
    }
    @Test
    void solve5x5NotSolvable3() {
        int[][] notSolvable = {{1, 1, 1, 1, 1}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        game.setMatrix(notSolvable);
        linSolver.solve(game);
        assertFalse(game.getSolvable());
    }
    @Test
    void solve6x6Num1() {
        int[][] solvable = {{0, 0, 1, 1, 0, 0}, {0, 1, 1, 1, 0, 1}, {1, 1, 0, 1, 0, 1},
                {1, 1, 0, 0, 0, 1}, {1, 1, 0, 0, 0, 0}, {0, 0, 1, 1, 1, 0}};
        game.setMatrix(solvable);
        linSolver.solve(game);
        int[][] generatedSolution = game.getSolution();
        int[][] expectedSolution = {{1, 1, 0, 1, 0, 0}, {1, 1, 0, 1, 0, 1}, {0, 1, 0, 0, 1, 1},
                {0, 0, 0, 0, 1, 1}, {0, 1, 1, 0, 0, 1}, {1, 0, 1, 0, 1, 1}};
        assertArrayEquals(generatedSolution, expectedSolution);
    }
    @Test
    void solve7x7Num1() {
        int[][] solvable = {{1, 0, 0, 0, 1, 0, 1}, {1, 1, 1, 1, 1, 0, 0}, {0, 0, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 1, 1}, {1, 0, 0, 0, 0, 1, 1}, {0, 1, 0, 1, 0, 0, 1}, {1, 0, 1, 0, 1, 1, 1}};
        game.setMatrix(solvable);
        linSolver.solve(game);
        assertTrue(game.getSolvable());
    }
}