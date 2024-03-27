package rokLights;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LightsChaser lightsOutChaser = new LightsChaser();

        int[][] arr2 = {{1, 1, 0, 0}, {0, 0, 0, 1}, {1, 1, 1, 1}, {1, 0, 0, 1}};
        int[][] arr3 = {{0, 1, 1, 0, 1}, {0, 0, 1, 0, 0}, {1, 0, 0, 0, 1}, {1, 1, 0, 0, 0}, {0, 0, 1, 1, 1}};
        int[][] solvedMatrix = lightsOutChaser.chaseLights(arr3);
        System.out.println(Arrays.deepToString(solvedMatrix));

    }
}