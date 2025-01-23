package com.github.roknikolic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Mod2AlgebraTest {
    Mod2Algebra mod2Algebra = new Mod2Algebra();
    @Test
    void makeIdentitySize0() {
        int[][] generatedIdentity =  mod2Algebra.makeIdentity(0);
        int[][] expectedIdentity = {};
        assertArrayEquals(generatedIdentity, expectedIdentity);
    }
    @Test
    void makeIdentitySize4() {
        int[][] generatedIdentity =  mod2Algebra.makeIdentity(4);
        int[][] expectedIdentity = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        assertArrayEquals(generatedIdentity, expectedIdentity);
    }
    @Test
    void addVectorsMod2Normal() {
        int[] vector1 = {1, 0, 0, 1, 0, 1, 1};
        int[] vector2 = {0, 1, 0, 1, 1, 1, 0};
        int[] returnedVector =  mod2Algebra.addVectorsMod2(vector1, vector2);
        int[] expectedVector = {1, 1, 0, 0, 1, 0, 1};
        assertArrayEquals(returnedVector, expectedVector);
    }
    @Test
    void addVectorsMod2Empty() {
        int[] vector1 = {};
        int[] vector2 = {};
        int[] returnedVector =  mod2Algebra.addVectorsMod2(vector1, vector2);
        int[] expectedVector = {};
        assertArrayEquals(returnedVector, expectedVector);
    }
    @Test
    void addVectorsMod2Error() {
        int[] vector1 = {1, 0, 1};
        int[] vector2 = {1, 0};
        assertThrows(IllegalArgumentException.class,
                () -> mod2Algebra.addVectorsMod2(vector1, vector2));
    }
    @Test
    void multiplyMatrixAndVectorMod2Normal() {
        int[][] matrix = {{0, 0, 1}, {1, 1, 0}, {1, 0, 1}};
        int[] vector = {1, 0, 0};
        int[] returnedMatrix =  mod2Algebra.multiplyMatrixAndVectorMod2(matrix, vector);
        int[] expectedMatrix = {0, 1, 1};
        assertArrayEquals(returnedMatrix, expectedMatrix);
    }
    @Test
    void multiplyMatrixAndVectorMod2Empty() {
        int[][] matrix = {{}};
        int[] vector = {};
        int[] returnedMatrix =  mod2Algebra.multiplyMatrixAndVectorMod2(matrix, vector);
        int[] expectedMatrix = {};
        assertArrayEquals(returnedMatrix, expectedMatrix);
    }
    @Test
    void multiplyMatrixAndVectorMod2ErrorSize() {
        int[][] matrix = {{1, 1, 1, 0}, {1, 1, 0, 1}, {1, 0, 1, 1}, {0, 1, 1, 1}};
        int[] vector = {1, 0, 0};
        assertThrows(IllegalArgumentException.class,
                () -> mod2Algebra.multiplyMatrixAndVectorMod2(matrix, vector));
    }
    @Test
    void multiplyMatrixAndVectorMod2Error2D() {
        int[][] matrix = {};
        int[] vector = {1, 0, 0};
        assertThrows(IllegalArgumentException.class,
                () -> mod2Algebra.multiplyMatrixAndVectorMod2(matrix, vector));
    }
    @Test
    void extendMatrixSquare() {
        int[][] matrix1 = {{1, 1, 0}, {1, 0, 0}, {0, 1, 0}};
        int[][] matrix2 = {{0, 0, 1}, {1, 1, 0}, {1, 0, 1}};
        int[][] returnedMatrix =  mod2Algebra.extendMatrix(matrix1, matrix2);
        int[][] expectedMatrix = {{1, 1, 0, 0, 0, 1}, {1, 0, 0, 1, 1, 0}, {0, 1, 0, 1, 0, 1}};
        assertArrayEquals(returnedMatrix, expectedMatrix);
    }
    @Test
    void extendMatrixNotSquare1() {
        int[][] matrix1 = {{1, 1, 0}, {1, 0, 0}, {0, 1, 0}};
        int[][] matrix2 = {{0, 0}, {1, 1}, {1, 0}};
        int[][] returnedMatrix =  mod2Algebra.extendMatrix(matrix1, matrix2);
        int[][] expectedMatrix = {{1, 1, 0, 0, 0}, {1, 0, 0, 1, 1}, {0, 1, 0, 1, 0}};
        assertArrayEquals(returnedMatrix, expectedMatrix);
    }
    @Test
    void extendMatrixNotSquare2() {
        int[][] matrix1 = {{1, 1}, {1, 0}, {0, 1}};
        int[][] matrix2 = {{0, 0, 0}, {1, 1, 1}, {1, 0, 0}};
        int[][] returnedMatrix =  mod2Algebra.extendMatrix(matrix1, matrix2);
        int[][] expectedMatrix = {{1, 1, 0, 0, 0}, {1, 0, 1, 1, 1}, {0, 1, 1, 0, 0}};
        assertArrayEquals(returnedMatrix, expectedMatrix);
    }
    @Test
    void extendMatrixEmpty() {
        int[][] matrix1 = {{1, 1, 0}, {1, 0, 0}, {0, 1, 0}};
        int[][] matrix2 = {{}, {}, {}};
        int[][] returnedMatrix =  mod2Algebra.extendMatrix(matrix1, matrix2);
        int[][] expectedMatrix = {{1, 1, 0}, {1, 0, 0}, {0, 1, 0}};
        assertArrayEquals(returnedMatrix, expectedMatrix);
    }
    @Test
    void extendMatrixError() {
        int[][] matrix1 = {{1, 1, 0}, {1, 0, 0}, {0, 1, 0}};
        int[][] matrix2 = {{0, 0, 1}, {1, 1, 0}};
        assertThrows(IllegalArgumentException.class,
                () -> mod2Algebra.extendMatrix(matrix1, matrix2));
    }
    @Test
    void splitMatrixInHalfNormal() {
        int[][] matrix = {{1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 0, 0}, {0, 1, 0, 1, 1, 1}};
        int[][][] returnedMatrix =  mod2Algebra.splitMatrixInHalf(matrix);
        int[][][] expectedMatrix = {{{1, 1, 0}, {1, 0, 0}, {0, 1, 0}}, {{1, 1, 1}, {0, 0, 0}, {1, 1, 1}}};
        assertArrayEquals(returnedMatrix, expectedMatrix);
    }
    @Test
    void splitMatrixInHalfError() {
        int[][] matrix = {{1, 1, 0}, {1, 0, 0}, {0, 1, 0}};
        assertThrows(IllegalArgumentException.class,
                () -> mod2Algebra.splitMatrixInHalf(matrix));
    }
    @Test
    void inverseMod2Normal4x4() {
        int[][] matrix = {{1, 1, 0, 0}, {0, 0, 0, 1}, {1, 1, 1, 1}, {1, 0, 0, 1}};
        int[][] returnedMatrix =  mod2Algebra.inverseMod2(matrix);
        int[][] expectedMatrix = {{0, 1, 0, 1}, {1, 1, 0, 1}, {1, 1, 1, 0}, {0, 1, 0, 0}};
        assertArrayEquals(returnedMatrix, expectedMatrix);
    }
    @Test
    void inverseMod2Normal3x3() {
        int[][] matrix = {{1, 1, 0}, {0, 1, 0}, {1, 0, 1}};
        int[][] returnedMatrix =  mod2Algebra.inverseMod2(matrix);
        int[][] expectedMatrix = {{1, 1, 0}, {0, 1, 0}, {1, 1, 1}};
        assertArrayEquals(returnedMatrix, expectedMatrix);
    }
    @Test
    void inverseMod2Empty() {
        int[][] matrix = {{}};
        int[][] returnedMatrix =  mod2Algebra.inverseMod2(matrix);
        int[][] expectedMatrix = {};
        assertArrayEquals(returnedMatrix, expectedMatrix);
    }
}