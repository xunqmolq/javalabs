package main.com.labs.lab2;

import java.util.SortedMap;

public class Main {
    public static void main(String[] args)  {
        Matrix matrix = new Matrix (3);
        System.out.println("Creating random matrix 3x3:");
        matrix.fillRandom();
        matrix.printMatrix();

        System.out.println("=========================");
        System.out.println("Creating diagonal matrix by vector (-2,5,23,-5)");
        double[] vector = new double[]{-2,5,23,-5};
        Matrix diagMatrix = Matrix.createDiagonalMatrix(vector);
        diagMatrix.printMatrix();


        System.out.println("=========================");
        System.out.println("Creating unit matrix 4x4");
        Matrix unitMatrix = Matrix.unitMatrix(4);
        unitMatrix.printMatrix();

        System.out.println("=========================");
        System.out.println("Sum of 2 matrix'");
        Matrix mat1 = new Matrix(2);
        Matrix mat2 = new Matrix(2);
        mat1.fillRandom();
        mat2.fillRandom();
        System.out.println("FIRST MATRIX:");
        mat1.printMatrix();
        System.out.println("SECOND MATRIX:");
        mat2.printMatrix();
        Matrix res = mat1.sum(mat2);
        System.out.println("RESULT:");
        res.printMatrix();
    }
}
