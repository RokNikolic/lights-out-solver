package rokLights;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LightChaseSolverTest {
    LightChaseSolver chaseSolver = new LightChaseSolver();
    @Test
    void flipAtLocationNormal() {
        int[][] matrix = {{0, 1, 0}, {0, 0, 0}, {0, 1, 0}};
        chaseSolver.flipAtLocation(matrix, 1, 1);
        int[][] expectedMatrix = {{0, 0, 0}, {1, 1, 1}, {0, 0, 0}};
        assertArrayEquals(matrix, expectedMatrix);
    }
    @Test
    void flipAtLocationEdge() {
        int[][] matrix = {{0, 1, 0}, {0, 1, 0}, {0, 1, 0}};
        chaseSolver.flipAtLocation(matrix, 2, 2);
        int[][] expectedMatrix = {{0, 1, 0}, {0, 1, 1}, {0, 0, 1}};
        assertArrayEquals(matrix, expectedMatrix);
    }
    @Test
    void flipAtLocationError() {
        int[][] matrix = {{0, 1, 0}, {0, 0, 0}, {0, 1, 0}};
        assertThrows(IllegalArgumentException.class,
                () -> chaseSolver.flipAtLocation(matrix, 4, 1));
    }
    @Test
    void contains1() {
        int[] vector = {1, 1, 0, 1, 0, 0, 0, 1, 0};
        assertTrue(chaseSolver.contains(vector, 1));
    }
    @Test
    void contains0() {
        int[] vector = {1, 1, 0, 1, 0, 0, 0, 1, 0};
        assertTrue(chaseSolver.contains(vector, 0));
    }
    @Test
    void contains0false() {
        int[] vector = {1, 1, 1, 1, 1};
        assertFalse(chaseSolver.contains(vector, 0));
    }
    Game game = new Game();
    @Test
    void chase4x4Num1() {
        int[][] solvable = {{1, 1, 0, 0}, {0, 0, 0, 1}, {1, 1, 1, 1}, {1, 0, 0, 1}};
        game.setProblem(solvable);
        chaseSolver.solve(game);
        int[][] generatedSolution = game.getSolution();
        int[][] expectedSolution = {{0, 0, 0, 0}, {0, 0, 1, 1}, {1, 0, 1, 0}, {1, 0, 0, 0}};
        assertArrayEquals(generatedSolution, expectedSolution);
    }
    @Test
    void chase4x4NotSolvable1() {
        int[][] notSolvable = {{0, 1, 1, 0}, {1, 1, 1, 1}, {1, 0, 1, 1}, {0, 0, 0, 0}};
        game.setProblem(notSolvable);
        chaseSolver.solve(game);
        assertFalse(game.getSolvable());
    }
    @Test
    void chase5x5Num1() {
        int[][] solvable = {{0, 1, 1, 0, 1}, {0, 0, 1, 0, 0}, {1, 0, 0, 0, 1}, {1, 1, 0, 0, 0}, {0, 0, 1, 1, 1}};
        game.setProblem(solvable);
        chaseSolver.solve(game);
        int[][] generatedSolution = game.getSolution();
        int[][] expectedSolution = {{0, 0, 0, 0, 0}, {1, 0, 0, 1, 0}, {0, 0, 1, 0, 0}, {1, 0, 0, 1, 0}, {1, 1, 1, 0, 0}};
        assertArrayEquals(generatedSolution, expectedSolution);
    }
    @Test
    void chase5x5Num2() {
        int[][] solvable = {{0, 1, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 1, 1, 1, 1}, {1, 1, 0, 0, 0}, {0, 0, 1, 1, 1}};
        game.setProblem(solvable);
        chaseSolver.solve(game);
        int[][] generatedSolution = game.getSolution();
        int[][] expectedSolution = {{0, 0, 0, 0, 0}, {1, 1, 1, 0, 0}, {1, 1, 1, 1, 1}, {1, 0, 0, 1, 0}, {0, 0, 1, 1, 1}};
        assertArrayEquals(generatedSolution, expectedSolution);
    }
    @Test
    void chase5x5NotSolvable1() {
        int[][] notSolvable = {{1, 0, 1, 1, 1}, {1, 1, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 0}, {1, 1, 1, 1, 1}};
        game.setProblem(notSolvable);
        chaseSolver.solve(game);
        assertFalse(game.getSolvable());
    }
    @Test
    void chase5x5Error() {
        int[][] notSolvable = {{1, 0}, {1, 1}};
        game.setProblem(notSolvable);
        assertThrows(IllegalArgumentException.class,
                () -> chaseSolver.solve(game));
    }
}