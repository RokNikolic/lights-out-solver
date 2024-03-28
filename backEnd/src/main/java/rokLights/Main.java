package rokLights;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LightsChaseSolver lightsOutChaser = new LightsChaseSolver();

        int[][] S4 = {{1, 1, 0, 0}, {0, 0, 0, 1}, {1, 1, 1, 1}, {1, 0, 0, 1}};
        int[][] notS4 = {{0, 1, 1, 0}, {1, 1, 1, 1}, {1, 0, 1, 1}, {0, 0, 0, 0}};
        int[][] S5 = {{0, 1, 1, 0, 1}, {0, 0, 1, 0, 0}, {1, 0, 0, 0, 1}, {1, 1, 0, 0, 0}, {0, 0, 1, 1, 1}};
        int[][] notS5 = {{1, 0, 1, 1, 1}, {1, 1, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 0}, {1, 1, 1, 1, 1}};

        Game game1 = new Game();
        game1.setMatrix(notS5);

        Game solvedGame = lightsOutChaser.chaseLights(game1);
        System.out.println(solvedGame.getSolvable());
        System.out.println(Arrays.deepToString(solvedGame.getSolution()));

    }
}